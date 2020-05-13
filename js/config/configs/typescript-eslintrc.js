//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  parserOptions: {
    sourceType: 'module',
    project: './tsconfig.json'
  },
  plugins: [ '@typescript-eslint' ],
  rules: {
    '@typescript-eslint/consistent-type-definitions': [
      'error',
      'interface'
    ],
    '@typescript-eslint/ban-types': [
      'error', {
        types: {
          'Array': 'Use [] instead',
          'Object': 'Use {} instead',
          'Component': 'Use function component instead',
          'PureComponent': 'Use function component instead',
          'React.Component': 'Use function component instead',
          'React.PureComponent': 'Use function component instead',
          'String': 'Use string instead'
        }
      }
    ],
    '@typescript-eslint/explicit-function-return-type': [
      'error', {
        allowExpressions: true
      }
    ],
    '@typescript-eslint/explicit-module-boundary-types': 'error',
    '@typescript-eslint/generic-type-naming': [
      'error',
      '^[A-Z][A-Z_][A-Z_0-9]{1,22}$'
    ],
    '@typescript-eslint/member-ordering': 'error',
    '@typescript-eslint/consistent-type-assertions': 'error',
    '@typescript-eslint/no-extraneous-class': [
      'error', {
        allowWithDecorator: true
      }
    ],
    '@typescript-eslint/no-inferrable-types': 'error',
    '@typescript-eslint/no-misused-promises': 'error',
    '@typescript-eslint/no-this-alias': 'error',
    '@typescript-eslint/no-type-alias': [
      'warn', {
        allowAliases: 'in-unions-and-intersections',
        allowCallbacks: 'never',
        allowLiterals: 'in-unions-and-intersections',
        allowMappedTypes: 'always'
      }
    ],
    '@typescript-eslint/no-unnecessary-qualifier': 'error',
    '@typescript-eslint/no-unnecessary-type-assertion': 'error',
    '@typescript-eslint/no-useless-constructor': 'error',
    '@typescript-eslint/prefer-as-const': 'error',
    '@typescript-eslint/prefer-readonly': 'error',
    '@typescript-eslint/prefer-readonly-parameter-types': 'error',
    '@typescript-eslint/prefer-reduce-type-parameter': 'error',
    '@typescript-eslint/promise-function-async': 'error',
    '@typescript-eslint/type-annotation-spacing': 'error'
  }
}
