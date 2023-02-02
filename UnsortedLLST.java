package com.lpa.lesson;

    // TODO: 2/1/2023
    /*
    Symbol Table Implementation Using Unsorted Linked List
    Author - @Lwin Phyo Aung
    put - O(N)
    get - O(N)
     */

import java.util.NoSuchElementException;

public class UnsortedLLST <K extends Comparable<K>, V>{

    private class Node{
        private K key;
        private V val;
        private Node next;
        private Node(K key, V val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public UnsortedLLST(){

    }

    public void put(K key, V val){
        for (Node cur = head; cur!=null; cur=cur.next){
            if (cur.key.compareTo(key)==0){
                cur.val = val;
                return;
            }
        }
        head = new Node(key, val, head);
        size++;
    }

    public V get(K key){
        for (Node cur = head; cur!=null; cur=cur.next){
            if (key.compareTo(cur.key)==0){
                return cur.val;
            }
        }
        return null;
    }

    public V remove(K key){
        if (head==null){
            throw new NoSuchElementException();
        }
        if (key.compareTo(head.key)==0){
            V res = head.val;
            head = head.next;
            size--;
            return res;
        }
        Node cur = head;
        while (cur.next!=null){
            if (key.compareTo(cur.next.key)==0){
                V res = cur.next.val;
                cur.next = cur.next.next;
                size--;
                return res;
            }
            cur=cur.next;
        }
        return null;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public boolean contains(K key){
        return get(key)!=null;
    }

    @Override
    public String toString() {
        String res = "";
        for (Node cur=head; cur!=null; cur=cur.next){
            res += " [" + cur.key + "," + cur.val + "] ";
        }
        return res;
    }
}
