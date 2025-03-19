package ru.ase.integration.db;

import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ase.AEPJMIXApplication;
import ru.ase.entity.structure.PBSCode;
import ru.ase.support.extension.PostgreSQLExtension;
import ru.ase.support.generator.KksCodeGenerator;
import ru.ase.support.initializer.TestContextInitializer;

@SpringBootTest
@ExtendWith({PostgreSQLExtension.class, SpringExtension.class})
@ContextConfiguration(classes = AEPJMIXApplication.class, initializers = TestContextInitializer.class)
@TestPropertySource("classpath:application.properties")
public class DbIntegrationTest {
    @Autowired
    private DataManager dataManager;

    @Autowired
    private SystemAuthenticator systemAuthenticator;

    @Test
    void test() {
        PBSCode pbsCode = dataManager.create(PBSCode.class);
        pbsCode.setCode(KksCodeGenerator.generateKksCode(16));
        systemAuthenticator.runWithSystem(() -> {
            dataManager.save(pbsCode);
        });
    }

}
