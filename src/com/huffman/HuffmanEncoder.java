package com.huffman;

import java.util.*;

public class HuffmanEncoder {

    private static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    private static void dfs(Node temp, String st, String[] code) {
        if (isLeaf(temp)) {
            code[temp.ch] = st.length() > 0 ? st : "1";
            return;
        }
        dfs(temp.left, st + "0", code);
        dfs(temp.right, st + "1", code);
    }

    public static Node buildTree(String text, String[] code) {

        int[] freq = new int[128];
        for (char ch : text.toCharArray()) {
            freq[ch]++;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.freq)
        );

        for (int i = 0; i < 128; i++) {
            if (freq[i] > 0) {
                pq.add(new Node(i, freq[i]));
            }
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node merged = new Node(-1, left.freq + right.freq);
            merged.left = left;
            merged.right = right;

            pq.add(merged);
        }

        Node root = pq.poll();
        dfs(root, "", code);
        return root;
    }

    public static String encode(String text, String[] code) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encoded.append(code[ch]);
        }
        return encoded.toString();
    }
}
