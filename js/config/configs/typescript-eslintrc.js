//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  extends: './configs/.eslintrc.js',
  parser: '@typescript-eslint/parser',
  parserOptions: {
    sourceType: 'module',
    project: './tsconfig.json'
  },
  plugins: ['@typescript-eslint'],
  rules: {
    '@typescript-eslint/ban-types': ['error', {
      types: {
        'Array': 'Use [] instead',
        'Object': 'Use {} instead',
        'Component': 'Use function component instead',
        'PureComponent': 'Use function component instead',
        'React.Component': 'Use function component instead',
        'React.PureComponent': 'Use function component instead',
        'String': 'Use string instead'
      }
    }],
    '@typescript-eslint/explicit-function-return-type': 'error',
    '@typescript-eslint/generic-type-naming': [
      'error',
      '^[A-Z][A-Z0-9]{0,9}$'
    ],
    '@typescript-eslint/member-ordering': 'error',
    '@typescript-eslint/consistent-type-assertions': 'error',
    '@typescript-eslint/no-extraneous-class': 'error',
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
    '@typescript-eslint/promise-function-async': 'error',
    '@typescript-eslint/type-annotation-spacing': 'error',
  }
}
