package it.unicam.cs.asdl2324.mp1;

import java.util.Set;

/**
 * Una classe che implementa questa interfaccia è in grado di gestire una
 * collezione di insiemi disgiunti.
 * 
 * Le operazioni fondamentali sono la creazione di un nuovo insieme singoletto a
 * partire da un elemento, la ricerca dell'insieme disgiunto di cui fa parte un
 * elemento e l'unione di due insiemi disgiunti.
 * 
 * In ogni momento ad ogni insieme disgiunto presente è associato un elemento
 * rappresentante. Il rappresentante di un insieme singoletto è l'unico
 * elemento, mentre il rappresentante di un insieme disgiunto con più di un
 * elemento può essere qualsiasi elemento dell'insieme disgiunto. Il
 * rappresentante può cambiare in seguito all'operazione di unione.
 * 
 * Gli elementi devono implementare i metodi previsti dall'interface
 * {@code DisjointSetElement}.
 * 
 * @author Luca Tesei
 *
 */
public interface DisjointSets {

    /**
     * Determina se un elemento è stato precedentemente inserito.
     * 
     * @param e
     *              l'elemento da cercare
     * @return true se l'elemento <code>e</code> è già presente in qualche
     *         insieme disgiunto corrente, false altrimenti
     */
    boolean isPresent(DisjointSetElement e);

    /**
     * Crea un nuovo insieme disgiunto contenente solo l'elemento dato.
     * 
     * @param e
     *              l'elemento da inserire nell'insieme creato
     * @throws NullPointerException
     *                                      se l'elemento passato è null
     * @throws IllegalArgumentException
     *                                      se l'elemento passato è già presente
     *                                      in uno degli insiemi disgiunti
     *                                      correnti
     */
    void makeSet(DisjointSetElement e);

    /**
     * Restituisce il rappresentante dell'insieme disgiunto in cui si trova
     * l'elemento passato.
     * 
     * @param e
     *              l'elemento di cui cercare l'insieme disgiunto
     * @return l'elemento rappresentante dell'insieme disgiunto in cui
     *         attualmente si trova <code>e</code>
     * @throws NullPointerException
     *                                      se l'elemento passato è null
     * @throws IllegalArgumentException
     *                                      se l'elemento passato non è presente
     *                                      in nessuno degli insiemi disgiunti
     *                                      correnti
     * 
     */
    DisjointSetElement findSet(DisjointSetElement e);

    /**
     * Unisce gli insiemi disgiunti di cui fanno parte i due elementi passati.
     * Se gli elementi passati fanno già parte dello stesso insieme non fa
     * nulla. Dopo l'operazione il rappresentante dell'insieme unito è un
     * elemento dell'insieme disgiunto la cui identità è definita dalla classe
     * che implementa questa interface.
     * 
     * @param e1
     *               un elemento del primo insieme da unire
     * @param e2
     *               un elemento del secondo insieme da unire
     * @throws NullPointerException
     *                                      se almeno uno dei due elementi
     *                                      passati è null
     * @throws IllegalArgumentException
     *                                      se almeno uno dei due elementi
     *                                      passati non è presente in nessuno
     *                                      degli insiemi disgiunti correnti
     * 
     */
    void union(DisjointSetElement e1, DisjointSetElement e2);

    /**
     * Restituisce l'insieme dei rappresentantanti degli insiemi disgiunti
     * attualmente presenti.
     * 
     * @return l'insieme corrente dei rappresentanti degli insiemi disgiunti
     */
    Set<DisjointSetElement> getCurrentRepresentatives();

    /**
     * Restituisce gli elementi appartenenti all'insieme disgiunto di cui fa
     * parte un certo elemento.
     * 
     * @param e
     *              l'elemento di cui si vuole ottenere l'insieme disgiunto di
     *              cui fa parte
     * @return l'insieme di elementi di cui fa parte l'elemento passato
     * @throws NullPointerException
     *                                      se l'insieme passato è null
     * @throws IllegalArgumentException
     *                                      se l'elemento passato non è
     *                                      contenuto in nessun insieme
     *                                      disgiunto
     */
    Set<DisjointSetElement> getCurrentElementsOfSetContaining(
            DisjointSetElement e);

    /**
     * Restituisce la cardinalità dell'insieme disgiunto di cui fa parte un
     * certo elemento.
     * 
     * @param e
     *              l'elemento di cui si vuole ottenere la cardinalità
     * @return il numero di elementi di cui fa parte l'elemento passato
     * @throws NullPointerException
     *                                      se l'insieme passato è null
     * @throws IllegalArgumentException
     *                                      se l'elemento passato non è
     *                                      contenuto in nessun insieme
     *                                      disgiunto
     */
    int getCardinalityOfSetContaining(DisjointSetElement e);

}