package ru.ase.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class XMLUtilsTest {

    @MethodSource("getSchemaNameByClassDataProvider")
    @ParameterizedTest(name = "{0} should return {1}")
    @DisplayName("Schema name generation test")
    void testGetSchemaNameByClass(String schemaName, Class<?> clazz) {
        Assertions.assertEquals(
                System.getProperty("schema.output.dir") + schemaName,
                XMLUtils.getSchemaFileName(clazz));
    }

    static Stream<Arguments> getSchemaNameByClassDataProvider() {
        return EntitiesStream.allEntityClasses().map(clazz ->
                Arguments.of(clazz.getSimpleName()+ ".xsd", clazz)
        );
    }
}