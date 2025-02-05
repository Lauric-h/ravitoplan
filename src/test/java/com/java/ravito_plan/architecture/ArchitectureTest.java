package com.java.ravito_plan.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

public class ArchitectureTest {
  ImportOption ignoreTests = new ImportOption() {
    @Override
    public boolean includes(Location location) {
      return !location.contains("/test/"); // ignore any URI to sources that contains '/test/'
    }
  };

  private final JavaClasses classes =
      new ClassFileImporter().withImportOption(ignoreTests).importPackages("com.java.ravito_plan");

  @Test
  void domainShouldNotDependOnInfrastructure() {
    ArchRule rule =
        noClasses()
            .that()
            .resideInAPackage("..domain..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..infrastructure..");

    rule.check(classes);
  }

  @Test
  void applicationShouldNotDependOnInfrastructure() {
    ArchRule rule =
        noClasses()
            .that()
            .resideInAPackage("..application..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..infrastructure..");

    rule.check(classes);
  }

  @Test
  void infrastructureShouldOnlyBeAccessedByControllers() {
    ArchRule rule =
        classes()
            .that()
            .resideInAPackage("..infrastructure..")
            .should()
            .onlyBeAccessed()
            .byAnyPackage("..infrastructure..", "..api..", "..RavitoPlanApplication");

    rule.check(classes);
  }

  @Test
  void controllerNamingRule() {
    ArchRule rule =
        classes()
            .that()
            .resideInAPackage("..api..")
            .and()
            .areNotInterfaces()
            .should()
            .haveSimpleNameEndingWith("Controller");

    rule.check(classes);
  }

  @Test
  void presenterNamingRule() {
    ArchRule rule =
        classes()
            .that()
            .resideInAPackage("..infrastructure.presenter..")
            .and()
            .areNotInterfaces()
            .should()
            .haveSimpleNameEndingWith("Presenter")
            .orShould()
            .haveSimpleNameEndingWith("ViewModel");

    rule.check(classes);
  }

  @Test
  void repositoryImplementationNamingRule() {
    ArchRule rule =
        classes()
            .that()
            .resideInAPackage("..infrastructure.adapters.repository..")
            .and()
            .areNotInterfaces()
            .should()
            .haveSimpleNameEndingWith("Adapter")
            .orShould()
            .haveSimpleNameEndingWith("Repository");

    rule.check(classes);
  }

  @Test
  void domainModelLocation() {
    ArchRule rule =
        classes()
            .that()
            .haveSimpleNameEndingWith("Food")
            .or()
            .haveSimpleNameEndingWith("Race")
            .or()
            .haveSimpleNameEndingWith("User")
            .or()
            .haveSimpleNameEndingWith("Brand")
            .or()
            .haveSimpleNameEndingWith("Checkpoint")
            .and()
            .areNotInterfaces()
            .should()
            .resideInAPackage("..domain.model..");

    rule.check(classes);
  }

  @Test
  void dtoPackageStructure() {
    ArchRule rule =
        classes()
            .that()
            .haveSimpleNameEndingWith("Command")
            .or()
            .haveSimpleNameEndingWith("View")
            .or()
            .haveSimpleNameEndingWith("Dto")
            .should()
            .resideInAPackage("..dto..");

    rule.check(classes);
  }

  @Test
  void exceptionNaming() {
    ArchRule rule =
        classes()
            .that()
            .areAssignableTo(Exception.class)
            .should()
            .haveSimpleNameEndingWith("Exception");

    rule.check(classes);
  }

  @Test
  void portsDependencyRules() {
    ArchRule rule =
        classes()
            .that()
            .resideInAPackage("..domain.ports..")
            .should()
            .onlyBeAccessed()
            .byAnyPackage("..domain..", "..infrastructure.adapters..")
            .orShould()
                .onlyBeAccessed().byClassesThat().
                resideInAPackage("..infrastructure.service..")
                .orShould()
                .onlyBeAccessed().byClassesThat().resideInAPackage("..common.api..")
            ;

    rule.check(classes);
  }

  @Test
  void useCaseStructure() {
    ArchRule rule =
        classes()
            .that()
            .haveSimpleNameEndingWith("Request")
            .or()
            .haveSimpleNameEndingWith("Response")
            .should()
            .resideInAPackage("..usecase..");

    rule.check(classes);
  }
}
