//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
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
