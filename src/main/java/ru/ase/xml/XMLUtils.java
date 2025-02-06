package ru.ase.xml;

import java.io.File;

public class XMLUtils {
    public static String getSchemaBaseDir() {
        return System.getProperty("schema.output.dir",
                "src/main/resources/ru/ase/xml/");
    }

    public static String getSchemaFileName(Class<?> clazz) {
        return getSchemaBaseDir() + clazz.getSimpleName() + ".xsd";
    }

    private XMLUtils() {
    }
}
