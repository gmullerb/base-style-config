//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  plugins: ['import'],
  parserOptions: {
    sourceType: 'module'
  },
  rules: {
    'import/default': 'error',
    'import/export': 'error',
    'import/first': 'error',
    'import/newline-after-import': 'error',
    'import/no-default-export': 'error',
    'import/no-mutable-exports': 'error',
    'import/no-unused-modules': 'error',
    'import/no-anonymous-default-export': 'error'
  }
}
