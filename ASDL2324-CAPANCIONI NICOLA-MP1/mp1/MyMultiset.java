package it.unicam.cs.asdl2324.mp1;

import java.util.*;

/**
 *La classe MyMultiset gestisce un multinsieme ovvero una mappa (collezione) che consente duplicati e tiene traccia del numero di occorrenze
 * di ciascun elemento. Il multinsieme è utilizza una mappa dove le chiavi rappresentano gli elementi e i valori rappresentano
 * il numero totale di occorrenze di ciascun elemento. La classe fornisce una serie di metodi per eseguire operazioni su multiset:
 * aggiunta di elementi, rimozione di elementi, contare il numero di occorrenze e settare il  numero di occorrenze di uno specifico elemento.
 * Il costruttore inizializza un nuovo multinsieme con una mappa vuota. La dimensione del multinsieme è gestita dal metodo size(), che scorre
 * tutte le occorrenze degli elementi presenti nella mappa e ne calcola la somma.
 * Sono stati implementati metodi per aggiungere, rimuovere e contare le occorrenze degli elementi nel multinsieme. Il metodo add può
 * aggiungere un numero specifico di occorrenze di un elemento. Il metodo remove può eliminare un numero specifico di occorrenze di un elemento
 * o rimuovere una singola occorrenza.
 * Il metodo setCount consente di impostare il numero desiderato di occorrenze di un elemento nel multinsieme, aggiungendo o rimuovendo occorrenze
 * come necessario.
 * E' stato implementato un iteratore interno che consente di scorrere gli le occorrenze e gli elementi del multinsieme.
 * Successivamente, sono presenti i metodi equals e hashCode che permettono di confrontare due multiset per determinare se sono uguali in base
 * al numero di occorrenze e agli elementi.
 * Infine viene utilizzato un contatore modCount (contatore di modifiche effettuate) per rilevare eventuali modifiche strutturali (aggiunte o rimozioni)
 * a una mappa durante l'iterazione. Durante ogni successiva operazione sull'iteratore, viene verificato se il valore di modCount della mappa
 * è cambiato, se viene rilevato un cambiamento, viene lanciata un'eccezione ConcurrentModificationException, segnalando che la struttura della
 * mappa è stata modificata.
 *
 * @author Luca Tesei (template) Nicola, Capancioni, nicola.capancioni@studenti.unicam.it (implementazione)
 */
public class MyMultiset<E> implements Multiset<E> {
    private final Map<E, Integer> occorrenzeTot;  //occorrenze (totali) di ogni elemento nella mappa
    private int modCount;                         //contatore utilizzato per tenere traccia delle modifiche

