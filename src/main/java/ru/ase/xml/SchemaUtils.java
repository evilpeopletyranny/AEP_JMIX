package ru.ase.xml;

import jakarta.xml.bind.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class SchemaUtils {
    private static final Logger logger = LoggerFactory.getLogger(SchemaUtils.class);

    private SchemaUtils() {
    }

    public static void generateSchema(Class<?> clazz) {
        try {
            JAXBContext.newInstance(clazz).generateSchema(new FileSchemaOutputResolver(new File(XMLUtils.getSchemaFileName(clazz))));
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
}