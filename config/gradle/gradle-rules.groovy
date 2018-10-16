//  Copyright (c) 2018 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

final GRADLE_FILES = '*.gradle'
final TEST_FILES = '*Test.*'

ruleset {
  description 'Gradle code rules'

  ruleset('rulesets/basic.xml')

  ruleset('rulesets/braces.xml')

  ruleset('rulesets/convention.xml') {
    MethodParameterTypeRequired {
      doNotApplyToFileNames = GRADLE_FILES
    }
    NoDef {
      doNotApplyToFileNames = GRADLE_FILES
    }
    VariableTypeRequired {
      doNotApplyToFileNames = GRADLE_FILES
    }
    exclude 'InvertedCondition'
    exclude 'PublicMethodsBeforeNonPublicMethods'
    exclude 'StaticMethodsBeforeInstanceMethods'
    exclude 'TrailingComma'
  }

  ruleset('rulesets/dry.xml') {
    DuplicateNumberLiteral {
      priority = 0
    }
    DuplicateStringLiteral {
      priority = 0
    }
  }

  ruleset('rulesets/formatting.xml') {
    BracesForIfElse {
      validateElse = true
      elseOnSameLineAsClosingBrace = false
    }
    Indentation {
      spacesPerIndentLevel = 2
      doNotApplyToFileNames = GRADLE_FILES
    }
    LineLength {
      length = 144
      priority = 3
    }
    SpaceAroundMapEntryColon {
      characterAfterColonRegex = /\s/
    }
    exclude 'ClassJavadoc'
  }

  ruleset('rulesets/groovyism.xml')

  ruleset('rulesets/imports.xml') {
    MisorderedStaticImports {
      comesBefore = false
    }
  }

  ruleset('rulesets/logging.xml')

  ruleset('rulesets/naming.xml') {
    FieldName {
      staticFinalRegex = '[a-zA-Z](_?[a-zA-Z0-9]+)*'
    }
    exclude 'FactoryMethodName'
    exclude 'VariableName'
  }

  ruleset('rulesets/size.xml') {
    ClassSize {
      maxLines = 300
      doNotApplyToFileNames = TEST_FILES
    }
    CyclomaticComplexity {
      maxMethodComplexity = 8
    }
    MethodSize {
      maxLines = 25
      doNotApplyToFileNames = TEST_FILES
    }
    ParameterCount {
      maxParameters = 5
    }
    exclude 'AbcMetric'
    exclude 'CrapMetric'
  }

  ruleset('rulesets/unnecessary.xml') {
    UnnecessaryGetter {
      doNotApplyToFileNames = TEST_FILES
    }
    UnnecessarySetter {
      doNotApplyToFileNames = TEST_FILES
    }
    exclude 'UnnecessaryObjectReferences'
  }

  ruleset('rulesets/unused.xml')

  IllegalRegex {
    // Codenarc rule settings
    name = 'NameOfTestsMustStartWithShould'
    description = 'Names of Test methods must begin with "should"'
    violationMessage = 'Name of the Test method must begin with "should"'
    applyToFileNames = TEST_FILES
    // IllegalRegex rule settings
    regex = /(@Test|@org\.junit\.jupiter\.api\.Test|@org\.junit\.Test|@org\.testng\.annotations\.Test)\s+((public|protected)\s+)?void(?!\s+should)/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'UseOnlyDoFamilyMethodsWhenMocking'
    description = 'Should use only "do*"\'s family methods when using Mockito: doAnswer, doCallRealMethod, doNothing, doReturn & doThrow'
    violationMessage = 'Use only "do*"\'s family methods when using Mockito: doAnswer, doCallRealMethod, doNothing, doReturn & doThrow'
    applyToFileNames = TEST_FILES
    // IllegalRegex rule settings
    regex = /Mockito\s*\.\s*when|org\.mockito\.BDDMockito/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'UseOnlyMockOrSpyPrefixOnTestFiles'
    description = 'Should use the "mock" or "spy" prefixes only on test\'s files\' code'
    violationMessage = 'Use the "mock" or "spy" prefixes only on test\'s files\' code'
    doNotApplyToFileNames = "$GRADLE_FILES,$TEST_FILES"
    priority = 3
    // IllegalRegex rule settings
    regex = /\b(mock|spy)[A-Z0-9]/
  }

  RequiredRegex {
    // Codenarc rule settings
    name = 'AnnotateClassesWith@CompileStaticOr@TypeChecked'
    description = 'Must annotate Main class with @CompileStatic or @TypeChecked'
    violationMessage = 'Annotate Main class with @CompileStatic or @TypeChecked'
    doNotApplyToFileNames = GRADLE_FILES
    // RequiredRegex rule settings
    regex = /(((?<![\}\/]\s*)@CompileStatic|(?<![\}\/]\s*)@TypeChecked)\s+(public\s+)?(final\s+)?class) | (^(((?!class).)*)$)/
  }
}
