package it.unicam.cs.asdl2324.mp1;

import java.util.Set;
import java.util.HashSet;


/**
 *
     La classe LinkedListDisjointSets implementa una struttura di insiemi disgiunti mediante l'utilizzo di una lista concatenata di rappresentanti.
     Gli elementi sono gestiti come nodi collegati, consentendo l'esecuzione di operazioni come la creazione di nuovi insiemi, l'unione di insiemi
     disgiunti e la ricerca dei rappresentanti. La parte fondamentale del codice è rappresentato dal SetList, un HashSet che tiene traccia
     dei rappresentanti correnti. L'utilizzo di un HashSet assicura un accesso efficiente ai rappresentanti.
     Il metodo isPresent verifica se un elemento è presente in qualche insieme disgiunto, controllando se il puntatore al suo rappresentante (ref1)
     non è nullo. Il metodo makeSet crea un nuovo insieme disgiunto rappresentato da una lista concatenata contenente l'unico elemento specificato,
     con il rappresentante che è l'elemento stesso e la cardinalità iniziale impostata a 1.
     Il metodo findSet restituisce il rappresentante di un elemento nella rappresentazione con liste concatenate.
     Il metodo union effettua l'unione di due insiemi disgiunti, determinando il nuovo rappresentante in base alla cardinalità degli insiemi uniti.
     La classe fornisce anche metodi per ottenere l'insieme corrente dei rappresentanti, gli elementi dell'insieme contenente un dato elemento
     e la cardinalità di un insieme specifico.

 * @author Luca Tesei (template) Nicola, Capancioni, nicola.capancioni@studenti.unicam.it (implementazione)
 *
 */
public class LinkedListDisjointSets implements DisjointSets {

    private final Set<DisjointSetElement> SetList; //creo un set dei rappresentanti (set=lista)

    /**
     * Crea una collezione vuota di insiemi disgiunti.
     */
    public LinkedListDisjointSets() {
    //creo il set dei rappresentanti
        SetList = new HashSet<>();
    }

    /*
     * Nella rappresentazione con liste concatenate un elemento è presente in
     * qualche insieme disgiunto se il puntatore al suo elemento rappresentante
     * (ref1) non è null.
     */
    @Override
    public boolean isPresent(DisjointSetElement e) {
        if(e.getRef1()!=null){                  //con getRef1() ottengo il riferimento al primo elemento di e
            return true;                        //restituisce true se il riferimento non è nullo
        }else {
            return false;                       //restituisce false se il riferimento è nullo
        }
    }
    /*
     * Nella rappresentazione con liste concatenate un nuovo insieme disgiunto è
     * rappresentato da una lista concatenata che contiene l'unico elemento. Il
     * rappresentante deve essere l'elemento stesso e la cardinalità deve essere
     * 1.
     */
    @Override
    public void makeSet(DisjointSetElement e) {
        if(e==null){
            throw new NullPointerException("L'elemento non può essere nullo");
        }
        if(isPresent(e)) {
            throw new IllegalArgumentException("L'elemento è già presente");
        }
        e.setRef1(e);       //setto il riferimento 1 dell'elemento su se stesso
        e.setRef2(null);    //setto il riferimento 2 dell'elemento a null
        e.setNumber(1);     //setto il numero dell'elemento a 1
        SetList.add(e);     //aggiungo l'elemento e alla lista degli insiemi
    }

