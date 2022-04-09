//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-opening-brace-in-new-line-in-jsx')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-opening-brace-in-new-line-in-jsx': [
      'error', [
        {
          message: 'Use Stroustrup opening brace (i.e. opening brace in the same line)',
          regex: '\\<[^\\/]*\\>[\\r\\n\\v\\f]+[ \\t]*\\{\\s*$',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
