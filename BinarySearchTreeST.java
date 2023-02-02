package com.lpa.lesson;

    // TODO: 2/1/2023
    /*
    Symbol Table Implementation Using Binary Search Tree
    put - O(N)
    get - O(N)
     */

import java.util.NoSuchElementException;

public class BinarySearchTreeST<K extends Comparable<K>, V> {

    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int N;
        private Node(K key, V val){
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return " [" + key + "," + val + "] ";
        }
    }

    private Node root;
    private int size;

    public BinarySearchTreeST(){

    }

    public void put(K key, V val){
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val){
        if (x==null){
            size++;
            return new Node(key, val);
        }
        int comp = key.compareTo(x.key);
        if (comp<0){
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
    }

    public V get(K key){
        return get(root, key);
    }

    private V get(Node x, K key){
        System.out.println("get");
        if (x==null) return null;
        int comp = key.compareTo(x.key);
        if (comp<0){
            return get(x.left, key);
        } else if (comp > 0) {
            return get(x.right, key);
        } else return x.val;
    }

    public int size(){
        return size;
    }

    public boolean contains(K key){
        return get(key)!=null;
    }

    public K min(){
        if (root==null){
            throw new NoSuchElementException();
        }
        Node cur = root;
        while (cur.left!=null){
            cur = cur.left;
        }
        return cur.key;
    }

    public K max(){
        if (root==null){
            throw new NoSuchElementException();
        }
        Node cur = root;
        while (cur.right!=null){
            cur = cur.right;
        }
        return cur.key;
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node x){
        if (x!=null){
            inOrder(x.left);
            System.out.print(x);
            inOrder(x.right);
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node x){
        if (x!=null){
            System.out.print(x);
            preOrder(x.left);
            preOrder(x.right);
        }
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node x){
        if (x!=null){
            postOrder(x.left);
            postOrder(x.right);
            System.out.print(x);
        }
    }

}
