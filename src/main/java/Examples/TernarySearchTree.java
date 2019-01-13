package Examples;

import java.util.ArrayList;

/***
 * What is een ternary search tree?
 * Een ternary search tree heeft child nodes die geordend zijn als een binary search tree.
 *
 * Hoe werkt het?
 * Elke node in een ternary search tree heeft 3 kinderen.
 * Het linker kind heeft een pointer naar nodes met waardes die kleiner zijn dan de waarde van de huidige node.
 * Het midden kind heeft een pointer naar nodes met waardes die gelijk zijn aan de waarde van de huidige node.
 * Het rechter kind heeft een pointer naar nodes met waardes die groter zijn dan de waarde van de huidige node.
 *
 * Ook heeft elke node een veld om aan te geven wat voor een data erin zit en wordt er aangegeven of dit het laatste stuk is.

 */
public class TernarySearchTree {
    private TSTNode root;
    private ArrayList<String> al;

    /**
     * Constructor
     **/
    public TernarySearchTree() {
        root = null;
    }

    /**
     * function to insert for a word
     **/
    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    /**
     * function to insert for a word
     **/
    public TSTNode insert(TSTNode root, char[] word, int pointer) {
        if (root == null)
            root = new TSTNode(word[pointer]);

        if (word[pointer] < root.data)
            root.left = insert(root.left, word, pointer);
        else if (word[pointer] > root.data)
            root.right = insert(root.right, word, pointer);
        else {
            if (pointer + 1 < word.length)
                root.middle = insert(root.middle, word, pointer + 1);
            else
                root.isEnd = true;
        }
        return root;
    }

    /**
     * function to delete a word
     **/
    public void delete(String word) {
        delete(root, word.toCharArray(), 0);
    }

    /**
     * function to delete a word
     **/
    private void delete(TSTNode r, char[] word, int ptr) {
        if (r == null)
            return;

        if (word[ptr] < r.data)
            delete(r.left, word, ptr);
        else if (word[ptr] > r.data)
            delete(r.right, word, ptr);
        else {
            /** to delete a word just make isEnd false **/
            if (r.isEnd && ptr == word.length - 1)
                r.isEnd = false;

            else if (ptr + 1 < word.length)
                delete(r.middle, word, ptr + 1);
        }
    }

    /**
     * function to search for a word
     **/
    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    /**
     * function to search for a word
     **/
    private boolean search(TSTNode r, char[] word, int ptr) {
        if (r == null)
            return false;

        if (word[ptr] < r.data)
            return search(r.left, word, ptr);
        else if (word[ptr] > r.data)
            return search(r.right, word, ptr);
        else {
            if (r.isEnd && ptr == word.length - 1)
                return true;
            else if (ptr == word.length - 1)
                return false;
            else
                return search(r.middle, word, ptr + 1);
        }
    }

    /**
     * function to traverse tree
     **/
    private void traverse(TSTNode r, String str) {
        if (r != null) {
            traverse(r.left, str);

            str = str + r.data;
            if (r.isEnd)
                al.add(str);

            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);

            traverse(r.right, str);
        }
    }
}