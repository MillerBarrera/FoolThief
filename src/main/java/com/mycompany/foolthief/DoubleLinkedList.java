package com.mycompany.foolthief;

/**
 * Reportes de robo - Lista Doblemente Enlazada
 * Lista que guarda instancias de la clase Stolen (reporte de robo).
 */
public class DoubleLinkedList {
    private Node<Stolen> head;
    private Node<Stolen> tail;
    private int length;

    DoubleLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }
    /**
     * Retorna el número de elementos contenidos en la lista.
     * @return int: tamaño de la lista
     */
    public int size(){
        return length;
    }
    
    /**
     * Verifica si la lista no tiene elementos.
     * @return boolean:
     *      <ul>
     *          <li>true: la lista esta vacia</li>
     *          <li>false: la lista no esta vacia</li>
     *      </ul>
     */
    public boolean itsEmpty(){
        return (head == null) && (tail == null);
    }

    public void addFirst (Stolen stolen){
        Node<Stolen> node = new Node<Stolen>(stolen);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
    }
    
    /**
     * Agrega un elemento al final de la lista por defecto.
     * @param stolen: Instancia de tipo Stolen.
     */
    public void add(Stolen stolen){
        Node<Stolen> node = new Node<Stolen>(stolen);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        length++;
    }
    
    /**
     * Recarga del método add(Stolen stolen), ahora recibe un indice
     * y lo inserta en este.Recorre la lista hasta anterior al índice e inserta adelante.
     * @param stolen: Instancia de tipo Stolen.
     * @param index: Índice donde insertará.
     */
    public void add(Stolen stolen, int index ){
        if (index == length) {
            add(stolen);
        } else if (index == 0) {
            addFirst(stolen);
        } else if (index > 0 && index <= (length - 1)) {
            Node<Stolen> node = new Node<Stolen>(stolen);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                Node<Stolen> pointer = head;
                int counter = 0;
                while ((counter < (index - 1)) && (pointer.getNext() != null)) {
                    pointer = pointer.getNext();
                    counter++;
                }
                node.setNext(pointer.getNext());
                pointer.getNext().setPrev(node);
                pointer.setNext(node);
                node.setPrev(pointer);
            }
        }
    }
    
    /**
     * Retorna el objeto Stolen que se encuentra contenido en el primer nodo.
     * @return objeto Stolen
     */
    public Stolen getFirst() {
        if (head != null) {
            return head.getData();
        } else {
            return null;
        }
    }
    
    /**
     * Retorna el objeto Stolen que se encuentra contenido en el i-ésimo nodo.
     * @param index
     * @return objeto Stolen
     */
    public Stolen get(int index) {
        if (head != null) {
            if (index == length) {
                getLast();
            } else if (index >= 0 && index <= (length - 1)) {
                Node<Stolen> pointer = head;
                int counter = 0;
                while ((counter < index) && (pointer.getNext() != null)) {
                    pointer = pointer.getNext();
                    counter++;
                }
                if (counter == index) {
                    return pointer.getData();
                } else {
                    return null;
                }
            }
        }
        return null;
    }
    
    /**
     * Retorna el objeto Stolen que se encuentra contenido en el último nodo.
     * @return Objeto Stolen
     */
    public Stolen getLast() {
        if (tail != null) {
            return tail.getData();
        } else {
            return null;
        }
    }
    
    /**
     * Busca un elemento identificado con el id dado y lo retorna.
     * Realiza la comparación de todos los id de los elementos y el 
     * que se recibe.
     * @param id a buscar en la lista
     * @return Objeto Stolen con el que coincide el id
     */
    public Stolen browseById(String id) {
        if (head != null) {
            Node<Stolen> pointer = tail;
            boolean flag = true;
            while (flag == true) {
                if ((flag == false) || (pointer != null)) {
                    Stolen objectNode = pointer.getData();
                    String idObject = objectNode.getId();
                    if (id != idObject) {
                        pointer = pointer.getPrev();
                    } else {
                        return objectNode;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Elimina el primer elemento de la lista.
     */
    public void removeFirst(){
        if (head != null) {
            Node<Stolen> first = head;
            head = head.getNext();
            head.setPrev(null);
            first.setNext(null);
            length--;
        }
    }
    
    /**
     * Elimina el elemento alojado en el i-ésimo nodo de la lista.
     * @param index: Indice del elemento que se borrará.
     */
    public void remove(int index){
        if (head != null) { 
            if (index == 0) {
                removeFirst();
            } else if (index == length - 1) {
                removeLast();
            } else if ((index > 0) && (index < length)) {
                Node<Stolen> pointer = head;
                int counter = 0;
                while ((counter < index - 1) && (pointer.getNext() != null)) {
                    pointer = pointer.getNext();
                    counter++;
                }
                Node<Stolen> temp = pointer.getNext();
                pointer.setNext(temp.getNext());
                temp.getNext().setPrev(pointer);
                temp.setNext(null);
                temp.setPrev(null);
            }
        }
    }
    
    /**
     * Eliminá el último elemento contenido en la lista.
     */
    public void removeLast(){
        if (head != null) {
            Node<Stolen> last = tail;
            tail = tail.getPrev();
            last.setPrev(null);
            length--;
        }
    }
}

