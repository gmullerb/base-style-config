//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
package front

import groovy.transform.CompileStatic
import groovy.json.JsonParserType
import groovy.json.JsonSlurper

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertNotNull

@CompileStatic
class LintersRulesTest {

  @Test
  public void shouldPassedWhenEsLintRcIsWellFormed() {
    assertNotNull(new JsonSlurper()
        .setType(JsonParserType.INDEX_OVERLAY)
        .parse(new File('src/config/front/.eslintrc.json')))
  }

  @Test
  public void shouldPassedWhenStyleLintRcIsWellFormed() {
    assertNotNull(new JsonSlurper()
        .setType(JsonParserType.INDEX_OVERLAY)
        .parse(new File('src/config/front/.stylelintrc.json')))
  }
}
