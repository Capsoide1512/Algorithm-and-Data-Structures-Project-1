package it.unicam.cs.asdl2324.mp1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;

/**
 * Classe di test per LinkedListDisjointSets
 * 
 * @author Luca Tesei
 *
 */
class LinkedListDisjointSetsTest {

    @Test
    final void testLinkedListDisjointSets() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertTrue(ds.getCurrentRepresentatives().isEmpty());
    }

    @Test
    final void testIsPresent() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertTrue(ds.isPresent(e1));
        assertTrue(e1.getRef1() != null);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        assertFalse(ds.isPresent(e2));
        assertTrue(e2.getRef1() == null);
    }

    @Test
    final void testIsPresentAfterUnion() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.union(e1, e2);
        assertTrue(ds.isPresent(e1));
        assertTrue(ds.isPresent(e2));
        assertTrue(ds.isPresent(e3));
        assertTrue(ds.isPresent(e4));
        assertFalse(ds.isPresent(e5));
        ds.union(e2, e3);
        assertTrue(ds.isPresent(e1));
        assertTrue(ds.isPresent(e2));
        assertTrue(ds.isPresent(e3));
        assertTrue(ds.isPresent(e4));
        assertTrue(e4.getRef1() != null);
        assertFalse(ds.isPresent(e5));
        ds.union(e1, e4);
        assertTrue(ds.isPresent(e1));
        assertTrue(e1.getRef1() != null);
        assertTrue(ds.isPresent(e2));
        assertTrue(ds.isPresent(e3));
        assertTrue(ds.isPresent(e4));
        assertFalse(ds.isPresent(e5));
    }

    @Test
    final void testMakeSet() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertTrue(e1.getRef1() == e1);
        assertTrue(e1.getRef2() == null);
        assertTrue(e1.getNumber() == 1);
        assertTrue(ds.findSet(e1) == e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        assertTrue(e2.getRef1() == e2);
        assertTrue(e2.getRef2() == null);
        assertTrue(e2.getNumber() == 1);
        assertTrue(ds.findSet(e2) == e2);
    }

    @Test
    final void testMakeSetExceptions() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertThrows(NullPointerException.class, () -> ds.makeSet(null));
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertThrows(NullPointerException.class, () -> ds.makeSet(null));
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        assertThrows(IllegalArgumentException.class, () -> ds.makeSet(e1));
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        ds.union(e1, e3);
        assertThrows(IllegalArgumentException.class, () -> ds.makeSet(e2));
        assertThrows(IllegalArgumentException.class, () -> ds.makeSet(e3));
        ds.union(e1, e2);
        assertThrows(IllegalArgumentException.class, () -> ds.makeSet(e2));
    }

    @Test
    final void testMakeSetRepresentatives() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertTrue(ds.findSet(e1) == e1);
        assertTrue(e1.getRef1() == e1);
        assertTrue(e1.getRef2() == null);
        assertTrue(ds.getCardinalityOfSetContaining(e1) == 1);
        assertTrue(e1.getNumber() == 1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        assertTrue(ds.findSet(e2) == e2);
        assertTrue(e2.getRef1() == e2);
        assertTrue(e2.getRef2() == null);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 1);
        assertTrue(e2.getNumber() == 1);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        Set<DisjointSetElement> elements = ds.getCurrentRepresentatives();
        assertTrue(elements.size() == 5);
        Set<DisjointSetElement> expectedElements = new HashSet<DisjointSetElement>();
        expectedElements.add(e1);
        expectedElements.add(e2);
        expectedElements.add(e3);
        expectedElements.add(e4);
        expectedElements.add(e5);
        assertEquals(expectedElements, elements);
    }

    @Test
    final void testFindSetExceptions() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertThrows(NullPointerException.class, () -> ds.findSet(null));
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertThrows(NullPointerException.class, () -> ds.findSet(null));
        assertTrue(ds.findSet(e1) == e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        assertThrows(IllegalArgumentException.class, () -> ds.findSet(e2));
        ds.makeSet(e2);
        assertTrue(ds.findSet(e2) == e2);
    }

    @Test
    final void testFindSetSingletons() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertTrue(ds.findSet(e1) == e1);
        assertTrue(e1.getRef1() == e1);
        assertTrue(e1.getRef2() == null);
        assertTrue(e1.getNumber() == 1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        assertTrue(ds.findSet(e2) == e2);
        assertTrue(e2.getRef1() == e2);
        assertTrue(e2.getRef2() == null);
        assertTrue(e2.getNumber() == 1);
    }

    @Test
    final void testFindSetAfterUnion() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        ds.union(e1, e2);
        assertTrue(ds.findSet(e2) == ds.findSet(e1));
        assertTrue(e1.getRef1() == e2.getRef1());
        assertTrue(ds.findSet(e1).getRef2() != null);
        assertTrue(ds.findSet(e2).getNumber() == 2);
        ds.union(e3, e4);
        assertTrue(ds.findSet(e3) == ds.findSet(e4));
        ds.union(e5, e3);
        assertTrue(ds.findSet(e4) == ds.findSet(e5));
        assertTrue(ds.findSet(e3) == ds.findSet(e4));
        ds.union(e1, e5);
        assertTrue(ds.findSet(e1) == ds.findSet(e2));
        assertTrue(ds.findSet(e2) == ds.findSet(e3));
        assertTrue(ds.findSet(e3) == ds.findSet(e4));
        assertTrue(ds.findSet(e4) == ds.findSet(e5));
        assertTrue(e1.getRef1() == e4.getRef1());
        assertTrue(e2.getRef1().getRef2() != null);
        assertTrue(e3.getRef1().getNumber() == 5);
    }

    @Test
    final void testUnionException() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertThrows(NullPointerException.class, () -> ds.union(null, null));
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertThrows(NullPointerException.class, () -> ds.union(e1, null));
        assertThrows(NullPointerException.class, () -> ds.union(null, e1));
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        assertThrows(IllegalArgumentException.class, () -> ds.union(e1, e3));
        assertThrows(IllegalArgumentException.class, () -> ds.union(e3, e2));
        ds.union(e1, e2);
        assertThrows(IllegalArgumentException.class, () -> ds.union(e3, e1));
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        assertThrows(IllegalArgumentException.class, () -> ds.union(e3, e4));
        assertThrows(IllegalArgumentException.class, () -> ds.union(e1, e4));
    }

    @Test
    final void testUnionSet() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        ds.union(e1, e2);
        assertTrue(ds.findSet(e1) == ds.findSet(e2));
        assertFalse(ds.findSet(e1) == ds.findSet(e3));
        assertFalse(ds.findSet(e2) == ds.findSet(e3));
        assertTrue(ds.getCurrentRepresentatives().size() == 4);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 2);
        assertTrue(ds.getCardinalityOfSetContaining(e1) == 2);
        ds.union(e2, e1);
        assertTrue(ds.findSet(e1) == ds.findSet(e2));
        assertFalse(ds.findSet(e1) == ds.findSet(e3));
        assertFalse(ds.findSet(e2) == ds.findSet(e3));
        assertTrue(ds.getCurrentRepresentatives().size() == 4);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 2);
        assertTrue(ds.getCardinalityOfSetContaining(e1) == 2);
        ds.union(e1, e3);
        assertTrue(e1.getRef1() == e2.getRef1());
        assertTrue(e2.getRef1() == e3.getRef1());
        assertTrue(ds.getCurrentRepresentatives().size() == 3);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 3);
        assertTrue(e1.getRef1().getNumber() == 3);
        assertTrue(ds.getCardinalityOfSetContaining(e3) == 3);
        ds.union(e4, e5);
        assertTrue(ds.findSet(e4) == ds.findSet(e5));
        assertTrue(ds.getCurrentRepresentatives().size() == 2);
        assertTrue(ds.getCardinalityOfSetContaining(e4) == 2);
        assertTrue(ds.getCardinalityOfSetContaining(e5) == 2);
        ds.union(e4, e2);
        assertTrue(ds.findSet(e1) == ds.findSet(e2));
        assertTrue(ds.findSet(e2) == ds.findSet(e3));
        assertTrue(e3.getRef1() == e4.getRef1());
        assertTrue(ds.findSet(e4) == ds.findSet(e5));
        assertTrue(ds.getCurrentRepresentatives().size() == 1);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 5);
        assertTrue(e1.getRef1().getNumber() == 5);
        assertTrue(ds.getCardinalityOfSetContaining(e5) == 5);
        assertTrue(e4.getRef1().getNumber() == 5);
        assertTrue(ds.getCardinalityOfSetContaining(e3) == 5);
    }

    @Test
    final void testUnionNewRepresentativesDifferentNumberOfElements1() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        ds.union(e1, e2);
        DisjointSetElement rep1 = ds.findSet(e2);
        ds.union(e3, e1);
        assertTrue(ds.findSet(e1) == rep1);
        assertTrue(e2.getRef1() == rep1);
        assertTrue(ds.findSet(e3) == rep1);
        ds.union(e4, e5);
        ds.union(e5, e1);
        assertTrue(ds.findSet(e1) == rep1);
        assertTrue(ds.findSet(e2) == rep1);
        assertTrue(e3.getRef1() == rep1);
        assertTrue(ds.findSet(e4) == rep1);
        assertTrue(e5.getRef1() == rep1);
        MyIntLinkedListDisjointSetElement e6 = new MyIntLinkedListDisjointSetElement(
                6);
        ds.makeSet(e6);
        ds.union(e6, e2);
        assertTrue(ds.findSet(e1) == rep1);
        assertTrue(e2.getRef1() == rep1);
        assertTrue(ds.findSet(e3) == rep1);
        assertTrue(ds.findSet(e4) == rep1);
        assertTrue(e5.getRef1() == rep1);
        assertTrue(ds.findSet(e6) == rep1);
    }

    @Test
    final void testUnionNewRepresentativesDifferentNumberOfElements2() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        ds.union(e1, e2);
        DisjointSetElement rep1 = ds.findSet(e2);
        ds.union(e3, e1);
        assertTrue(e1.getRef1() == rep1);
        assertTrue(e2.getRef1() == rep1);
        assertTrue(e3.getRef1() == rep1);
        MyIntLinkedListDisjointSetElement e6 = new MyIntLinkedListDisjointSetElement(
                6);
        ds.makeSet(e6);
        MyIntLinkedListDisjointSetElement e7 = new MyIntLinkedListDisjointSetElement(
                7);
        ds.makeSet(e7);
        ds.union(e4, e5);
        ds.union(e5, e6);
        ds.union(e6, e7);
        DisjointSetElement rep2 = e7.getRef1();
        assertTrue(ds.findSet(e4) == rep2);
        assertTrue(ds.findSet(e5) == rep2);
        assertTrue(e6.getRef1() == rep2);
        assertTrue(ds.findSet(e7) == rep2);
        //
        assertTrue(rep1.getNumber() == 3);
        assertTrue(rep2.getNumber() == 4);
        ds.union(rep1, rep2);
        assertTrue(e1.getRef1().getNumber() == 7);
        assertTrue(e3.getRef1().getNumber() == 7);
        assertTrue(e6.getRef1().getNumber() == 7);
        assertTrue(ds.findSet(e1) == rep2);
        assertTrue(ds.findSet(e2) == rep2);
        assertTrue(ds.findSet(e3) == rep2);
        assertTrue(ds.findSet(e4) == rep2);
        assertTrue(ds.findSet(e5) == rep2);
        assertTrue(ds.findSet(e6) == rep2);
        assertTrue(ds.findSet(e7) == rep2);
    }

    @Test
    final void testUnionNewRepresentativesEqualNumberoOfElements() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        ds.union(e1, e2);
        assertTrue(ds.findSet(e1) == e1);
        assertTrue(ds.findSet(e2) == e1);
        ds.union(e4, e3);
        assertTrue(ds.findSet(e3) == e4);
        assertTrue(e4.getRef1() == e4);
        ds.union(e3, e2);
        assertTrue(ds.findSet(e1) == e4);
        assertTrue(e2.getRef1() == e4);
        assertTrue(e3.getRef1() == e4);
        assertTrue(ds.findSet(e4) == e4);
    }

    @Test
    final void testGetCurrentRepresentatives() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertTrue(ds.getCurrentRepresentatives().isEmpty());
        Set<DisjointSetElement> controlSet = new HashSet<DisjointSetElement>();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        controlSet.add(e1);
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        controlSet.add(e2);
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        controlSet.add(e3);
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        controlSet.add(e4);
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        controlSet.add(e5);
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        controlSet.remove(e1);
        controlSet.remove(e5);
        ds.union(e1, e5);
        controlSet.add(e5.getRef1());
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        controlSet.remove(e2);
        controlSet.remove(e1.getRef1());
        ds.union(e1, e2);
        controlSet.add(e2.getRef1());
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        controlSet.remove(e3);
        controlSet.remove(e4);
        ds.union(e4, e3);
        controlSet.add(e3.getRef1());
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
        controlSet.remove(e5.getRef1());
        controlSet.remove(e4.getRef1());
        ds.union(e1, e3);
        controlSet.add(e3.getRef1());
        assertTrue(ds.getCurrentRepresentatives().equals(controlSet));
    }

    @Test
    final void testGetCurrentElementsOfSetContainingExceptions() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertThrows(NullPointerException.class,
                () -> ds.getCurrentElementsOfSetContaining(null));
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertThrows(NullPointerException.class,
                () -> ds.getCurrentElementsOfSetContaining(null));
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        assertThrows(IllegalArgumentException.class,
                () -> ds.getCurrentElementsOfSetContaining(e2));
        ds.makeSet(e2);
        ds.union(e1, e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        assertThrows(IllegalArgumentException.class,
                () -> ds.getCurrentElementsOfSetContaining(e3));
    }

    @Test
    final void testGetCurrentElementsOfSetContaining() {
        DisjointSets ds = new LinkedListDisjointSets();
        Set<DisjointSetElement> controlSet = new HashSet<DisjointSetElement>();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        controlSet.add(e1);
        assertTrue(ds.getCurrentElementsOfSetContaining(e1).equals(controlSet));
        assertTrue(ds.getCurrentElementsOfSetContaining(e1).contains(e1));
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        assertFalse(ds.getCurrentElementsOfSetContaining(e1).contains(e2));
        assertTrue(ds.getCurrentElementsOfSetContaining(e1).size() == 1);
        ds.makeSet(e2);
        ds.union(e1, e2);
        controlSet.add(e2);
        assertTrue(ds.getCurrentElementsOfSetContaining(e1).equals(controlSet));
        assertTrue(ds.getCurrentElementsOfSetContaining(e2).equals(controlSet));
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.makeSet(e3);
        ds.union(e1, e3);
        controlSet.add(e3);
        assertTrue(ds.getCurrentElementsOfSetContaining(e1).equals(controlSet));
        assertTrue(ds.getCurrentElementsOfSetContaining(e2).equals(controlSet));
        assertTrue(ds.getCurrentElementsOfSetContaining(e3).equals(controlSet));
    }

    @Test
    final void testGetCardinalityOfSetContainingExceptions() {
        DisjointSets ds = new LinkedListDisjointSets();
        assertThrows(NullPointerException.class,
                () -> ds.getCardinalityOfSetContaining(null));
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertThrows(NullPointerException.class,
                () -> ds.getCardinalityOfSetContaining(null));
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        assertThrows(IllegalArgumentException.class,
                () -> ds.getCardinalityOfSetContaining(e2));
        ds.makeSet(e2);
        ds.union(e1, e2);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        assertThrows(IllegalArgumentException.class,
                () -> ds.getCardinalityOfSetContaining(e3));
    }

    @Test
    final void testGetCardinalityOfSetContaining() {
        DisjointSets ds = new LinkedListDisjointSets();
        MyIntLinkedListDisjointSetElement e1 = new MyIntLinkedListDisjointSetElement(
                1);
        ds.makeSet(e1);
        assertTrue(ds.getCardinalityOfSetContaining(e1) == 1);
        assertTrue(e1.getRef1().getNumber() == 1);
        MyIntLinkedListDisjointSetElement e2 = new MyIntLinkedListDisjointSetElement(
                2);
        ds.makeSet(e2);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 1);
        assertTrue(e2.getRef1().getNumber() == 1);
        MyIntLinkedListDisjointSetElement e3 = new MyIntLinkedListDisjointSetElement(
                3);
        ds.union(e1, e2);
        assertTrue(ds.getCardinalityOfSetContaining(e1) == 2);
        assertTrue(e1.getRef1().getNumber() == 2);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 2);
        assertTrue(e2.getRef1().getNumber() == 2);
        ds.makeSet(e3);
        MyIntLinkedListDisjointSetElement e4 = new MyIntLinkedListDisjointSetElement(
                4);
        ds.makeSet(e4);
        MyIntLinkedListDisjointSetElement e5 = new MyIntLinkedListDisjointSetElement(
                5);
        ds.makeSet(e5);
        ds.union(e3, e5);
        ds.union(e3, e4);
        assertTrue(ds.getCardinalityOfSetContaining(e3) == 3);
        assertTrue(e3.getRef1().getNumber() == 3);
        assertTrue(ds.getCardinalityOfSetContaining(e4) == 3);
        assertTrue(e4.getRef1().getNumber() == 3);
        assertTrue(ds.getCardinalityOfSetContaining(e5) == 3);
        assertTrue(e5.getRef1().getNumber() == 3);
        ds.union(e1, e3);
        assertTrue(ds.getCardinalityOfSetContaining(e1) == 5);
        assertTrue(e1.getRef1().getNumber() == 5);
        assertTrue(ds.getCardinalityOfSetContaining(e2) == 5);
        assertTrue(e2.getRef1().getNumber() == 5);
        assertTrue(ds.getCardinalityOfSetContaining(e3) == 5);
        assertTrue(e3.getRef1().getNumber() == 5);
        assertTrue(ds.getCardinalityOfSetContaining(e4) == 5);
        assertTrue(e4.getRef1().getNumber() == 5);
        assertTrue(ds.getCardinalityOfSetContaining(e5) == 5);
        assertTrue(e5.getRef1().getNumber() == 5);
    }

}
