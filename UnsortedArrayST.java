package com.lpa.lesson;

import java.util.NoSuchElementException;

    // TODO: 1/29/2023
    /*
    Symbol Table Implementation Using Unsorted Array
    Author - @LwinPhyoAung(cod-eCat)
    put - O(N)
    get - O(N)
    remove - O(N)
    */
public class UnsortedArrayST <K extends Comparable<K>, V>{

    private static final int INITIAL_CAPACITY = 10;
    private K[] keys;
    private V[] vals;
    private int size;
    private int pointer;

    public UnsortedArrayST(){
        this(INITIAL_CAPACITY);
    }

    public UnsortedArrayST(int capacity){
        if (capacity<INITIAL_CAPACITY){
            capacity = INITIAL_CAPACITY;
        }
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public void put(K key, V val){

        if (val==null){
            return;
        }
        for (int i = 0; i<size; i++){
            if (key.compareTo(keys[i]) == 0){
                keys[i] = key;
                vals[i] = val;
                return;
            }
        }
        keys[pointer] = key;
        vals[pointer++] = val;
        size++;

    }

    public V search(K key){
        int index = getIndex(key);
        if (index!=-1){
            return vals[index];
        }
        return null;
    }

    private int getIndex(K key){

        for (int i = 0; i<size; i++){
            if (keys[i].compareTo(key)==0){
                return i;
            }
        }
        return -1;

    }

    public V remove(K key){
        int i = getIndex(key);
        if (i==-1){
            throw new NoSuchElementException();
        }
        if (i==size-1){
            V res = vals[i];
            keys[i] = null;
            vals[i] = null;
            pointer--;
            size--;
            return res;
        }
        V res = vals[i];
        keys[i] = keys[size-1];
        vals[i] = vals[size-1];
        keys[size-1] = null;
        vals[size-1] = null;
        size--;
        pointer--;
        return res;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public boolean contains(K key){
        return getIndex(key)!=-1;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i<size; i++){
            res += " [" + keys[i] + "," + vals[i] + "] ";
        }
        return res;
    }
}
