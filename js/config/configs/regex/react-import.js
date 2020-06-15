//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/invalid': [
      'error', [
        {
          message: 'Use \'import * as React from "react"\', instead of \'import React from "react"\'',
          regex: 'import[ ]+React[ ]+from[ ]+["\']react["\']',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
