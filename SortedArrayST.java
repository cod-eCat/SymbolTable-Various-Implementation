package com.lpa.lesson;

    // TODO: 2/1/2023
    /*
    Symbol Table Implementation Using Sorted Array
    put - O(N)
    get - O(logN)
     */

public class SortedArrayST <K extends Comparable<K>, V>{

    private K[] keys;
    private V[] vals;
    private static final int INITIAL_CAPACITY = 10;
    private int N;

    public SortedArrayST(){
        this(INITIAL_CAPACITY);
    }

    public SortedArrayST(int capacity){
        if (capacity<INITIAL_CAPACITY){
            capacity=INITIAL_CAPACITY;
        }
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public void put(K key, V val){
        int i = rank(key, 0, N-1);
        if (i<N && key.compareTo(keys[i])==0){
            vals[i] = val;
            return;
        }
        shift(i);
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    private void shift(int index){
        for (int j=N; j>index; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
    }

    public int rank(K key, int lo, int hi){
        if (lo>hi) return lo;
        int mid = lo + (hi-lo)/2;
        int comp = key.compareTo(keys[mid]);
        if (comp<0){
            return rank(key, lo, mid-1);
        } else if (comp > 0) {
            return rank(key, mid+1, hi);
        } else {
            return mid;
        }
    }

    public V get(K key){
        int i = rank(key, 0, N-1);
        if (i<N && key.compareTo(keys[i])==0) return vals[i];
        return null;
    }

    public K min(){
        return keys[0];
    }

    public K max(){
        return keys[N-1];
    }

    public K select(int k){
        return keys[k];
    }

    public boolean contains(K key){
        return get(key)!=null;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i=0;i<N; i++){
            res += " [" + keys[i] + "," + vals[i] + "] ";
        }
        return res;
    }
}
