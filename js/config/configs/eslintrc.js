//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  rules: {
    'accessor-pairs': 'error',
    'array-bracket-newline': [
      'error',
      'consistent'
    ],
    'array-bracket-spacing': [
      'error',
      'always'
    ],
    'array-element-newline': [
      'error',
      'consistent'
    ],
    'block-scoped-var': 'error',
    'brace-style': [
      'error',
      'stroustrup'
    ],
    'camelcase': 'error',
    'complexity': [
      'error', {
        max: 8
      }
    ],
    'comma-spacing': 'error',
    'comma-style': 'error',
    'computed-property-spacing': 'error',
    'consistent-return': 'error',
    'curly': 'error',
    'dot-notation': 'error',
    'dot-location': [
      'error',
      'property'
    ],
    'eqeqeq': 'error',
    'eol-last': 'error',
    'func-call-spacing': 'error',
    'indent': [
      'error',
      2,
      {
        SwitchCase: 1,
        offsetTernaryExpressions: true,
        FunctionDeclaration: {
          body: 1,
          parameters: 2
        },
        FunctionExpression: {
          body: 1,
          parameters: 2
        }
      }
    ],
    'init-declarations': 'error',
    'id-match': [
      'error',
      '^\\$$|^\\$\\$$|^at$|^by$|^_id$|^id$|^in$|^k$|^K$|^of$|^on$|^to$|^up$|^T[A-Z]?[0-9]{0,2}$|(\\b(HTML|SVG|XML|JSON|URL|URI|XSL|IDB|RTC|UI|VR|DOM|CSS|RGB)\\w*)|(\\w*(HTML|SVG|XML|JSON|URL|URI|XSL|IDB|RTC|UI|VR|DOM|CSS|RGB)\\b)|^[A-Z]([_\\$]?[A-Z0-9]{1}){2,31}$|^_?[a-z]([A-Z][a-z0-9]|[a-z0-9][A-Z]|[a-z0-9]{2}){1}([A-Z]?[a-z0-9]{1}){0,19}[a-zA-Z0-9]?$|^[A-Z][a-z0-9][A-Z]([a-z0-9]([A-Z]?[a-z0-9]{1}){0,29}[a-zA-Z0-9]?)?$|^[A-Z][a-z0-9]{2}([A-Z]?[a-z0-9]{1}){0,29}[a-zA-Z0-9]?$',
      {
        properties: true,
        onlyDeclarations: true,
        ignoreDestructuring: true
      }
    ],
    'max-classes-per-file': 'error',
    'max-len': [
      'error', {
        code: 144,
        ignoreComments: false,
        ignoreTrailingComments: false,
        ignoreStrings: true,
        ignoreTemplateLiterals: true,
        ignoreRegExpLiterals: false
      }
    ],
    'max-lines': 'error',
    'max-params': [
      'error', {
        max: 7
      }
    ],
    'max-statements': [
      'error', {
        max: 30
      }
    ],
    'new-cap': [
      'error', {
        capIsNew: false
      }
    ],
    'new-parens': 'error',
    'newline-per-chained-call': 'error',
    'no-alert': 'error',
    'no-array-constructor': 'error',
    'no-caller': 'error',
    'no-duplicate-imports': 'error',
    'no-else-return': 'error',
    'no-eval': 'error',
    'no-extend-native': 'error',
    'no-extra-bind': 'error',
    'no-implicit-globals': 'error',
    'no-implied-eval': 'error',
    'no-labels': 'error',
    'no-lone-blocks': 'error',
    'no-mixed-operators': 'error',
    'no-mixed-requires': 'error',
    'no-multi-spaces': 'error',
    'no-multi-str': 'error',
    'no-new-func': 'error',
    'no-new-object': 'error',
    'no-new-require': 'error',
    'no-new-wrappers': 'error',
    'no-param-reassign': 'error',
    'no-script-url': 'error',
    'no-shadow-restricted-names': 'error',
    'no-tabs': 'error',
    'no-throw-literal': 'error',
    'no-trailing-spaces': 'error',
    'no-undef-init': 'error',
    'no-unmodified-loop-condition': 'error',
    'no-unneeded-ternary': 'error',
    'no-unused-expressions': [
      'error', {
        allowShortCircuit: true,
        allowTernary: true
      }
    ],
    'no-useless-return': 'error',
    'no-use-before-define': 'error',
    'no-void': 'error',
    'no-with': 'error',
    'object-curly-newline': [
      'error', {
        consistent: true
      }
    ],
    'object-curly-spacing': [
      'error',
      'always',
      {
        arraysInObjects: false,
        objectsInObjects: false
      }
    ],
    'object-property-newline': [
      'error',
      {
        allowAllPropertiesOnSameLine: true
      }
    ],
    'operator-assignment': 'error',
    'operator-linebreak': [
      'error',
      'before',
      {
        overrides:
        {
          '&&': 'after'
        }
      }
    ],
    'quote-props': [
      'error',
      'consistent-as-needed',
      {
        keywords: false
      }
    ],
    'spaced-comment': [
      'error',
      'always',
      {
        exceptions: [ '-', '+', '*', '/', ':', '::', '@', '?', '?:' ],
        markers: [ '-', '+', '*', '/', ':', '::', '@', '?', '?:' ]
      }
    ],
    'space-before-function-paren': [
      'error', {
        named: 'never',
        anonymous: 'always'
      }
    ],
    'space-before-blocks': 'error',
    'space-infix-ops': 'error',
    'space-in-parens': 'error',
    'space-unary-ops': [
      'error', {
        words: true,
        nonwords: false
      }
    ],
    'valid-jsdoc': [
      'error', {
        requireReturn: false,
        requireReturnType: true,
        requireReturnDescription: true,
        requireParamDescription: true
      }
    ],
    'wrap-iife': 'error'
  }
}
