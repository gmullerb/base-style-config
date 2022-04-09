//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse-html-entities')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse-html-entities': [
      'error', [
        {
          message: 'Do not use HTML Entities, Use UTF-8 characters instead',
          regex: '&(#\\d{1,5})|(#x[\\da-fA-F]{1,5})|([a-zA-Z0-9]{1,8});',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
