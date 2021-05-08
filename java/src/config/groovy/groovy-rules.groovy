//  Copyright (c) 2018 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

final GRADLE_FILES = '*.gradle'
final TEST_FILES = '*Test.*'

final NAME_REGEX = '^[a-z]([A-Z][a-z0-9]|[a-z0-9][A-Z]|[a-z0-9]{2}){1}([A-Z]?[a-z0-9]{1}){0,19}[a-zA-Z0-9]?$'
final STATIC_FINAL_NAME_REGEX = '^[A-Z](_?[A-Z0-9]{1}){2,31}$|' + NAME_REGEX
final STATIC_FINAL_NAME_LENGTH_REGEX = '^[A-Z_0-9]{3,32}$|^[a-zA-Z0-9]{3,23}$'
final TYPE_NAME_REGEX = '^[A-Z][a-z0-9][A-Z]([a-z0-9]([A-Z]?[a-z0-9]{1}){0,29}[a-zA-Z0-9]?)?$|^[A-Z][a-z0-9]{2}([A-Z]?[a-z0-9]{1}){0,29}[a-zA-Z0-9]?$'

ruleset {
  description 'Gradle code rules'

  ruleset('rulesets/basic.xml')

  ruleset('rulesets/braces.xml')

  ruleset('rulesets/convention.xml') {
    CompileStatic(enabled: false)
    CouldBeElvis {
      priority = 0
    }
    MethodParameterTypeRequired {
      doNotApplyToFileNames = GRADLE_FILES
    }
    NoDef {
      doNotApplyToFileNames = GRADLE_FILES
    }
    VariableTypeRequired {
      doNotApplyToFileNames = GRADLE_FILES
    }
    InvertedCondition(enabled: false)
    PublicMethodsBeforeNonPublicMethods(enabled: false)
    StaticMethodsBeforeInstanceMethods(enabled: false)
    TrailingComma(enabled: false)
  }

  ruleset('rulesets/design.xml') {
    PublicInstanceField(enabled: false)
    Instanceof  {
      priority = 0
    }
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
      length = 160
      priority = 3
      ignoreLineRegex = /^\h*.{0,2}["''].*["'][\?\+\)\(\}\]\[\.\=:,;]*$|^package\s|^import\s|public void should\w*()|file:\/\/|http:\/\/|https:\/\/|ftp:\/\/|classpath:|jar:|zip:|find\w*By\w*\(|read\w*By\w*\(|query\w*By\w*\(|get\w*By\w*\(|count\w*By\w*\(/
    }
    SpaceAfterOpeningBrace {
      ignoreEmptyBlock = true
    }
    SpaceAroundMapEntryColon {
      characterAfterColonRegex = /\s/
    }
    SpaceBeforeClosingBrace {
      ignoreEmptyBlock = true
    }
    ClassEndsWithBlankLine(enabled: false)
    ClassStartsWithBlankLine(enabled: false)
    SpaceAroundOperator(enabled: false)
  }

  ruleset('rulesets/groovyism.xml') {
    GStringExpressionWithinString  {
      priority = 0
    }
  }

  ruleset('rulesets/imports.xml') {
    MisorderedStaticImports {
      comesBefore = false
    }
  }

  ruleset('rulesets/logging.xml')

  ruleset('rulesets/naming.xml') {
    ClassName {
      regex = TYPE_NAME_REGEX
      doNotApplyToFileNames = TEST_FILES
    }
    FieldName {
      regex = '^at$|^by$|^id$|^in$|^of$|^on$|^to$|^up$|' + NAME_REGEX
      staticFinalRegex = STATIC_FINAL_NAME_REGEX
    }
    FactoryMethodName(enabled: false)
    InterfaceName {
      regex = TYPE_NAME_REGEX
    }
    MethodName {
      regex = '^of$|' + NAME_REGEX
      doNotApplyToFileNames = TEST_FILES
    }
    ParameterName {
      regex = '^at$|^by$|^id$|^in$|^of$|^on$|^to$|^up$|' + NAME_REGEX
    }
    PropertyName {
      regex = '^at$|^by$|^id$|^in$|^of$|^on$|^to$|^up$|' + NAME_REGEX
      staticFinalRegex = STATIC_FINAL_NAME_REGEX
    }
    VariableName {
      regex = '^at$|^by$|^id$|^in$|^k$|^of$|^on$|^to$|^up$|' + NAME_REGEX
      finalRegex = '^K$|' + NAME_REGEX
      doNotApplyToFileNames = GRADLE_FILES
    }
  }

  ClassName {
    name = 'GroovyTestClassName'
    description = 'Verifies that the name of a class matches a regular expression'
    applyToFileNames = TEST_FILES
  }
  FieldName {
    name = 'ConstantNameLength'
    description = 'Verifies that the length of name of a constant is between 3 and 23/32(Uppercase) characters'
    regex = '^.*$'
    staticFinalRegex = STATIC_FINAL_NAME_LENGTH_REGEX
    violationMessage = 'Constant name length is invalid, min: 3, max: 23/32(Uppercase)'
  }
  MethodName {
    name = 'GroovyTestMethodName'
    description = 'Verifies that the name of each method matches a regular expression'
    regex = '^afterAll$|^afterEach$|^beforeAll$|^beforeEach$|^should[A-Z]([a-z0-9]{1}[A-Z]?)*$|' + NAME_REGEX
    applyToFileNames = TEST_FILES
  }
  VariableName {
    name = 'GradleVariableName'
    description = 'Verifies that the name of each variable matches a regular expression'
    regex = '^at$|^by$|^id$|^in$|^k$|^of$|^on$|^to$|^up$|' + NAME_REGEX
    finalRegex = '^K$|'+ STATIC_FINAL_NAME_REGEX
    applyToFileNames = GRADLE_FILES
  }

  ruleset('rulesets/security.xml') {
    UnsafeArrayDeclaration(enabled: false)
    JavaIoPackageAccess(enabled: false)
    FileCreateTempFile(enabled: false)
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
      maxLines = 30
      doNotApplyToFileNames = TEST_FILES
    }
    ParameterCount {
      maxParameters = 7
    }
    AbcMetric(enabled: false)
    CrapMetric(enabled: false)
    MethodCount {
      maxMethods = 25
      doNotApplyToFileNames = TEST_FILES
    }
  }

  ruleset('rulesets/unnecessary.xml') {
    UnnecessaryGetter {
      doNotApplyToFileNames = TEST_FILES
    }
    UnnecessarySetter {
      doNotApplyToFileNames = TEST_FILES
    }
    UnnecessaryCast(enabled: false)
    UnnecessaryObjectReferences(enabled: false)
    UnnecessaryPublicModifier(enabled: false)
    UnnecessaryReturnKeyword(enabled: false)
  }

  ruleset('rulesets/unused.xml')

  IllegalRegex {
    // Codenarc rule settings
    name = 'CallOnlyOneMethodPerLineForChainedCall'
    description = 'No chained methods call allowed in same line'
    violationMessage = 'Call only one chained method per line: method1(...) NEWLINE .method2(...) NEWLINE .method3(...)'
    // IllegalRegex rule settings
    regex = /[^\(\v]*\(.*\)\h*\..*\(\V*/
  }

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
    name = 'SpaceBeforeOperator'
    description = 'Must set 1 space before operators'
    violationMessage = 'Set 1 space before operator'
    // IllegalRegex rule settings
    regex = /(?<=[\n\r]).((\h*[^"'\n\r\/\*]*(?<!\s)(\!\=|\=\=|\<\=\>|(?<![\!\=\+\-\/\*\&\|\>\<\^%])\=(?!\=)|\+\=|(?<!\+)\+(?![\=\+])|\-\=|(?<!\-)\-(?![\-\=\>])|(?<!\/)\*\=|(?<!\/)\*\*|(?<![\/\*])\*(?![\*\/\=])|%\=|%(?!\=)|\^\=|\^(?!\=)|\|\||\|\=|(?<!\|)\|(?![\|\=])|\&\&|\&\=|(?<![\.\&])\&(?![\&\=])|\?:))|([^\<"'\n\r\/\*]*(?<!\s)((?<!\-)(\>(?!\>)))))[^\n\r]*/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'SpaceAfterOperator'
    description = 'Must set 1 space after operators'
    violationMessage = 'Set 1 space after operator'
    // IllegalRegex rule settings
    regex = /(?<=[\n\r]).\h*[^"'\n\r\/\*]*((\!\=|\=\=|\<\=\>|(?<![\!\=\+\-\/\*\&\|\>\<\^%])\=(?!\=)|\+\=|(?<!\+)\+(?![\=\+])|\-\=|(?<!\-)\-(?![\-\=\>])|(?<!\/)\*\=|(?<!\/)\*\*|(?<![\/\*])\*(?![\*\/\=\.:])|%\=|%(?!\=)|\^\=|\^(?!\=)|\|\||\|\=|(?<!\|)\|(?![\|\=])|\&\&|\&\=|(?<![\&\.])\&(?![\&\=])|\?:|\>\>\>|(?<!\>)\>\>(?!\>)|\<\<\<|(?<!\<)\<\<(?!\<))(?!\s)|\s(\<(?!\<))(?!\s)(?!.*\>))[^\n\r]*/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'Exactly1SpaceBeforeOperator'
    description = 'Must set exactly 1 space before operators'
    violationMessage = 'Set exactly 1 space before operator'
    // IllegalRegex rule settings
    regex = /(?<=[\n\r]).((\h*[^"'\n\r\/\*]*(?<=\s\s)((?<=[\n\r])(\!\=|\=\=|\<\=\>|(?<![\!\=\+\-\/\*\&\|\>\<\^%])\=(?!\=)|\+\=|(?<!\+)\+(?![\=\+])|\-\=|(?<!\-)\-(?![\-\=\>])|(?<!\/)\*\=|(?<!\/)\*\*|%\=|%(?!\=)|\^\=|\^(?!\=)|\|\||\|\=|(?<!\|)\|(?![\|\=])|\&\&|\&\=|(?<![\.\&])\&(?![\&\=])|\?:)))|([^\<"'\n\r\/\*]*(?<=\s\s)\>(?!\>)))[^\n\r]*/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'Exactly1SpaceAfterOperator'
    description = 'Must set exactly 1 space after operators'
    violationMessage = 'Set exactly 1 space after operator'
    // IllegalRegex rule settings
    regex = /(?<=[\n\r]).\h*[^"'\n\r\/\*]*(((\!\=|\=\=|\<\=\>|(?<![\!\=\+\-\/\*\&\|\>\<\^%])\=(?!\=)|\+\=|(?<!\+)\+(?![\=\+])|\-\=|(?<!\-)\-(?![\-\=\>])|(?<!\/)\*\=|(?<!\/)\*\*|%\=|%(?!\=)|\^\=|\^(?!\=)|\|\||\|\=|(?<!\|)\|(?![\|\=])|\&\&|\&\=|(?<![\&\.])\&(?![\&\=])|\?:|\>\>\>|(?<!\>)\>\>(?!\>)|\<\<\<|(?<!\<)\<\<(?!\<)))(?![\n\r])(?=\s\s)|\s(\<(?=\s=\s))(?!.*\>))[^\n\r]*/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'UseMultilineTernaryOperator'
    description = 'Must expand ternary operator in multiple lines'
    violationMessage = 'Expand ternary operator in multiple lines: condition NEWLINE ? expression NEWLINE : expression'
    // IllegalRegex rule settings
    regex = /(?<=[\n\r]).\h*[^"'\n\r\/\*]*([^\s"']+?\h*?(?<!(\<|,|,\h))\?(?![:\>\.])|"[^"\n\r]*?"[^"\n\r]*?(?<!(\<|,|,\h))\?(?![:\>\.])|'[^'\n\r]*?'[^'\n\r]*?(?<!(\<|,|,\h))\?(?![:\>\.]))[^\n\r]*/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'UseDoFamilyMethodsWhenStubbing'
    description = 'Should use "do*"\'s family methods when using Mockito: doAnswer, doCallRealMethod, doNothing, doReturn & doThrow'
    violationMessage = 'Use "do*"\'s family methods when using Mockito: doAnswer, doCallRealMethod, doNothing, doReturn & doThrow'
    applyToFileNames = TEST_FILES
    // IllegalRegex rule settings
    regex = /\bMockito\s*\.\s*when\b/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'UseOnlyWillFamilyMethodsWhenStubbing'
    description = 'Should use only "will*"\'s family methods when using BDD Mockito: will, willAnswer, willCallRealMethod, willDoNothing, willReturn & willThrow'
    violationMessage = 'Use only "will*"\'s family methods when using BDD Mockito: will, willAnswer, willCallRealMethod, willDoNothing, willReturn & willThrow'
    applyToFileNames = TEST_FILES
    // IllegalRegex rule settings
    regex = /\bBDDMockito\s*\.\s*given\b|\bBDDMockito\s*\.\s*when\b/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'UseOnlyThenFamilyMethodsWhenMocking'
    description = 'Should use only "then"\'s family methods when using BDD Mockito: then(..).should(..)'
    violationMessage = 'Use only "then"\'s family methods when using BDD Mockito: then(..).should(..)'
    applyToFileNames = TEST_FILES
    // IllegalRegex rule settings
    regex = /\bBDDMockito\s*\.\s*verify\b/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'DoNotMixBDDandTDD'
    description = 'Should use either org.mockito.BDDMockito or org.mockito.Mockito, not both at the same time.'
    violationMessage = 'Use either org.mockito.BDDMockito or org.mockito.Mockito, not both at the same time.'
    applyToFileNames = TEST_FILES
    // IllegalRegex rule settings
    regex = /\borg\.mockito\.Mockito\b[\S\s]*\borg\.mockito\.BDDMockito\b|\borg\.mockito\.BDDMockito\b[\S\s]*\borg\.mockito\.Mockito\b/
  }

  IllegalRegex {
    // Codenarc rule settings
    name = 'PreferBDDTesting'
    description = 'Should prefer BDD Testing when using Mockito, use org.mockito.BDDMockito\'s methods: will, willAnswer, willCallRealMethod, willDoNothing, willReturn, willThrow & then'
    violationMessage = 'Prefer BDD Testing when using Mockito, use org.mockito.BDDMockito\'s methods: will, willAnswer, willCallRealMethod, willDoNothing, willReturn, willThrow & then'
    applyToFileNames = TEST_FILES
    priority = 0
    // IllegalRegex rule settings
    regex = /\borg\.mockito\.Mockito\b|\bdoAnswer\s*\(|\bdoCallRealMethod\s*\(|\bdoNothing\s*\(|\bdoReturn\s*\(|\bdoThrow\s*\(|\bverify\s*\(/
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
    description = 'Must annotate Main class, interface or trait with @CompileStatic or @TypeChecked'
    violationMessage = 'Annotate Main class, interface or trait with @CompileStatic or @TypeChecked'
    doNotApplyToFileNames = GRADLE_FILES
    // RequiredRegex rule settings
    regex = /(((?<![\}\/]\h*)@CompileStatic|(?<![\}\/]\h*)@TypeChecked)\s+(public\s+)?(final\s+)?(class|interface|trait)) | (^(((?!(class|interface|trait)).)*)$)/
  }
}
