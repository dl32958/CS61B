package deque;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node{
        public T item;
        public Node prev;
        public Node next;
        public Node(T i, Node p, Node n){
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node((T) "null", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x){
        sentinel = new Node((T) "null", null, null);
        sentinel.next = new Node(x, sentinel, null);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;

    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        // a=a.next, for a =sentinel, if a != sentinel
        for (Node a = sentinel.next; a != sentinel; a = a.next) {
            returnList.add(a.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel || sentinel.prev == sentinel){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()){
            return null;
        }
        Node temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return temp.item;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()){
            return null;
        }
        Node temp = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return temp.item;
    }

    @Override
    public T get(int index) {
        if (this.size() < index || index < 0){
            return null;
        }else {
            int count = 0;
            Node p = sentinel;
            while (p.next != sentinel){
                p = p.next;
                if (count == index){
                    return p.item;
                }
                count += 1;
            }
            return null;
        }
    }

    public T getRecursiveHelper(int index, Node p, int count){
        if (count == index){
            return p.item;
        }
        p = p.next;
        return getRecursiveHelper(index, p, count+1);
    }
    @Override
    public T getRecursive(int index) {
        if (this.size() < index || index < 0){
            return null;
        }
        int count = 0;
        Node p = sentinel.next;
        return getRecursiveHelper(index, p, count);
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListIterator(){
            wizPos = 0;
        }

        public boolean hasNext(){
            return wizPos < size;
        }

        public T next(){
            T returnItem = sentinel.next.item;
            sentinel = sentinel.next;
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean contains(T x){
        int count = 0;
        while (count != size){
            if (sentinel.next.item.equals(x)){
                return true;
            }else {
                sentinel = sentinel.next;
                count += 1;
            }
        }return false;
    }
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o instanceof LinkedListDeque otherLinedList){
            if (otherLinedList.size != this.size){
                return false;
            }
            for (T x : this){
                if (!otherLinedList.contains(x)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        List<String> listOfItems = new ArrayList<>();
        for (T x: this){
            listOfItems.add(x.toString());
        }
        return "{" + String.join(", ", listOfItems) + "}";
    }

    public static void main(String[] args){
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(4);
        lld.addFirst(3);
        lld.addFirst(2);
        lld.removeLast();
        System.out.println(lld.toList());
    }

}
