package com.lpa.lesson;

    // TODO: 2/1/2023
    /*
    Symbol Table Implementation Using Red-Black Tree(Balanced Search Tree)
    put - O(logN)
    get - O(logN)
     */

import java.util.NoSuchElementException;

public class RedBlackST <K extends Comparable<K>, V>{

    private class Node{

        private K key;
        private V val;
        private Node left;
        private Node right;
        private boolean color;
        private Node(K key, V val, boolean color){
            this.key = key;
            this.val = val;
            this.color = color;
        }

        @Override
        public String toString() {
            return " [" + key + "," + val + "] ";
        }
    }

    private Node root;
    private int size;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public RedBlackST(){

    }

    public void put(K key, V val){
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val){
        if (x==null){
            size++;
            return new Node(key, val, RED);
        }
        int comp = key.compareTo(x.key);
        if (comp<0){
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        if (!isRed(x.left) && isRed(x.right)){
            x = rotateLeft(x);
        } if (isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        } if (isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }
        return x;
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public V get(K key){
        return get(root, key);
    }

    private V get(Node x, K key){
        System.out.println("loop");
        if (x==null) return null;
        int comp = key.compareTo(x.key);
        if (comp<0){
            return get(x.left, key);
        } else if (comp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    private boolean isRed(Node x){
        if (x==null){
            return false;
        }
        return x.color==RED;
    }

    public int size(){
        return size;
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
