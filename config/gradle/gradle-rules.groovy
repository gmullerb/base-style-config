//  Copyright (c) 2018 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

ruleset {
  description 'Gradle code rules'

  ruleset('rulesets/basic.xml')

  ruleset('rulesets/braces.xml')

  ruleset('rulesets/convention.xml') {
    exclude 'NoDef'
    exclude 'TrailingComma'
    exclude 'VariableTypeRequired'
  }

  ruleset('rulesets/dry.xml')

  ruleset('rulesets/formatting.xml') {
    LineLength {
      length = 144
      priority = 3
    }
    SpaceAroundMapEntryColon {
      characterAfterColonRegex = /\s/
    }
    exclude 'Indentation'
  }

  ruleset('rulesets/groovyism.xml')

  ruleset('rulesets/imports.xml')

  ruleset('rulesets/logging.xml')

  ruleset('rulesets/naming.xml') {
    exclude 'FactoryMethodName'
    exclude 'VariableName'
  }

  ruleset('rulesets/size.xml') {
    CyclomaticComplexity {
      maxMethodComplexity = 8
    }
    MethodCount {
      maxMethods = 10
    }
    MethodSize {
      maxLines = 48
    }
    ParameterCount {
      maxParameters = 5
    }
  }

  ruleset('rulesets/unnecessary.xml')

  ruleset('rulesets/unused.xml')
}
