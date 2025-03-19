package ru.ase.support.initializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "jmix.datasource.url=jdbc:tc:postgresql:16.4:///postgres-test-db",
                "jmix.datasource.username=test",
                "jmix.datasource.password=pass",
                "jmix.data.dbmsType=postgres",
                "main.liquibase.context=!production-data"
        ).applyTo(applicationContext.getEnvironment());
    }
}
