package ru.ase.support.generator;

import org.apache.commons.lang3.RandomStringUtils;

public class KksCodeGenerator {

    // Набор символов для генерации кода: заглавные латинские буквы и цифры
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Генерирует KKS-код заданной длины.
     *
     * @param length длина кода (должна быть положительной)
     * @return сгенерированный KKS-код
     * @throws IllegalArgumentException если length меньше или равна нулю
     */
    public static String generateKksCode(Integer length) {
        return RandomStringUtils.random(length, CHARACTERS);
    }
}
