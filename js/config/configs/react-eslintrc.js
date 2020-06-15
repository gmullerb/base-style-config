//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  extends: [ 'plugin:react/recommended' ],
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    }
  },
  plugins: [ 'react' ],
  rules: {
    'react/jsx-first-prop-new-line': 'error',
    'react/jsx-max-props-per-line': [
      'error',
      {
        maximum: 4
      }
    ],
    'react/jsx-sort-props': [
      'error', {
        callbacksLast: true,
        noSortAlphabetically: true
      }
    ],
    'react/no-multi-comp': 'error',
    'react/no-this-in-sfc': 'error',
    'react/no-unsafe': 'error',
    'react/no-unused-prop-types': 'error',
    'react/no-unused-state': 'error',
    'react/prop-types': 'off'
  },
  settings:  {
    react:  {
      version: 'detect'
    }
  }
}
