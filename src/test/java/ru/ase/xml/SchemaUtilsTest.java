package ru.ase.xml;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

class SchemaUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(SchemaUtilsTest.class);

    @ParameterizedTest(name = "Generating schema for {0}")
    @MethodSource("ru.ase.xml.EntitiesStream#externalEntityClasses")
    @DisplayName("Schema generation test")
    void testGenerateSchemaForExternalClasses(Class<?> clazz) {
        SchemaUtils.generateSchema(clazz);

        File schemaFile = new File(XMLUtils.getSchemaFileName(clazz));
        Assertions.assertTrue(schemaFile.exists(), "Schema file for " + clazz.getSimpleName() + " should exist");
    }

    @AfterAll
    static void cleanupSchemaFiles() {
        EntitiesStream.externalEntityClasses().forEach(clazz -> {
            File schemaFile = new File(XMLUtils.getSchemaFileName(clazz));
            if (schemaFile.exists() && !schemaFile.delete())
                log.error("Failed to delete schema file: " + schemaFile.getAbsolutePath());
        });
    }
}