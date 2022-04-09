//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse starting jsx line with no-jsx')
addRegexRuleName('disuse ending jsx line with no-jsx')
addRegexRuleName('disuse-several-tags-per-line-in-jsx')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse starting jsx line with no-jsx': [
      'error', [
        {
          message: 'JSX code should start in its own line',
          regex: '(\\(\\<)|(return\\s+\\<)',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ],
    'regex/disuse ending jsx line with no-jsx': [
      'error', [
        {
          message: 'JSX code should end at its own line',
          regex: '(\\<\\/\\w*\\>|\\/\\>)\\)',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ],
    'regex/disuse-several-tags-per-line-in-jsx': [
      'error', [
        {
          message: 'One JSX tag per line, except when containing <span>, <a>, <img>, <i> or <FormattedMessage>',
          regex: '<.*>(.*<(?!\\/)(?!(\\/?span|\\/?a|\\/?i|\\/?img|\\/?FormattedMessage))).*<\\/.*>',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
