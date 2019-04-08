# Base Style Config Change Log

## 2.0.1 - April 2019

* Updates `EmptyLineSeparator` Checkstyle rule to allow fields on a class to be immediately below to each other without line between them.
* Removes `forceStrictCondition` from Checkstyles which allows more freedom on indentation in order to get a code more similar to JS code.
* Updates Gitlab CI configuration.
* Updates README file.
  * Adds a new section `Code Style convention/tips`, in order to talk about some code style conventions which allow to improve Readability and also allow more code style consistency between Java, Groovy and JS code, since some rules between them cannot be exactly equally defined.

## 2.0.0 - March 2019

* Breaking changes:
  * Changes `gradle` folder name to `groovy`.
  * Changes `gradle-rules.groovy` file name to `groovy-rules.groovy`.
  * Removes `"extends"` from `.eslintrc.json`, it was not allowing for proper eslint config cascading. (It's not really "breaking" change, but the assumption that extends "eslint:recommended" is lost).
* Adds the `design` rule set to Codenarc.
* Strengthen import rules in Javascript, makes it more consistent with Defined Backend rules:
  * Adds `sort-imports`, `no-duplicate-imports`, `no-new-require` and `no-mixed-requires`.
* Updates the eslint rules with `operator-linebreak` and the checkstyle checks with `OperatorWrap` in order to check for line breaks on operators, operator must be on a new line, this with the intention of increasing Readability.
* Updates Codenarc rules `SpaceAfterOpeningBrace` & `SpaceBeforeClosingBrace` to ignore empty blocks.
* Excludes Codenarc rule `UnnecessaryReturnKeyword` in order to increase Readability, some cases may be ok, like a method with one statement. Additionally, explicitly using `return` may increase code coverage values.
* Excludes Codenarc rule `UnnecessaryCast`, in some case is required the use of a cast, e.g. an empty List of String `[] as List<String>`.
* Excludes new Codenarc rules `ClassEndsWithBlankLine` & `ClassStartsWithBlankLine`.
* Updates Codenarc exclusion to the new format using `(enabled: false)`, which allows for better rules version validation.
* Adds Versions Compatibility Table.
* Updates Gitlab CI configuration.
* Upgrades Gradle to version 5.3.
* Updates README file.

## 1.0.14 - March 2019

* Removes `quotes` from eslint rules, should be set based on Backend language (or preference), for Groovy could be set to `'` -> `"quotes": ["quotes", "single"]`, for C++, Java or QML could be set to `"` ->  `"quotes": ["quotes", "double"]`.
* Removes `semi` from eslint rules, should be set based on Backend language (or preference), for Groovy or QML could be set to `"semi": ["quotes", "never"]`, for C++ or Java could be set to `"semi": ["quotes", "always"]`.
* Updates `indent` eslint rules to enforce different indentation in function declarations.
* Updates DOCTYPE's FPI and URI for checkstyle files.
* Adds new exceptions and markers to `spaced-comment`, to allow special comments, like Flow comments.
* Updates README file.

## 1.0.13 - February 2019

* Removes `requires-jsdoc` from eslint rules, as in Java checks, favoring self-documented code and agile development, and not forcing documentation.
* Adds exceptions and markers to `spaced-comment`, to allow special comments, like Flow comments.
* Adds Smoke Tests for .eslintrc and .stylelintrc.
* Updates README file.

## February 2019 B

* Adds gitlab-ci.yml.
* Updates build.gradle.
* Updates README file.

## 1.0.12 - February 2019

* Removes `UnnecessaryPublicModifier` from CodeNarc's rules, switching between different languages, for example Java, Groovy and C ++, the change can become confusing with the default modifiers, this will allow using modifiers always, and forgetting what is the default value.
* Changes `CustomImportOrder` check to give more freedom to import on java, due to all this mixing of programming languages can be in a project, it's "better" to only require alphabetical order.
* Fixes `CallOnlyOneMethodPerLineForChainedCall` check, it was giving false positives for split calls and had some problems with Casts.
* Fixes `UseMultilineTernaryOperator` Checkstyle check, it had some XML CDATA error.
* Adds Tests for Regular Expressions.
  * Updates [`build.gradle`](/build.gradle) for adding respective dependencies.
* Upgrades Gradle to version 5.2.1.
* Updates README file.

## February 2019

* Moves configuration files from config/ to src/config (may get confused with project config files).

## 1.0.11 - December 2018

* Fixes `UseMultilineTernaryOperator`, it was missing some cases for generics wildcards.

## 1.0.10 - December 2018

* Updates `AnnotateClassesWith@CompileStaticOr@TypeChecked` to also check for interfaces and traits.
* Updates README file.

## 1.0.9 - November 2018

* Sets priority to `0` (reported but does not cause a failure) for `GStringExpressionWithinString` rule from CodeNarc, it was causing some "false-positive", e.g. `'--file ${npm_config_configFile}'`.
* Updates README file.

## 1.0.8 - October 2018

* Sets priority to `0` (reported but does not cause a failure) for `CouldBeElvis` rule from CodeNarc, it was causing some "false-positive", e.g. `if (!object.field) object.field = value` value is required as `object.field = object.field ?: value`.
* Fixes `SpaceBeforeOperator` and `Exactly1SpaceBeforeOperator`:
  * It was not checking `.&` operator.
  * Removes validations for `<` `<<` `<<<` `>>` `>>>`, some false-positive were arising.
* Updates README file.

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
