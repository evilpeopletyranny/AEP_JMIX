package ru.ase.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.StringWriter;

import static ru.ase.xml.XMLUtils.getSchemaFileName;

@Service
public class XMLValidator {
    private static final Logger log = LoggerFactory.getLogger(XMLValidator.class);

    public static <T> void validateEntity(T entity, Class<T> clazz) {
        try {
            Marshaller marshaller = createDefaultMarshaller(entity);
            marshaller.setSchema(readSchema(clazz));
            marshaller.marshal(entity, new StreamResult(new StringWriter()));
        } catch (JAXBException e) {
            log.error("Error reading schema .xsd file for class: {}", clazz.getName(), e);
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
            log.error("""
                    Validation failed for entity of class {}:
                    The XML representation does not conform to the XSD schema.
                    Please verify that the XSD file is up-to-date and that the entity's structure matches the schema.
                    """, clazz.getName(), e);
        }
        return schema;
    }
}