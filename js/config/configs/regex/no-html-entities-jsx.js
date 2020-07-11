//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Do not use HTML Entities, Use UTF-8 instead',
          regex: '&(#\\d{1,5}|[a-zA-Z0-9]{1,8});',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
