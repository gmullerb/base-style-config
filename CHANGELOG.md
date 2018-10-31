# Base Style Config Change Log

## 1.0.8 - October 2018

* Sets priority to `0` (are reported but does not cause a failure) for `CouldBeElvis` rule from CodeNarc, was causing some "false-positive", e.g. `if (!object.field) object.field = value` value is required as `object.field = object.field ?: value`.
* Fixes `SpaceBeforeOperator` and `Exactly1SpaceBeforeOperator`:
  * It was not checking `.&` operator.
  * Removes validations for `<` `<<` `<<<` `>>` `>>>`, some false-positive were arising.

## 1.0.7 - October 2018

* Creates `CallOnlyOneMethodPerLineForChainedCall`, a custom rule for CodeNarc rules.
* Creates `UseMultilineTernaryOperator`, a custom rule for CodeNarc rules.
* Customizes PMD's `ClassNamingConventions` rule to allow `Factory` suffix for Utility classes.
* Customizes PMD's `FieldNamingConventions` rule:
  * To do real check for CamelCase.
  * To allow Constant or Non-Constants conventions for `static final` fields..
* Customizes Checkstyle's `AbbreviationAsWordInName` rule to allow some freedom in `final` variables.
* Improves Checkstyle's `CallOnlyOneMethodPerLineForChainedCall` rule.
* Removes CodeNarc's `SpaceAroundOperator` rule, it is unstable for some patterns (produces some false-positive).
* Creates `SpaceBeforeOperator`, `SpaceAfterOperator`, `Exactly1SpaceBeforeOperator` & `Exactly1SpaceAfterOperator` rules for CodeNarc.
  * Substitutes `SpaceAroundOperator` rule.
* Removes `MethodCount` for CodeNarc (missed this before).
* Fixes `UseMultilineTernaryOperator` rule to ignore Wildcards.
* Fixes Checkstyle's suppression file.
* Updates README file.

## 1.0.6 - October 2018

* Adds new rules to Checkstyle and CodeNarc:
  * `NameOfTestsMustStartWithShould` to guaranteed that test methods name are prefixed with the word `should`.
* Adds `AnonInnerLength` and `VariableDeclarationUsageDistance` check to [Backend Checkstyle checks](config/back/coding-checks.xml).
* Updates _Method Size Rule_ for Checkstyle, CodeNarc & ESLint to 25 lines, which is readable without scrolling, this will increase Mantainability.
* Removes _Method Count Rules_ and adds _File Length rules_ for Checkstyle, CodeNarc & ESLint, to allow to be more functional oriented.
  * Disables _File Length Rule_ for test files in Checkstyle & CodeNarc.
  * Actually does not remove `MethodCount` for [Backend Checkstyle checks](config/back/coding-checks.xml), **but modifies** in order to only check the count for public methods.
* Adds `TooManyFields` PMD rule to allow a maximum of 6 fields.
* Enables _Cyclomatic Complexity rule_ for Test files on Checkstyle & CodeNarc, in order to increase its Mantainability.
* Updates README file.

## 1.0.5 - October 2018

* Maven Group now is on all.shared.quality
* Fixes `AnnotateClassesWith@CompileStaticOr@TypeChecked` to also allow final classes.
* Ignores some of the CodeNarc's rules for Test's files: UnnecessarySetter to allow mocking and verification.
* Excludes CodeNarc's rule 'UnnecessaryObjectReferences' for same reason that other languages do not recommend it, e.g.: performance, readability.
* Updates README file.

## 1.0.4 - September 2018

* Adds new rules to Checkstyle and CodeNarc:
  * `UseOnlyMockOrSpyPrefixOnTestFiles` to use the '`mock`' or '`spy`' prefixes only on test's files.
    * This rule is ignored in Test's files.
  * `UseOnlyDoFamilyMethodsWhenMocking` to use only `do*`'s family methods when using Mockito: `doAnswer`, `doCallRealMethod`, `doNothing`, `doReturn` & `doThrow` (`Mockito.when` and `BDDMockito.given` have some "issues")
* Adds new rule `AnnotateClassesWith@CompileStaticOr@TypeChecked` to CodeNarc to check if Main class is annotated with `@CompileStatic` or `@TypeChecked`.
* Customizes CodeNarc's rule `BracesForIfElse` to have same Stroustrup style that Checkstyle and ESLint.
* Enables some CodeNarc's rules for `*.groovy` files and excludes to `*.gradle` files: `Indentation`, `NoDef`, `Indentation` and `VariableTypeRequired`
* Ignores some of the CodeNarc's rules for Test's files: `UnnecessaryGetter` to allow mocking.
* Fixes some indentation issues in `coding-checks.xml` file.
* Updates README file.

## 1.0.3 - September 2018

* Adds `DeclarationOrder` check to [Backend Checkstyle checks](config/back/coding-checks.xml).
* Updates [Suppressions](config/back/checks-suppressions.xml) for Test's files in Checkstyle:
  * Adds suppressions for `CyclomaticComplexity` and `MethodLength` checks.
* Updates CodeNarc's rulesets in order to:
  * Be more similar to [Backend Checkstyle checks](config/back/coding-checks.xml): updates `rulesets/convention.xml`, `rulesets/imports.xml` and `rulesets/naming.xml`.
  * Ignore some rules for Test's files: `CyclomaticComplexity`, `MethodCount` and `MethodSize`.
* Sets priority to `0` (are reported but does not cause a failure) for `DuplicateNumberLiteral` and `DuplicateStringLiteral` rules from CodeNarc, at the moment of this change, these two rules did not allow for a configurable limit of allowed duplications, which turns these rules into impractical.
* Excludes `ClassJavadoc` rule from CodeNarc's `rulesets/formatting.xml` set of rules.
* Excludes `CrapMetric` rule from CodeNarc's `rulesets/size.xml` set of rules.
* Updates README file.

## 1.0.2 - September 2018

* Excludes `AbcMetric` rule from CodeNarc's `rulesets/size.xml` set of rules.

## 1.0.1 - September 2018

* Adds new rules to Checkstyle to check Stroustrup brackets on Backend:
  * `LeftCurly`.
  * `RightCurly`.
* Adds new rules to ESLint mainly to get consistency between Backend's and Frontend's code:
  * `max-classes-per-file`.
  * `max-lines` (to get a similar to Backend limit on method count).
  * `complexity`.
* Adds new set of rules to Codenarc mainly to get consistency between Backend's, Frontend's and Build's code:
  * `rulesets/size.xml`.
* Modifies some ESLint's rules:
  * `brace-style` is now `stroustrup`.
* Moves the Cyclomatic Complexity's check from PMD to Checkstyle.
* Sets parameters number limit for methods to 5.
* Improves the `AvoidFieldInjection` PMD custom rule, to check for inline annotations: `@javax.inject.Inject`, `@com.google.inject.Inject` and `@org.springframework.beans.factory.annotation.Autowired`.
* Fixes the `BeanMembersShouldSerialize` PMD core rule, to check for `transient` only when the class does implements `java.io.Serializable` interface or has the `javax.persistence.Entity` Annotation.
* Upgrades Gradle to version 4.10.2.
* Updates README file.
