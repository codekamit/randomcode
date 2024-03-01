package com.learning.random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalancedBinarySearchTree {
    private BalancedBinarySearchTree left;
    private BalancedBinarySearchTree right;
    private int value;

    public BalancedBinarySearchTree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    private BalancedBinarySearchTree addNode(BalancedBinarySearchTree root, BalancedBinarySearchTree node) {
        if(root == null) {
            return node;
        }
        if(root.value == node.value) {
            return root;
        }
        else if(root.value < node.value) {
            root.right = addNode(root.right, node);
        }
        else {
            root.left = addNode(root.left, node);
        }
        return root;
    }

    private BalancedBinarySearchTree deleteNode(BalancedBinarySearchTree root, int value) {
        if(root == null) {
            return root;
        }
        if(root.value == value) {
            if(root.left != null) {
                BalancedBinarySearchTree temp = root.left;
                while(temp)
            }
        }
        else if(root.value < value) {
            root.right = deleteNode(root.right, value);
        }
        else {
            root.left = deleteNode(root.left, value);
        }
    }

    public void delete(int value) {

    }
    public void add(int value) {
        BalancedBinarySearchTree root = this;
        BalancedBinarySearchTree node = new BalancedBinarySearchTree(value);
        addNode(root, node);
    }
}
