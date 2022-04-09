//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-same-line-opening-brace-in-jsx')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-same-line-opening-brace-in-jsx': [
      'error', [
        {
          message: 'Use Allman opening brace (i.e. opening brace in a new line)',
          regex: '(((^[ \\t]*|\\>)\\<\\w+|[ \\t]*^)\\>|\\/\\>)(?=[ \\t]*\{[ \\t]*$)',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
