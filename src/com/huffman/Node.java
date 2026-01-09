package com.huffman;

public class Node {
    int ch;
    int freq;
    Node left, right;

    public Node(int ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
}
