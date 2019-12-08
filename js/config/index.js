//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  configs: {
    'js-rules': require('./configs/eslintrc.js'),
    'import-rules': require('./configs/import-eslintrc.js'),
    'react-rules': require('./configs/react-eslintrc.js'),
    'react-with-hooks-rules': require('./configs/react-with-hooks-eslintrc.js'),
    'typescript-rules': require('./configs/typescript-eslintrc.js'),
  },
}
