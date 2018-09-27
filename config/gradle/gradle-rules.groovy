//  Copyright (c) 2018 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

final TEST_FILE = '*Test.*'

ruleset {
  description 'Gradle code rules'

  ruleset('rulesets/basic.xml')

  ruleset('rulesets/braces.xml')

  ruleset('rulesets/convention.xml') {
    exclude 'InvertedCondition'
    exclude 'MethodParameterTypeRequired'
    exclude 'NoDef'
    exclude 'PublicMethodsBeforeNonPublicMethods'
    exclude 'StaticMethodsBeforeInstanceMethods'
    exclude 'TrailingComma'
    exclude 'VariableTypeRequired'
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
    LineLength {
      length = 144
      priority = 3
    }
    SpaceAroundMapEntryColon {
      characterAfterColonRegex = /\s/
    }
    exclude 'ClassJavadoc'
    exclude 'Indentation'
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
    CyclomaticComplexity {
      maxMethodComplexity = 8
      doNotApplyToFileNames = TEST_FILE
    }
    MethodCount {
      maxMethods = 10
      doNotApplyToFileNames = TEST_FILE
    }
    MethodSize {
      maxLines = 48
      doNotApplyToFileNames = TEST_FILE
    }
    ParameterCount {
      maxParameters = 5
    }
    exclude 'AbcMetric'
    exclude 'CrapMetric'
  }

  ruleset('rulesets/unnecessary.xml')

  ruleset('rulesets/unused.xml')
}
