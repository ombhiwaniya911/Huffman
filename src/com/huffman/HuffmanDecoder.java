package com.huffman;

public class HuffmanDecoder {

    public static String decode(String binary, Node root) {
        StringBuilder decoded = new StringBuilder();
        Node temp = root;

        for (char bit : binary.toCharArray()) {
            if (bit == '0') {
                temp = temp.left;
            } else {
                temp = temp.right;
            }

            if (temp.left == null && temp.right == null) {
                decoded.append((char) temp.ch);
                temp = root;
            }
        }
        return decoded.toString();
    }
}
