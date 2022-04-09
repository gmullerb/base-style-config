//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-double-quotes-in-jsx')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-double-quotes-in-jsx': [
      'error', [
        {
          message: 'Do not use ", Use \' instead',
          regex: '"(?!.*\')',
          replacement: '\'',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
