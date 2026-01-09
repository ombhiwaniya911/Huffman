package com.huffman;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Usage: java Main <input.txt> <encoded.bin> <decoded.txt>");
            return;
        }

        String inputFile = args[0];
        String encodedFile = args[1];
        String decodedFile = args[2];

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileOutputStream fos = new FileOutputStream(encodedFile)) {

            StringBuilder text = new StringBuilder();
            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {
                if (!first) text.append("\n");
                text.append(line);
                first = false;
            }

            String[] code = new String[128];
            Node root = HuffmanEncoder.buildTree(text.toString(), code);
            String encoded = HuffmanEncoder.encode(text.toString(), code);

            for (int i = 0; i < encoded.length(); i += 8) {
                String byteStr = encoded.substring(i, Math.min(i + 8, encoded.length()));
                int value = Integer.parseInt(byteStr, 2);
                fos.write((byte) value);
            }

            // Decode
            String decoded = HuffmanDecoder.decode(encoded, root);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(decodedFile))) {
                bw.write(decoded);
            }

            System.out.println("Compression and Decompression successful!");

        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
    }
}