    /**
     * Costruttore che inizializza un nuovo multinsieme con una mappa vuota per tenere traccia
     * delle occorrenze degli elementi
     */
    public MyMultiset() {
        this.occorrenzeTot = new HashMap<>();
    }
    /**
     * Restituisce il numero totale di elementi nel multinsieme. Il metodo utilizza un ciclo
     * for per scorrere tutte le occorrenze degli elementi presenti in elementCountMap e
     * sommarle per ottenere la dimensione totale del multinsieme
     */
    @Override
    public int size() {
        int Msize = 0;                                      //contiene la dimensione totale del multinsieme (inizializzazione)
        int i = 0;                                          //indice utilizzato per scorrere i valori delle occorrenze
        Collection<Integer> values = occorrenzeTot.values();//ottiene la collezione dei valori dalla mappa occorrenzeTot
        int size = values.size();                           //pongo a size la dimensione del multinsieme

        for (i = 0; i < size; i++) {                               //scorre i valori delle occorrenze degli elementi e attraverso l'indice accede al volore corrente
            Msize = Msize + values.toArray(new Integer[0])[i];     //e aggiunge Msize il quale conterrà la somma di tutti gli elementi presenti nella collezione multiset
        }
        return Msize;                                              //ritorna la dimensione totale del multinsieme
    }
    /**
     * Restituisce il numero di occorrenze dell'elemento specificato nel multinsieme.
     * Se l'elemento non è presente, viene restituito 0.
     */
    @Override
    public int count(Object element) {  //viene passato il parametro element di cui contare le occorrenze
        if (element == null) {          //viene lanciata un eccezione se l'elemento passato ha valore null
            throw new NullPointerException("Non possono esserci valori nulli nel multiset");
        }
        return occorrenzeTot.getOrDefault(element, 0); //ritorna il numero di occorrenze dell'elemento del multinsieme o 0 se non era presente
    }
    /**
     * Aggiunge un numero di occorrenze di un certo elemento a questo multiset
     */
    @Override
    public int add(E element, int occurrences) { //viene passato il paramentro element da aggiungere o incrementare nel multinsieme  e il parametro di occorrenze da aggiungere che deve essere positivo
        if (element == null) {
            throw new NullPointerException("Non possono esserci valori nulli nel multiset");
        }
        if (occurrences < 0) {
            throw new IllegalArgumentException("Le occorrenze non possono essere negative");
        }
        //ricevo il numero corrente di occorrenze dell'elemento nel multinsieme
        int preCount = occorrenzeTot.getOrDefault(element, 0); //preCount contiene il numero di occorrenze dell'elemento nel multinsieme prima di apportare modifiche

        //controllo le nuove occorrenze che non possono superare Integer.MAX_VALUE
        if (occurrences > Integer.MAX_VALUE - preCount) {
            throw new IllegalArgumentException("Le occorrenze superano il MAX_VALUE");
        }
        //aggiunta delle nuove occorrenze al multinsieme
        occorrenzeTot.put(element, preCount + occurrences);

        //aggiorno contatore modifiche
        modCount++;

        //ritorno il numero attuale delle occorrenze prima dell'operazione (non aggiornato)
        return preCount;
    }
    /**
     * Aggiunge un'occorrenza dell'elemento al multinsieme. Se l'elemento è già presente,
     * incrementa il numero di occorrenze di 1; se l'elemento non è presente, aggiunge un nuovo elemento
     */
    @Override
    public void add(E element) { //viene passato il parametro element da ggiunngeere al multinsieme
        if (element == null) {
            throw new NullPointerException("Non possono esserci valori nulli nel multiset");
        }
        int preCount = occorrenzeTot.getOrDefault(element, 0);      //prende il numero attuale di occorrenze dell'elemento
        if (1 > Integer.MAX_VALUE - preCount) {                                 //controllo del numero di occorrenze che non può superare il MAX_VALUE
            throw new IllegalArgumentException("Le occorrenze superano il MAX_VALUE");
        }
        add(element, 1); //aggiunge una nuova occorrenza
    }
    /**
     * Elimina un numero definito di occorrenze dell'elemento dal multinsieme.
     * Se l'elemento è presente viene diminuito il numero di occorrenze di quell'elemento di una quantità uguale a occurrences;
     * se l'elemento non è presente o il numero di occorrenze da eliminiare è >= al numero attuale di occorrenze, elimina le occorrenze dell'elemento
     * dal multinsieme.
     */
    @Override
    public int remove(Object element, int occurrences) { //vengono passati i parametri element da cui rimuovere le occorrenze e occurrences da cui rimuovere le occorrenze
        if (element == null) {
            throw new NullPointerException("Non possono esserci valori nulli nel multiset");
        }
        if (occurrences < 0) {
            throw new IllegalArgumentException("Le occorrenze non possono essere negative");
        }
        //restituisce il valore delle occorrenze, oppure se la chiave non è presente nella mappa restituisce un valore di default (ovvero 0).
        int preCount = occorrenzeTot.getOrDefault(element, 0);
        if(occurrences == 0){
            return preCount;
        }
        if (preCount > 0) {                         //se è > 0 l'elemento è presente nella mappa con almeno un'occorrenza
            if (occurrences >= preCount) {          //se il numero di occorrenze è >= alle occorrenze attuali,
                occorrenzeTot.put((E) element, 0);  //le occorrenze vengono rimsosse
            } else {
                occorrenzeTot.put((E) element, preCount - occurrences); //sennò vengono decrementate quelle precedenti con quelle che vengono passate
            }
            modCount++;                             //aggiorno il contatore delle modifiche
            return preCount;                        //ritorno il numero delle occorrenze prima di apportare modifiche
        }
        return 0;                                   //elemento non presente
    }

