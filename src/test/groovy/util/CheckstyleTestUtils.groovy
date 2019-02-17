//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
package util

import groovy.transform.CompileStatic
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.NodeChild

@CompileStatic
class CheckstyleTestUtils {
  public static final GPathResult findNodeByTypeAndId(final GPathResult sourceNode, final String type, final String id) {
    final GPathResult nodes = sourceNode.findAll { final NodeChild node ->
      type == node
        .attributes()
        .get('name')
    }

    nodes.find { NodeChild node ->
      checkId(node, id)
    }

  }

  public static final boolean checkId(final GPathResult node, final String id) {
    node
      .children()
      .find { final NodeChild child ->
        'id' == child
        .attributes()
        .get('name')
        &&
        id == child
        .attributes()
        .get('value')
    }
  }

  public static final String obtainChildValue(final GPathResult node, final String name) {
    final GPathResult result = node.children().find { final NodeChild child ->
      name == child
        .attributes()
        .get('name')
    }
    result.isEmpty()
      ? null
      : ((NodeChild) result)
        .attributes()
        .get('value')
  }
}
