/**
 * 
 */
package it.unicam.cs.asdl2324.mp1;

/**
 * Interfaccia che astrae le funzionalità richieste ad un oggetto per essere
 * inserito in una collezione di insiemi disgiunti così come descritta
 * dall'interface {@DisjointSets}.
 * 
 * Le operazioni richieste sono semplicemente dei metodi setter/getter che la
 * classe che implementa deve fornire.
 * 
 * @author Luca Tesei
 * 
 */
public interface DisjointSetElement {

    /**
     * Fornisce un riferimento a un altro elemento. Il significato associato
     * dipende dall'implementazione della interface {@DisjointSets} e viene
     * specificato in quella classe. Ad esempio in una implementazione con
     * Linked Lists questo riferimento può essere usato per restituire il
     * rappresentante dell'insieme disgiunto di questo elemento.
     * 
     * @return un riferimento a un altro elemento, dipendente dal tipo di
     *         implementazione di {@DisjointSets}
     */
    DisjointSetElement getRef1();

    /**
     * Cambia il riferimento 1 di questo elemento. Si veda la API del metodo
     * {@getRef1}.
     * 
     * @param e
     *              il nuovo valore del riferimento 1
     * @throws NullPointerException
     *                                  se l'elemento passato è null
     */
    void setRef1(DisjointSetElement e);

    /**
     * Fornisce un riferimento a un altro elemento. Il significato associato
     * dipende dall'implementazione della interface {@DisjointSets} e viene
     * specificato in quella classe. Ad esempio in una implementazione con
     * Linked Lists questo riferimento può essere usato per restituire il
     * prossimo elemento nella lista concatenata che rappresenta l'insieme
     * disgiunto di cui questo elemento fa parte.
     * 
     * @return un riferimento a un altro elemento, dipendente dal tipo di
     *         implementazione di {@DisjointSets}
     */
    DisjointSetElement getRef2();

    /**
     * Cambia il riferimento 2 di questo elemento. Si veda la API del metodo
     * {@getRef2}.
     * 
     * @param e
     *              il nuovo valore del riferimento 1
     * @throws NullPointerException
     *                                  se l'elemento passato è null
     */
    void setRef2(DisjointSetElement e);

    /**
     * Fornisce un numero intero associato a questo elemento. Il significato
     * associato dipende dall'implementazione della interface {@DisjointSets} e
     * viene specificato in quella classe. Ad esempio in una implementazione con
     * Linked Lists questo numero può essere associato al numero di elementi
     * correnti nell'insieme disgiunto se questo elemento ne è il
     * rappresentante.
     * 
     * @return un intero associato a questo elemento, dipendente dal tipo di
     *         implementazione di {@DisjointSets}
     */
    int getNumber();

    /**
     * Cambia l'intero associato a questo elemento. Si veda la API del metodo
     * {@getInt}.
     * 
     * @param e
     *              il nuovo valore del riferimento 1
     */
    void setNumber(int n);

}
