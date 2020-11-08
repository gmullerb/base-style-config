//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt

module.exports = {
  rules: {
    'id-match': [
      'error',
      '^\\$$|^\\$\\$$|^at$|^by$|^_id$|^id$|^in$|^k$|^K$|^of$|^on$|^to$|^up$|^T[A-Z]?[0-9]{0,2}$|^[_\\$]?([_\\$]?[A-Z0-9]{1})*[_\\$]?$|(\\b(HTML|SVG|XML|JSON|URL|URI|XSL|IDB|RTC|UI|VR|DOM|CSS|RGB)\\w*)|(\\w*(HTML|SVG|XML|JSON|URL|URI|XSL|IDB|RTC|UI|VR|DOM|CSS|RGB)\\b)|^[A-Z]([_\\$]?[A-Z0-9]{1}){2}$|^_?[a-z]([A-Z][a-z0-9]|[a-z0-9][A-Z]|[a-z0-9]{2}){1}([A-Z]?[a-z0-9]{1})*[a-zA-Z0-9]?$|^[A-Z][a-z0-9][A-Z]([a-z0-9]([A-Z]?[a-z0-9]{1})*[a-zA-Z0-9]?)?$|^[A-Z][a-z0-9]{2}([A-Z]?[a-z0-9]{1})*[a-zA-Z0-9]?$',
      {
        properties: true,
        onlyDeclarations: true,
        ignoreDestructuring: true
      }
    ]
  }
}
