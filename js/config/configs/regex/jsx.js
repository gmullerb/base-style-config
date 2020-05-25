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
          message: 'One JSX tag per line',
          regex: '\\>(?=.*\\<(?!\\/))',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
