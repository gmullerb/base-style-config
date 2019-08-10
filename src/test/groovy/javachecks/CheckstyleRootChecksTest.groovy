//  Copyright (c) 2019 Gonzalo Müller Bravo.
//  Licensed under the MIT License (MIT), see LICENSE.txt
package javachecks

import util.CheckstyleTestUtils

import groovy.transform.CompileStatic
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.Node

import java.util.function.Predicate
import java.util.regex.Pattern

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertFalse

@CompileStatic
class CheckstyleRootChecksTest {
  private static GPathResult checkerChildrenNodes

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
    checkerChildrenNodes = checkerNode.children()
  }

  @Nested
  private class NameOfTestsMustStartWithShouldTest {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult nameOfTestsMustStartWithShouldCheckstyle = CheckstyleTestUtils
        .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'NameOfTestsMustStartWithShould')
      checkPattern = Pattern.compile(
        CheckstyleTestUtils.obtainChildValue(nameOfTestsMustStartWithShouldCheckstyle, 'format'),
        Pattern.MULTILINE).asPredicate()
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForJUnit() {
      assertTrue(checkPattern.test('\u0040Test public void someTest'))
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsStartWithShouldForJUnit() {
      assertFalse(checkPattern.test('\u0040Test public void shouldTest'))
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForJUnit4() {
      assertTrue(checkPattern.test('\u0040org.junit.Test public void someTest'))
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsStartWithShouldForJUnit4() {
      assertFalse(checkPattern.test('\u0040org.junit.Test public void shouldTest'))
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForJUnit5() {
      assertTrue(checkPattern.test('\u0040org.junit.jupiter.api.Test public void someTest'))
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsStartWithShouldForJUnit5() {
      assertFalse(checkPattern.test('\u0040org.junit.jupiter.api.Test public void shouldTest'))
    }

    @Test
    public void shouldFindPatternWhenNameOfTestsDoesNotStartWithShouldForTestNg() {
      assertTrue(checkPattern.test('\u0040org.testng.annotations.Test public void someTest'))
    }

    @Test
    public void shouldNotFindPatternWhenNameOfTestsStartWithShouldForTestNg() {
      assertFalse(checkPattern.test('\u0040org.testng.annotations.Test public void shouldTest'))
    }
  }

  @Nested
  private class UseOnlyDoFamilyMethodsWhenMockingTest {
    private Predicate<String> checkPattern

    @BeforeEach
    public void beforeEach() {
      final GPathResult useOnlyDoFamilyMethodsWhenMocking = CheckstyleTestUtils
        .findNodeByTypeAndId(checkerChildrenNodes, 'RegexpMultiline', 'UseOnlyDoFamilyMethodsWhenMocking')
      checkPattern = Pattern.compile(
        CheckstyleTestUtils.obtainChildValue(useOnlyDoFamilyMethodsWhenMocking, 'format'),
        Pattern.MULTILINE).asPredicate()
    }

    @Test
    public void shouldFindPatternWhenUsingMockitoWhen() {
      assertTrue(checkPattern.test('\u004Dockito.when'))
    }

    @Test
    public void shouldFindPatternWhenUsingBDDMockito() {
      assertTrue(checkPattern.test('\u006Frg.mockito.BDDMockito'))
    }

    @Test
    public void shouldNotFindPatternWhenUsingMockitoDoReturn() {
      assertFalse(checkPattern.test('\u004Dockito.doReturn'))
    }
  }
}
