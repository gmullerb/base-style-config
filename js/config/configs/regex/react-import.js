//  Copyright (c) 2020 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
const { addRegexRuleName } = require('eslint-plugin-regex')

addRegexRuleName('disuse "import React from \'react\'"')

module.exports = {
  plugins: [ 'regex' ],
  rules: {
    'regex/disuse "import React from \'react\'"': [
      'error', [
        {
          message: 'Use "import * as React from \'react\'", instead of "import React from \'react\'"',
          regex: 'import[ ]+React[ ]+from[ ]+["\']react["\']',
          replacement: 'import React from \'react\'',
          files: {
            inspect: '\\.[jt]sx'
          }
        }
      ]
    ]
  }
}
