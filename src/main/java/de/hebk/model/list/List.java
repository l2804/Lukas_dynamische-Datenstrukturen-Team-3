package de.hebk.model.list;

import de.hebk.model.node.Node;

/**
 * Objekte der Klasse List verwalten beliebig viele, linear angeordnete Objekte. Auf
 * höchstens ein Listenobjekt, aktuelles Objekt genannt, kann jeweils zugegriffen
 * werden. Wenn eine Liste leer ist, vollständig durchlaufen wurde oder das aktuelle
 * Objekt am Ende der Liste gelöscht wurde, gibt es kein aktuelles Objekt. Das erste
 * oder das letzte Objekt einer Liste können durch einen Auftrag zum aktuellen Objekt
 * gemacht werden. Außerdem kann das dem aktuellen Objekt folgende Listenobjekt
 * zum neuen aktuellen Objekt werden.
 * Das aktuelle Objekt kann gelesen, verändert oder gelöscht werden. Außerdem kann
 * vor dem aktuellen Objekt ein Listenobjekt eingefügt werden
 * @param <T>
 */
public class List<T> {
    private Node<T> first;
    private Node<T> aktuelleNode;


    /**
     * Die Anfrage liefert den Wert true, wenn die Liste keine Objekte
     * enthält, sonst liefert sie den Wert false.
     * @return
     */
    public boolean isEmpty(){
        if(first == null){
            return true;
        }
        return false;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn es ein aktuelles Objekt
     * gibt, sonst liefert sie den Wert false.
     * @return
     */
    public  boolean hasAccess(){
        if (aktuelleNode != null) {
            return true;
        }
        return false;
    }

    /**
     * Falls die Liste nicht leer ist, es ein aktuelles Objekt gibt und
     * dieses nicht das letzte Objekt der Liste ist, wird das dem
     * aktuellen Objekt in der Liste folgende Objekt zum aktuellen
     * Objekt, andernfalls gibt es nach Ausführung des Auftrags kein
     * aktuelles Objekt.
     */
    public void next(){
        if(aktuelleNode != null && aktuelleNode.getNext() != null && first != null){
            aktuelleNode = aktuelleNode.getNext();
        }
        else{
            aktuelleNode = null;
        }
    }

    /**
     * Falls die Liste nicht leer ist, wird das erste Objekt der Liste
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts
     */
    public void toFirst(){
        if(first != null){
            aktuelleNode = first;
        }
    }

    /**
     * Falls die Liste nicht leer ist, wird das letzte Objekt der Liste
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts.
     */
    public void toLast(){
        //ToDo Wienands
        if(first != null) {
            Node tmp = first;

            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }

            aktuelleNode = tmp;
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt, wird das aktuelle Objekt
     * zurückgegeben, andernfalls gibt die Anfrage den Wert null
     * zurück.
     * @return
     */
    public T getObject(){
        if(aktuelleNode != null){
            return aktuelleNode.getContext();
        }

        return null;
    }

    /**
     * Falls es ein aktuelles Objekt gibt und pObject ungleich null ist,
     * wird das aktuelle Objekt durch pObject ersetzt.
     */
    public void setObject(T pObjekt){
        if(aktuelleNode.getContext() != pObjekt && pObjekt != null){
            aktuelleNode.setContext(pObjekt);
        }
    }

    /**
     * Ein neues Objekt pObject wird am Ende der Liste angefügt.
     * Das aktuelle Objekt bleibt unverändert. Wenn die Liste leer ist,
     * wird das Objekt pObject in die Liste eingefügt und es gibt
     * weiterhin kein aktuelles Objekt.
     * Falls pObject gleich null ist, bleibt die Liste unverändert.
     */
    public void append(T pContext){
        //ToDo Wienands
        if(first == null){
            // ToDo du musst first auch initialisieren ;) ala  first = new Node<T>();
            first.setContext(pContext);
        }
        //ToDo Warum überprüfst du, ob pContext != null ist nicht bevor du die first node setzt? Dein Fall hier sollte else if (first != null) sein
        else if(pContext != null) {
            Node<T> tmp = first;
            while (tmp != null) {
                tmp = tmp.getNext();
            }
            if (tmp == null) {
                tmp.setContext(pContext);
            }
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt, wird ein neues Objekt vor
     * dem aktuellen Objekt in die Liste eingefügt. Das aktuelle Objekt
     * bleibt unverändert. Falls die Liste leer ist und es somit kein
     * aktuelles Objekt gibt, wird pObject in die Liste eingefügt und es
     * gibt weiterhin kein aktuelles Objekt. Falls es kein aktuelles
     * Objekt gibt und die Liste nicht leer ist oder pObject gleich null
     * ist, bleibt die Liste unverändert.
     */
    public void insert(T pContext){
        //TODO Fill
    }
    /**
     * Die Liste pList wird an die Liste angehängt. Das aktuelle Objekt
     * bleibt unverändert. Falls pList null oder eine leere Liste ist,
     * bleibt die Liste unverändert
     */
    public void concat(List<T> pList){
        //ToDO Tipp ;) nutzen wir doch unsere toLast() Methode es fehlt aber die Überprüfung, ob first evtl null ist
        // if (first == null && pList.first == null) {first = pList.first}else{
        //      this.toLast();
        //      aktuelleNode.setNext(pList.first)
        // }
        if(pList != null) {
            Node<T> tmp = first;

            while (tmp != null) {
                tmp = tmp.getNext();
            }

            // ToDo Wienands
            tmp.setNext(pList.first);
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt, wird das aktuelle Objekt
     * gelöscht und das Objekt hinter dem gelöschten Objekt wird
     * zum aktuellen Objekt. Wird das Objekt, das am Ende der Liste
     * steht, gelöscht, gibt es kein aktuelles Objekt mehr. Wenn die
     * Liste leer ist oder es kein aktuelles Objekt gibt, bleibt die Liste
     * unverändert.
     */
    public void remove(){
        if(aktuelleNode == first){
            first = first.getNext();
        }
        // ToDo warum hier ein oder (||) und kein und (&&) ?
        else if(aktuelleNode != null || first != null){
            if(aktuelleNode.getNext() != null) {
                Node<T> tmp = first;
                while(tmp.getNext() != aktuelleNode){
                    tmp = tmp.getNext();
                }
                // ToDo Du musst das anders herum machen so setzt du (Pfeil zeigt hier welche Nodes aufeinander folgen)
                //  1. tmp -> aktuellenode.next()
                //  2. aktuelleNode -> aktuelleNode.next()   (da du den Nachfolger von tmp ja vorher auf den Nachfolger von der aktuellenNode gesetzt hast)
                tmp.setNext(aktuelleNode.getNext());
                aktuelleNode = tmp.getNext();
            }
            else{
                aktuelleNode = null;
            }
        }
    }

}
