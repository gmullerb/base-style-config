//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
package javachecks

import util.CheckstyleTestUtils

import groovy.transform.CompileStatic
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.Node
import groovy.util.slurpersupport.NodeChild

import java.util.function.Predicate
import java.util.regex.Pattern

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertFalse

@CompileStatic
class CheckstyleTreeWalkerChecksTest {
  private static GPathResult treeWalkerChildrenNodes

  @BeforeAll
  public static void beforeAll() {
    final XmlSlurper reader = new XmlSlurper()
    reader.setFeature('http://apache.org/xml/features/disallow-doctype-decl', false)
    reader.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, 'all')
    final GPathResult rootNodes = reader.parse('src/config/java/coding-checks.xml')
    final GPathResult checkerNode = rootNodes.find { Node node ->
      node
        .attributes()
        .get('name') == 'Checker'
    }
    final GPathResult checkerChildrenNodes = checkerNode.children()
    final GPathResult treeWalkerNode = checkerChildrenNodes.find { NodeChild node ->
      node
        .attributes()
        .get('name') == 'TreeWalker'
    }
    treeWalkerChildrenNodes = treeWalkerNode.children()
  }

  @Nested
  private class CallOnlyOneMethodPerLineForChainedCallTest {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult callOnlyOneMethodPerLineForChainedCall = CheckstyleTestUtils
        .findNodeByTypeAndId(treeWalkerChildrenNodes, 'RegexpSinglelineJava', 'CallOnlyOneMethodPerLineForChainedCall')
      checkPattern = Pattern
        .compile(CheckstyleTestUtils.obtainChildValue(callOnlyOneMethodPerLineForChainedCall, 'format'))
        .asPredicate()
    }

    @Test
    public void shouldFindPatternWhenInstantiatingAndCallingAnyMethod() {
      assertTrue(checkPattern.test('new Class()\u002E.some()'))
    }

    @Test
    public void shouldFindPatternWhenChainingMethods() {
      assertTrue(checkPattern.test('object.some()\u002Esome()'))
    }

    @Test
    public void shouldFindPatternWhenCastAtBeginning() {
      assertTrue(checkPattern.test('((Cast) object)\u002Esome()\u002Esome()'))
    }

    @Test
    public void shouldFindPatternWhenCastInside() {
      assertTrue(checkPattern.test('object.some((Cast) some)\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenInstantiating() {
      assertFalse(checkPattern.test('new Class()'))
    }

    @Test
    public void shouldNotFindPatternWhenNotChaining() {
      assertFalse(checkPattern.test('object\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenCallsAreSplit() {
      assertFalse(checkPattern.test(')\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenCallsAreSplitWithArgument() {
      assertFalse(checkPattern.test('object)\u002Esome()'))
    }

    @Test
    public void shouldNotFindPatternWhenContainsAnyCast() {
      assertFalse(checkPattern.test('object)\u002Esome((Cast) some)'))
    }
  }

  @Nested
  private class UseMultilineTernaryOperatorTest {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult useMultilineTernaryOperator = CheckstyleTestUtils
        .findNodeByTypeAndId(treeWalkerChildrenNodes, 'RegexpSinglelineJava', 'UseMultilineTernaryOperator')
      checkPattern = Pattern
        .compile(CheckstyleTestUtils.obtainChildValue(useMultilineTernaryOperator, 'format'))
        .asPredicate()
    }

    @Test
    public void shouldFindPatternWhenTernaryOperatorInSameLine() {
      assertTrue(checkPattern.test('? some() : some()'))
    }

    @Test
    public void shouldNotFindPatternWhenTernaryOperatorNotInSameLine() {
      assertFalse(checkPattern.test('? some() '))
    }

    @Test
    public void shouldNotFindPatternWhenUsingGenericMethods() {
      assertFalse(checkPattern.test('<?> type some() {'))
    }

    @Test
    public void shouldNotFindPatternWhenUsingGenericClasses() {
      assertFalse(checkPattern.test('class Some<?> {'))
    }

    @Test
    public void shouldNotFindPatternWhenTernaryOperatorInsideAnyString() {
      assertFalse(checkPattern.test('"? some() : some() "'))
    }

    @Test
    public void shouldNotFindPatternWhenUsingMethodReference() {
      assertFalse(checkPattern.test('Some::method'))
    }

    @Test
    public void shouldNotFindPatternWhenColonInsideJsonString() {
      assertFalse(checkPattern.test('{\"identifier\":\"theId\",\"modified\":\"2019-05-01T15:12:12.123142Z\",\"uri\":\"theUri\"}'))
    }
  }

  @Nested
  private class UseOnlyMockOrSpyPrefixOnTestFiles {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult useOnlyMockOrSpyPrefixOnTestFiles = CheckstyleTestUtils
        .findNodeByTypeAndId(treeWalkerChildrenNodes, 'RegexpSinglelineJava', 'UseOnlyMockOrSpyPrefixOnTestFiles')
      checkPattern = Pattern
        .compile(CheckstyleTestUtils.obtainChildValue(useOnlyMockOrSpyPrefixOnTestFiles, 'format'))
        .asPredicate()
    }

    @Test
    public void shouldFindPatternWhenUsingMockPrefix() {
      assertTrue(checkPattern.test(' mockSome'))
    }

    @Test
    public void shouldFindPatternWhenUsingSpyPrefix() {
      assertTrue(checkPattern.test(' spySome'))
    }

    @Test
    public void shouldNotFindPatternWhenUsingMockInsideAnyWord() {
      assertFalse(checkPattern.test(' theMock '))
    }

    @Test
    public void shouldNotFindPatternWhenUsingSpyInsideAnyWord() {
      assertFalse(checkPattern.test(' theSpy '))
    }
  }
}
