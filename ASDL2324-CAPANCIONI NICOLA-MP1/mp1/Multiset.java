package it.unicam.cs.asdl2324.mp1;

import java.util.Iterator;
import java.util.Set;

/**
 * Un multinsieme (chiamato anche bag) è un insieme in cui gli elementi hanno un
 * certo numero di occorrenze (detto anche molteplicità o frequenza). Tale
 * numero può essere zero, il che è equivalente a dire che l'elemento non
 * appartiene al multinsieme. Se tale numero è invece un intero positivo
 * {@code n} allora l'elemento è presente {@code n} volte nel multinsieme. La
 * molteplicità non può essere negativa.
 * 
 * Come nel classico insieme, in un multinsieme gli elementi non sono ordinati
 * né indicizzati.
 * 
 * Non è possibile inserire {@code null} in un multinsieme.
 *
 * Poiché la molteplicità è rappresentata con un {@code int}, un multinsieme non
 * può mai contenere più di {@code Integer.MAX_VALUE} occorrenze per ogni
 * elemento.
 * 
 * @author Luca Tesei
 *
 * @param <E>
 *                il tipo di elmenti del multinsieme. Se un multinsieme deve
 *                contenere oggetti di tipo eterogeneo allora si può utilizzare
 *                il tipo {@code Object}.
 */
public interface Multiset<E> {
    /**
     * Restituisce il numero totale di elementi in questo multinsieme. Ad
     * esempio, per il multinsieme {@code [1,2,3,1,4]} il metodo restituisce
     * {@code 5} poiché l'elemento {@code 1} ha due occorrenze.
     * 
     * @return il numero totale di elementi in questo multinsieme contando tutte
     *         le occorrenze
     *
     */
    public int size();
    
    // TODO 2022 il risultato dovrebbe essere un long

    /**
     * Restituisce il numero di occorrenze di un certo elemento in questo
     * multinsieme.
     *
     * @param element
     *                    l'elemento di cui contare le occorrenze
     * @return il numero di occorrenze dell'elemento {@code element} in questo
     *         multinsieme. Se l'elemento non è presente restituisce 0
     * @throws NullPointerException
     *                                  se {@code element} è null
     */
    public int count(Object element);

    /**
     * Aggiunge un numero di occorrenze di un certo elemento a questo multiset.
     *
     * @param element
     *                        l'elemento di cui aggiungere le occorrenze
     * @param occurrences
     *                        il numero di occorrenze dell'elemento da
     *                        aggiungere. Può essere zero, nel qual caso non
     *                        verrà apportata alcuna modifica.
     * @return il numero di occorrenze dell'elemento prima dell'operazione;
     *         possibilmente zero
     * @throws IllegalArgumentException
     *                                      se {@code occurrences} è negativo, o
     *                                      se questa operazione comporterebbe
     *                                      più di {@code Integer.MAX_VALUE}
     *                                      occorrenze dell'elemento
     * @throws NullPointerException
     *                                      se {@code element} è null
     */
    public int add(E element, int occurrences);

    /**
     * Aggiunge una singola occorrenza di un certo elemento a questo multiset.
     *
     * @param element
     *                        l'elemento di cui aggiungere l'occorrenza
     * @param occurrences
     *                        il numero di occorrenze dell'elemento da
     *                        aggiungere. Può essere zero, nel qual caso non
     *                        verrà apportata alcuna modifica
     * @throws IllegalArgumentException
     *                                      se questa operazione comporterebbe
     *                                      più di {@code Integer.MAX_VALUE}
     *                                      occorrenze dell'elemento
     * @throws NullPointerException
     *                                      se {@code element} è null
     */
    public void add(E element);

    /**
     * Rimuove da questo multinsieme un dato numero di occorrenze di un elemento
     * se questo è presente secondo il metodo {@code boolean contains(Object)}.
     * Se il multinsieme contiene meno del dato numero di occorrenze, tutte le
     * occorrenze verranno rimosse.
     *
     * @param element
     *                        l'elemento di cui rimuovere le occorrenze
     * @param occurrences
     *                        il numero di occorrenze dell'elemento da
     *                        rimuovere. Può essere zero, nel qual caso non
     *                        verrà apportata alcuna modifica
     * @return il numero di occorrenze dell'elemento prima dell'operazione;
     *         possibilmente zero
     * @throws IllegalArgumentException
     *                                      se {@code occurrences} è negativo
     * @throws NullPointerException
     *                                      se {@code element} è null
     */
    public int remove(Object element, int occurrences);

    /**
     * Rimuove da questo multinsieme una singola occorrenza di un elemento se
     * questo è presente secondo il metodo {@code boolean contains(Object)}.
     *
     * @param element
     *                    l'elemento di cui rimuovere l'occorrenza
     * @return {@code true} se un'occorrenza è stata trovata e rimossa,
     *         {@code false altrimenti}
     * @throws NullPointerException
     *                                  se {@code element} è null
     */
    public boolean remove(Object element);

    /**
     * Aggiunge o rimuove le occorrenze di un elemento in modo tale che il
     * l'elemento raggiunga il numero di occorrenze desiderato.
     *
     * @param element
     *                        l'elemento di cui aggiungere o togliere occorrenze
     * @param occurrences
     *                        il numero desiderato di occorrenze dell'elemento
     * @return il numero di occorrenze dell'elemento prima dell'operazione;
     *         possibilmente zero
     * @throws IllegalArgumentException
     *                                      se {@code count} è negativo
     * @throws NullPointerException
     *                                      se {@code element} è negativo
     */
    public int setCount(E element, int occurrences);

    /**
     * Restituisce l'insieme di elementi distinti contenuti in questo
     * multinsieme. L'ordine degli elementi nel set risultato non è specificato.
     *
     * @return l'insieme di elementi distinti in questo multinsieme
     */
    public Set<E> elementSet();

    /**
     * Restituisce un iteratore per questo multinsieme. L'iteratore deve
     * presentare tutti gli elementi del multinsieme (in un ordine qualsiasi) e
     * per ogni elemento deve presentare tutte le occorrenze. Le occorrenze
     * dello stesso elemento devono essere presentate in sequenza. L'iteratore
     * restituito non implementa l'operazione {@code remove()}.
     * 
     * L'iteratore restituito deve essere <b>fail-fast</b>: se il multinsieme
     * viene modificato strutturalmente (cioè viene fatta un'aggiunta o una
     * cancellazione di almeno un'occorrenza) in qualsiasi momento dopo la
     * creazione dell'iteratore, l'iteratore dovrà lanciare una
     * {@code ConcurrentModificationException} alla chiamata successiva del
     * metodo {@code next()}.
     * 
     * @return un iteratore per questo multinsieme
     */
    public Iterator<E> iterator();

    /**
     * Determina se questo multinsieme contiene l'elemento specificato.
     *
     * @param element
     *                    l'elemento da cercare
     * @return {@code true} se questo multinsieme contiene almeno una occorrenza
     *         di un elemento {@code e} tale che
     *         {@code element.equals(e) == true}
     * @throws NullPointerException
     *                                  se {@code element} è null
     */
    public boolean contains(Object element);

    /**
     * Rimuove tutti gli elementi da questo multinsieme. Il multinsieme sarà
     * vuoto dopo il ritorno da questo metodo.
     *
     */
    public void clear();

    /**
     * Determina se questo multinsieme è vuoto.
     *
     * @return {@code true} se questo multinsieme è vuoto, {@code false} se
     *         contiene almeno una occorrenza di un elemento
     */
    public boolean isEmpty();

}
