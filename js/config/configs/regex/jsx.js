//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'JSX code should start in its own line',
          regex: '(\\(\\<)|(return\\s+\\<)',
          files: {
            inspect: '\\.[jt]sx'
          }
        },
        {
          message: 'JSX code should end at its own line',
          regex: '(\\<\\/\\w*\\>|\\/\\>)\\)',
          files: {
            inspect: '\\.[jt]sx'
          }
        },
        {
          message: 'One JSX tag per line, except for <span>, <a> or <i>',
          regex: '\\>(?=.*\\<(?!(\\/|span\\>|a\\>|i\\>)))',
          files: {
            inspect: '\\.[jt]sx'
          }
        },
        {
          message: 'Opening brace should be in a new line',
          regex: '\\>(?=[ \\t]*{\\s*$)',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
