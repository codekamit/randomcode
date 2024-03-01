package com.learning.random;

import java.util.*;
import java.util.function.Supplier;

public class Main {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static Node getTree() {
        Node root = new Node(6);
        root.left = new Node(4);
        root.right = new Node(8);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(9);
        return root;
    }

    private static int nextNode(Node root) {
        if(root.left == null) {

        }


    }

    public static Node deleteNode(int value, Boolean status, Node root) {
        if(root == null) {
            return root;
        }
        if(root.value == value) {

        }
        else if(root.value < value) {

        }
        else {

        }
    }
    public static void main(String[] args) {
        Node root = getTree();
        add(root, 2);
        print(root);
    }
}
