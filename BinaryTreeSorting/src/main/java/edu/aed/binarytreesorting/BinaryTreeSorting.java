package edu.aed.binarytreesorting;

import java.util.Arrays;

public class BinaryTreeSorting {

    public static void main(String[] args) {
        
        int[] data = {8, 20, 41, 7, 2};
        int h = (int) (Math.log(data.length) / Math.log(2)) + 1;
        int E = 42; 

        Node root = buildCompleteBinaryTree(data, h, E);
        System.out.println("Sorted array:");
        int[] sortedArray = extractSortedArray(root, data.length);
        System.out.println(Arrays.toString(sortedArray));
         
    }

    private static Node buildCompleteBinaryTree(int[] data, int height, int E) {
        Node root = new Node(data[0]);
        int n = data.length;

        for (int i = 1; i < n; i++) {
            insertElement(root, data[i], height, E);
        }

        return root;
    }

    private static void insertElement(Node root, int value, int height, int E) {
        Node current = root;
        int level = height - 2; // Start at second-to-last level
                
        while (level >= 0) {
            if (value < current.value ) {
                if (current.left == null) {
                    current.left = new Node(value);
                    current = current.left;
                } else {
                    current = current.left;
                }
            } 
            //Con esta condicion nos aseguramos de no repetir un nodo.
            else if (value == current.value){
                    break;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    current = current.right;
                } else {
                    current = current.right;
                }
            }

            level--;
        }
        /*
        if (current.left == null) {
            current.left = new Node(E);
        } else {
            current.right = new Node(E);
        }*/
    }

    private static int[] extractSortedArray(Node root, int n) {
        int[] sortedArray = new int[n];
        int index = 0;

        extractSortedArrayRec(root, sortedArray, index);

        return sortedArray;
    }

    private static int extractSortedArrayRec(Node root, int[] sortedArray, int index) {
        if (root == null) {
            return index;
        }
        
        index = extractSortedArrayRec(root.left, sortedArray, index);
        sortedArray[index++] = root.value;   
        index = extractSortedArrayRec(root.right, sortedArray, index);
        
        return index;
        
    }
}