    /**Rimuove da questo multinsieme una singola occorrenza di un elemento se
     * questo è presente secondo il metodo {@code boolean contains(Object)}
     */
    @Override
    public boolean remove(Object element) {
        int count = remove(element, 1);     //richiama il metodo remove (element, occurrences) con le occorrenze = 1
        if(count > 0){                                 //restituisce true se almeno un'occorrenza è stata eliminata
            return true;
        }else                                          //altrimenti false
            return false;
    }
/**Aggiunge o rimuove le occorrenze di un elemento in modo tale che il
 * l'elemento raggiunga il numero di occorrenze desiderato
 */
    @Override
    public int setCount(E element, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count non può essere un valore negativo");
        }
        int preCount = occorrenzeTot.getOrDefault(element, 0);              //ottiene il numero di occorrenze dell'elemento dal multinsieme
        //se i due contatori sono uguali non bisogna effettuare nessun cambiamento
        if(count == preCount){
            return preCount;
        }
        //confronto count e preCount
        if (count > preCount) {                        //se count maggiore di preCount
            add(element, count - preCount); //aggiungo al multinsieme un numero di occorrenze pari alla differenza tra count e preCount
        } else if (count < preCount) {                 //altrimenti se count è minore di preCount riduco occorrenze
            if (occorrenzeTot.get(element) > 1) {      //controllo se è presente più di un' occorrenza dell'elemento
                occorrenzeTot.put(element, count);     //se è presente imposta il numero di occorrenze a count
            } else {
                remove(element, preCount - count);  //altrimenti se è presente solo un'occorrenza rimuovo quelle in più
            }
        }
        modCount++;                                                 //aggiorno contatore delle modifiche
        return preCount;                                            //ritorna il numero di occorrenze prima di apportare modifiche
    }
    /**
     * Restituisce l'insieme di elementi distinti contenuti in questo
     * multinsieme. L'ordine degli elementi nel set risultato non è specificato.
     */
    @Override
    public Set<E> elementSet() {
        //creo e ritorno un nuovo HashSet che contiene le chiavi degli elementi della mappa
        return new HashSet<>(occorrenzeTot.keySet());
    }
    /**
     * Determina se questo multinsieme contiene l'elemento specificato.
     */
    @Override
    public boolean contains(Object element) {
        if (element == null) {
            throw new NullPointerException("Non possono esserci valori nulli nel multiset");
        }
        return occorrenzeTot.containsKey(element); //ritorna true se l'elemento passato è una chiave nella mappa, sennò restituisce false
    }
    /**
     * Rimuove tutti gli elementi da questo multinsieme. Il multinsieme sarà
     * vuoto dopo il ritorno da questo metodo.
     *
     */
    @Override
    public void clear() {
        occorrenzeTot.clear();          //rimuove tutti gli elementi dalla mappa
        modCount++;                     //aggiorno contatore delle modiche
    }
    /**
     * Determina se questo multinsieme è vuoto.
     *
     */
    @Override
    public boolean isEmpty() {
        return occorrenzeTot.isEmpty(); //ritorno true se la mappa occorrenzeTot è vuota sennò ritorno false
    }
    /**
     * Verifica se due mappe sono considerate uguali; ciò avviene se le mappe contengono gli stessi elementi
     * con lo stesso numero di occorrenze.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {                                      //controllo se l'oggetto è un'istanza della classe stessa
            return true;                                        //se è uguale ritorno true
        }
        if (obj == null || getClass() != obj.getClass()) {      //controllo se l'oggetto è null o non è un'istanza della stessa classe
            return false;                                       //se nullo o non istanziato ritorno false
        }
        //confronto le due mappe per determinare l'uguaglianza degli oggetti con lo stesso numero di occorrenze
        return occorrenzeTot.equals(((MyMultiset) obj).occorrenzeTot);
    }


    @Override
    public int hashCode() {
        return Objects.hash(occorrenzeTot);
    }
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
     */
    @Override
    public Iterator<E> iterator() {
        return new Iteratore();     //restituisce un nuovo iteratore per il multinsieme
    }

    private class Iteratore implements Iterator<E> {    //classe che implementa l'iteratore
        int modificheAttese;    //variabile che tiene traccia delle modifiche attese al multinsieme
        private final Iterator<Map.Entry<E, Integer>> mapIterator;  //creo iteratore per scorrere la mappa
        int occorrenze;     //variabile che tiene traccia delle occorrenze rimanenti
        E element;          //variabile che contiene l'elemento attuale nell'iteratore

        private Iteratore() {   //costruttore iteratore
            mapIterator = occorrenzeTot.entrySet().iterator();  //creo un iteratore per la mappa occorrenzeTot che consente di accedere alle occorrenze di ogni elemento
            this.modificheAttese = MyMultiset.this.modCount;    //setto il contatore delle modifiche attese con il valore corrente delle modifiche del multinsieme
            occorrenze = 0;                                     //prima di iterare setto a 0 il numero di occorrenze rimanenti dell'elemento
        }

        @Override
        public boolean hasNext() {
            //restituisco true se c'è ancora almeno un elemento o un'occorrenza dell'elemento corrente da restituire
            return mapIterator.hasNext() || occorrenze > 0;
        }

        @Override
        public E next() {
            if (modificheAttese != MyMultiset.this.modCount) {                                  //se ci sono state modifiche al multinsieme dopo la creazione dell'iteratore
                throw new ConcurrentModificationException("Modifiche rilevate dopo la creazione dell'iteratore");    //lancio eccezione
            }

            if (occorrenze == 0) {                              //se non ci sono occorrenze dell'elemento da ritornare
                Map.Entry<E, Integer> app = mapIterator.next(); //prende il valore successivo della chiave e delle occorrenze nella mappa
                element = app.getKey();                         //estrae la chiave
                occorrenze = app.getValue();                    //estrae il numero di occorrenze della coppia
            }

            occorrenze--;                                       //decremento il numero di occorrenze
            return element;                                     //restituis o l'elemento corrente
        }
    }
}

