//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Opening brace should be in a new line',
          regex: '(((^[ \\t]*|\\>)\\<\\w+|[ \\t]*^)\\>|\\/\\>)(?=[ \\t]*\{[ \\t]*$)',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
