package ru.ase.xml;

import jakarta.xml.bind.*;
import org.xml.sax.SAXException;
import ru.ase.entity.DesignCondition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

//TODO: рефакторинг
//TODO: тесты
public class SchemaGenerator {
    private static final Logger logger = LoggerFactory.getLogger(SchemaGenerator.class);

    private SchemaGenerator() {
    }

    private static String getSchemaFileName(Class<?> clazz) {
        return clazz.getSimpleName() + ".xsd";
    }

    public static void generateSchema(Class<?> clazz) {
        try {
            JAXBContext.newInstance(clazz).generateSchema(new FileSchemaOutputResolver(new File(getSchemaFileName(clazz))));
        } catch (IOException e) {
            logger.error("Error writing schema .xsd file for class: {}", clazz.getName(), e);
        } catch (JAXBException e) {
            logger.error("JAXB error while generating schema for class: {}", clazz.getName(), e);
        }
    }

    private static class FileSchemaOutputResolver extends SchemaOutputResolver {
        private final File file;

        public FileSchemaOutputResolver(File file) {
            this.file = file;
        }

        @Override
        public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            StreamResult result = new StreamResult(writer);
            result.setSystemId(file.toURI().toString());
            return result;
        }
    }

    public static <T> void validateEntity(T entity, Class<T> clazz) {
        try {
            Marshaller marshaller = createDefaultMarshaller(entity);
            marshaller.setSchema(readSchema(clazz));
            marshaller.marshal(entity, new StreamResult(new StringWriter()));
        } catch (JAXBException e) {
            logger.error("Error reading schema .xsd file for class: {}", clazz.getName(), e);
        }
    }

    private static <T> Marshaller createDefaultMarshaller(T entity) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(entity.getClass()).createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);

        return marshaller;
    }

    private static <T> Schema readSchema(Class<T> clazz) {
        Schema schema = null;
        try {
            schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(getSchemaFileName(clazz)));
        } catch (SAXException e) {
            logger.error("""
                    Validation failed for entity of class {}:
                    The XML representation does not conform to the XSD schema.
                    Please verify that the XSD file is up-to-date and that the entity's structure matches the schema.
                    """, clazz.getName(), e);
        }
        return schema;
    }

    public static void main(String[] args) throws IOException, JAXBException, SAXException {
        SchemaGenerator.generateSchema(DesignCondition.class);

        var condition = new DesignCondition();
        condition.setId(UUID.randomUUID());
        condition.setName("Design condition");

        SchemaGenerator.validateEntity(condition, DesignCondition.class);
    }
}