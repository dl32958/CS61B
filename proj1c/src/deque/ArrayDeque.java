package deque;

import org.apache.commons.collections.iterators.ArrayIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private int factor = 2;
    private double usageFactor = 0.25;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public int plusOne(int index){
        return (index + 1) % items.length;
    }

    public int minsOne(int index){
        return (index - 1) % items.length;
    }
    public void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        int cur = plusOne(nextFirst);

        // assign old items to newArray.
        for (int i = 0; i < size; i++){
            newArray[i] = items[cur];
            cur = plusOne(cur);
        }

        items = newArray;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public int pickFirst(){
        return plusOne(nextFirst);
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length){
            resize(factor * size);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = updateNextFirst();
    }

    public int updateNextFirst(){
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        }else {
            nextFirst -= 1;
        }
        return nextFirst;
    }
    @Override
    public void addLast(T x) {

        if (size == items.length){
            resize(factor * size);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = updateNextLast();
    }

    public int updateNextLast(){
        if (nextLast == items.length - 1){
            nextLast = 0;
        }else {
            nextLast += 1;
        }
        return nextLast;
    }
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int cur = plusOne(nextFirst);

        for (int i = 0; i < size; i ++){
            returnList.add(items[cur]);
            cur = plusOne(cur);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
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
        if (size == 0){
            return null;
        }
        int oldFirst = plusOne(nextFirst);
        T oldFirstItemTemp = items[oldFirst];
        items[oldFirst] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;

        if (items.length > 16 && size < usageFactor * items.length){
            resize(items.length / 2);
        }

        return oldFirstItemTemp;
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        int oldLast = minsOne(nextLast);
        T oldLastItemTemp = items[oldLast];
        items[oldLast] = null;
        nextLast = minsOne(nextLast);
        size -= 1;

        if (items.length > 16 && size < usageFactor * items.length){
            resize(items.length / 2);
        }

        return oldLastItemTemp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }
        int realIndex = (plusOne(nextFirst) + index) % items.length;
        return items[realIndex];

    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int wizPos;
        public ArrayIterator(){
            wizPos = 0;
        }

        public boolean hasNext(){
            return wizPos < size;
        }
        public T next(){
            T returnItem = items[plusOne(nextFirst)];
            nextFirst = plusOne(nextFirst);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean contains(T x){
        int count = 0;
        while (count != size){
            if (items[plusOne(nextFirst)].equals(x)){
                return true;
            }else {
                nextFirst = plusOne(nextFirst);
                count += 1;
            }
        }return false;
    }
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o instanceof ArrayDeque otherArray){
            if (otherArray.size != this.size){
                return false;
            }
            for (T x : this){
                if (!otherArray.contains(x)){
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



    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 10; i++){
            ad.addFirst(i);
        }

        for (int i = 0; i < 10; i++){
            ad.addLast(i + 10);
        }
    }

}
