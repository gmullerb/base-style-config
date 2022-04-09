//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-mutable-public-fields')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-mutable-public-fields': [
      'error', [
        {
          message: 'public class fields should be readonly',
          regex: '\\bpublic\\b\\s+(static\\s+|(?!static)\\s*)(?!async)\\s*(?!readonly)[\\$_A-Za-z]+\\b(?!\\s*\\()',
          files: {
            inspect: '\\.tsx?'
          }
        }
      ]
    ]
  }
}