    /*
     * Nella rappresentazione con liste concatenate per trovare il
     * rappresentante di un elemento basta far riferimento al suo puntatore
     * ref1.
     */
    @Override
    public DisjointSetElement findSet(DisjointSetElement e) {
        if(e==null) {
            throw new NullPointerException("Non possono esserci valori nulli");
        }
        if(!isPresent(e)) {
            throw new IllegalArgumentException("Elemento già presente");
        }
        return e.getRef1(); //ritorno il rappresentante
    }
    /*
     * Dopo l'unione di due insiemi effettivamente disgiunti il rappresentante
     * dell'insieme unito è il rappresentate dell'insieme che aveva il numero
     * maggiore di elementi tra l'insieme di cui faceva parte {@code e1} e
     * l'insieme di cui faceva parte {@code e2}. Nel caso in cui entrambi gli
     * insiemi avevano lo stesso numero di elementi il rappresentante
     * dell'insieme unito è il rappresentante del vecchio insieme di cui faceva
     * parte {@code e1}.
     *
     * Questo comportamento è la risultante naturale di una strategia che
     * minimizza il numero di operazioni da fare per realizzare l'unione nel
     * caso di rappresentazione con liste concatenate.
     *
     */
    @Override
    public void union(DisjointSetElement e1, DisjointSetElement e2) {
        //controllo se e1 o e2 sono nulli.
        if(e1 == null || e2 == null) {
            throw new NullPointerException("L'elemento e1 o e2 è nullo");
        }

        //controllo se uno dei due elementi passati non è presente in nessuno degli insiemi disgiunti
        if(!SetList.contains(e1.getRef1()) || !SetList.contains(e2.getRef1())) {
            throw new IllegalArgumentException("Almeno uno dei due non è presente in nessuno degli elementi disgiunti");
        }
        //se i puntatori all'elemento e1.getref1() e e1.getRef2() sono nello stesso insieme non fa niente

        //il rappresentante sarà sempre il 1.
        if(e1.getRef1() == e2.getRef1()) {  //Controllo se sono nello stesso insieme, ovvero se hanno un rappresentante uguale.
            return;
        }
        DisjointSetElement app ;   //variabile di tipo disjoint appoggio del nuovo rappresentante
        DisjointSetElement old;    //variabile di tipo disjoint appoggio del vecchio rappresentamte
        int geta1 = e1.getNumber(); // dimensione del 1 set (variabile di appoggio)
        int geta2 = e2.getNumber(); // dimensione del 2 set (variabile di appoggio)
        //trovo il maggiore tra i due rappresentanti, imposta quelli nuovi e se sono uguali prendo il 1 come maggiore
        if(geta1 >= geta2){          //confronto la lunghezza e setto il rappresentante corretto.
            app = e1.getRef1();
            old = e2.getRef1();
        }else {
            app = e2.getRef1();
            old = e1.getRef1();
        }

        DisjointSetElement current = app;    //DisjointSetElement current tiene traccia degli spostamenti e delle modifiche
        while(current.getRef2() != null) {   //scorro le posizioni del Set (nuovo referente) affinchè non è nullo
            current.setNumber(geta1+geta2);  //aggiorno la dimensione (number = somma tra i due)
            current = current.getRef2();     //mi da il successivo e aggiorno per scorrere.
        }
        current.setNumber(geta1 + geta2);
        current.setRef2(old);                   //collego l'ultimo elemento del nuovo insieme con il primo elemento del vecchio insieme da aggiungere(vecchio).
        //aggiornamento degli elementi del vecchio insieme con il nuovo rappresentante
        current = current.getRef2();            //punta al successivo, ovvero il primmo elemento del vecchio insieme da aggiornare
        while(current.getRef2() != null){       //mi trovo nel primo elemento del vecchio insieme che bisogna modificarlo con il nuovo
            current.setRef1(app);               //cambio il rappresentante con il nuovo
            current.setNumber(geta1+geta2);     //aggiorno la dimensione
            current = current.getRef2();        //sposto al successivo
        }
        //Ultimo elemento aggiunto manualmente fuori dal ciclo while
        current.setRef1(app);
        current.setNumber(geta1+geta2);
        SetList.remove(old);                //rimuovo il vecchio insieme dalla lista degli insiemi
    }
    @Override
    public Set<DisjointSetElement> getCurrentRepresentatives() {
        return SetList;             //ritorno il set di rappresentanti
    }

    @Override
    public Set<DisjointSetElement> getCurrentElementsOfSetContaining(DisjointSetElement e) {
        if(e == null)
            throw new NullPointerException("Non possono esserci valori nulli");
        if(!isPresent(e))
            throw new IllegalArgumentException("Elemento non presente");

        Set<DisjointSetElement> insiemeNuovo =  new HashSet<>();        //creo un nuovo set che serve per inserire gli elementi
        DisjointSetElement elementoCorrente = findSet(e);               //trovo il rappresentante dell'insieme che contiene l'elemento

        insiemeNuovo.add(elementoCorrente);                             //aggiunnta del rappresentante al set

        while(elementoCorrente.getRef2() != null){                      //scorro gli elementi dell'insieme fino all'ultimo elemento
            insiemeNuovo.add(elementoCorrente.getRef2());               //aggiunta del successivo
            elementoCorrente = elementoCorrente.getRef2();              //ottengo l'elemento successivo nell'insieme disgiunto
        }
        return insiemeNuovo;                                            //restituisce il set nuovo
    }

    @Override
    public int getCardinalityOfSetContaining(DisjointSetElement e) {
        if(e == null)
            throw new NullPointerException("Non possono esserci valori nulli");
        if(!isPresent(e))
            throw new IllegalArgumentException("Elemento non presente");
        return e.getRef1().getNumber();                                 //return della lunghezza dell'insieme con getNumber
    }
}
