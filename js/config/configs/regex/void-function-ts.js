//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse () => void')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse () => void': [
      'error', [
        {
          message: 'Use VoidFunction, instead of () => void',
          regex: '\\([ ]*\\)[ ]*=>[ ]*void',
          replacement: 'VoidFunction',
          files: {
            inspect: '\\.tsx?'
          }
        }
      ]
    ]
  }
}
