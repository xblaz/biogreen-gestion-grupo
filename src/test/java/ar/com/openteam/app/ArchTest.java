package ar.com.openteam.app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ar.com.openteam.app");

        noClasses()
            .that()
            .resideInAnyPackage("ar.com.openteam.app.service..")
            .or()
            .resideInAnyPackage("ar.com.openteam.app.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ar.com.openteam.app.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
