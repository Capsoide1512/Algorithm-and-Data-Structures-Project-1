package it.unicam.cs.asdl2324.mp1;

/**
 * Una semplice classe che implementa l'interface {@code DisjointSetElement} i
 * cui elementi sono interi e che è adatta per essere usata da una
 * implementazione dell'interface {@code DisjointSets} che usa liste
 * concatenate.
 * 
 * @author Luca Tesei
 *
 */
public class MyIntLinkedListDisjointSetElement implements DisjointSetElement {

    /*
     * L'intero che rappresenta questo elemento
     */
    private final int value;

    /*
     * Puntatore all'elemento rappresentante, cioè il primo della lista
     * concatenata che rappresenta l'insieme disgiunto di questo elemento
     */
    private DisjointSetElement representative;

    /*
     * Puntatore al prossimo elemento nella lista concatenata che rappresenta
     * l'insieme disgiunto di cui questo elemento fa parte
     */
    private DisjointSetElement nextElement;

    /*
     * Numero di elementi di questo insieme disgiunto, diverso da zero solo se
     * questo elemento è il rappresentante, altrimenti non viene usato
     */
    private int size;

    /**
     * Crea un nuovo elemento di un insieme disgiunto.
     * 
     * @param value
     *                  il numero associato al nuovo elemento
     */
    public MyIntLinkedListDisjointSetElement(int value) {
        this.value = value;
        /*
         * Metto i campi relativi al DisjointSet ai valori di default. Nelle API
         * dell'interface LinkedListDisjointSets è indicato che tali valori sono
         * quelli appropriati da mettere nel costruttore della classe che
         * implementa l'interface. Indicano che l'elemento appena creato non è
         * stato ancora inserito in nessun disjoint set.
         */
        this.representative = null;
        this.nextElement = null;
        this.size = 0;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    @Override
    public DisjointSetElement getRef1() {
        return this.representative;
    }

    @Override
    public void setRef1(DisjointSetElement e) {
        this.representative = e;
    }

    @Override
    public DisjointSetElement getRef2() {
        return this.nextElement;
    }

    @Override
    public void setRef2(DisjointSetElement e) {
        this.nextElement = e;
    }

    @Override
    public int getNumber() {
        return this.size;
    }

    @Override
    public void setNumber(int n) {
        this.size = n;
    }

    /*
     * Si usa solo il value per l'hashcode poiché l'uso di
     * representative.hashcode() potrebbe produrre delle chiamate ricorsive che
     * non terminano.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    /*
     * L'uguaglianza è definita in base al rappresentante e al valore, utile per
     * scrivere i test JUnit in maniera più semplice.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MyIntLinkedListDisjointSetElement))
            return false;
        MyIntLinkedListDisjointSetElement other = (MyIntLinkedListDisjointSetElement) obj;
        if (representative == null) {
            if (other.representative != null)
                return false;
        } else if (this.representative != other.representative)
            return false;
        if (this.value != other.value)
            return false;
        return true;
    }

}
