import java.io.*;
/*
 * Copyright (c) 2022 Beaudelaire Tsoungui Nzodoumkouo. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under My consent.
 *
 * This code is shared on GitHub in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Please contact Me at +1 438 509 3906
 * or LinkedIn: https://www.linkedin.com/in/beaudelaire-tsoungui-nzodoumkouo-809744231
 * if you need additional information or have any questions.
 */

/**
 * The {@code Decryption} class provides a simple implementation of  a character based
 * decryption algorithms.
 *
 * The {@code Decryption} class has three instance variables:
 *  - {@code text}: a string representing the text to be decrypted
 *  - {@code inputFile}: a {@code File} object representing the input file containing the text to be decrypted
 *  - {@code outputFile}: a {@code File} object representing the output file to which the decrypted text will be written
 *  - {@code key}: an integer which represents the key value used to decrypt the text.
 *
 * The {@code Decryption} class provides methods for decrypting text using the key value, as well as
 * methods for reading text from files and writing decrypted text to files.
 *
 *@author Beaudelaire Tsoungui Nzodoumkouo
 */
public class Decryption {
    private String text;
    private File inputFile;
    private File outputFile;
    private final int key;

    /**
     * Decrypts the given text using the given key.
     *
     * @param text the text to decrypt
     * @param key the key used to decrypt the text
     */
    public Decryption(String text, int key){
        this.text = text;
        this.key = key;
    }

    /**
     * Decrypts the contents of the input file and writes the resulting decrypted text to the output file.
     *
     * @param inputFile  the input file containing the encrypted text
     * @param outputFile the output file to write the decrypted text to
     * @param key        the key used to decrypt the text
     */
    public Decryption(File inputFile, File outputFile, int key){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.key = key;
    }

    /**
     * @return the text stored in this object
     */
    public String getText(){
        return this.text;
    }

    /**
     * @return the input file for this object
     */
    public File getInputFile() {
        return inputFile;
    }

    /**
     * @return the output file for this object
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * @return decryption key for this object
     */
    public int getKey() {
        return key;
    }

    /**
     * Decrypts a string of text that has been encrypted using the unicodeTextEncryption method.
     */
    public void unicodeTextDecryption(){
        StringBuilder printable = new StringBuilder(); // create a new StringBuilder to hold the decrypted text
        // loop through each character in the text
        for (int i = 0; i < this.text.length(); i++) {
            char c = this.text.charAt(i);
            if(Character.isLetter(c)){ // check if the character is a letter
                if (Character.isUpperCase(c)){ // check if the letter is uppercase
                    c = (char) (((c - 'A' - this.key + 26) % 26 + 'A'));  // shift the uppercase letter according to the key
                } else {
                    c = (char) (((c - 'a' - this.key + 26) % 26 + 'a')); // shift the lowercase letter according to the key
                }
            }
            printable.append (c); // append the decrypted character to the decrypted text
        }
        System.out.print(printable); // print the decrypted text
    }

    /**
     * Decrypts the text stored in this object using a shift cipher with the specified key.
     *
     * <p>The shift cipher works by shifting each letter in the text by a certain number of positions in the alphabet.
     * For example, a key of 1 would shift 'a' to 'b', 'b' to 'c', and so on.
     *
     * <p>This method handles both uppercase and lowercase letters, and wraps around to the beginning of the alphabet if necessary.
     * Non-letter characters are left unchanged.
     */
    public void shiftTextDecryption() {
        StringBuilder printable = new StringBuilder(); // create a new StringBuilder to hold the decrypted text
        for (int i = 0; i < this.text.length(); i++) {
                // check if the character is a lowercase letter
            if (this.text.charAt(i) > (char) (('a' - 1) + this.key) && this.text.charAt(i) <= 'z'
                    // or if the character is an uppercase letter
                    || this.text.charAt(i) > (char) ( ('A' - 1) + this.key ) && this.text.charAt(i) <= 'Z') {

                printable.append( (char) (this.text.charAt(i) - this.key) );

                // check if the character is an uppercase letter that has been shifted past 'A'
            } else if ( this.text.charAt(i) >= 'A' && this.text.charAt(i) <= (char) ( ('A' - 1) + this.key ) ) {

                // if the character is an uppercase letter that has been shifted past 'A', wrap it around to the end of the alphabet
            	printable.append( (char) ( ('Z' - ( ('A'- 1) - (this.text.charAt(i) - this.key) ) ) ) );

                // check if the character is a lowercase letter that has been shifted past 'a'
            } else if (this.text.charAt(i) >= 'a' && (this.text.charAt(i) - this.key) <= (char) ( ('a' - 1) + this.key ) ) {

                // if the character is a lowercase letter that has been shifted past 'a', wrap it around to the end of the alphabet
                printable.append( (char) ( ('z' - ( ('a'- 1) - (this.text.charAt(i) - this.key) ) ) ) );

            } else {
                // if the character is not a letter, leave it unchanged
                printable.append( this.text.charAt(i) );
            }
        }
        // print the decrypted text to the console
        System.out.println(printable);
    }

    /**
     * Decrypts the contents of an input file using the specified key and writes the decrypted text to an output file.
     * If no output file is specified, the decrypted text is printed to the console.
     *
     * @throws IOException if there is an error reading or writing the input or output file.
     */
    public void unicodeFileDecryption() throws IOException {
        // create a new File object for the output file
        File output = new File(this.inputFile.getPath());

        // read the input file into a string
        String encrypted = FileIntoStringFormat(output);

        // create a FileWriter for the output file
        FileWriter decryptedFile = new FileWriter(this.outputFile.getPath());

        // unicode decryption algorithm
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            if(Character.isLetter(c)){ // check if the character is a letter
                if (Character.isUpperCase(c)){ // check if the letter is uppercase
                    c = (char) (((c - 'A' - this.key + 26) % 26 + 'A'));  // shift the uppercase letter according to the key
                } else {
                    c = (char) (((c - 'a' - this.key + 26) % 26 + 'a')); // shift the lowercase letter according to the key
                }
            }
            decryptedFile.append (c); // append the decrypted character to the decrypted text
        }

        // check if the output file name is empty
        if(this.outputFile.getPath().equals("")) {

            // if the output file name is empty, print the decrypted text to the console
            System.out.println(FileIntoStringFormat(this.outputFile));
        }

        // close the FileWriter
        decryptedFile.close();
    }

    /**
     * Decrypts the text in the input file using the specified key and writes the decrypted text to the output file.
     *
     * @throws IOException if there is an error reading or writing to the input or output file
     */
    public void shiftFileDecryption() throws IOException {
        // create a new File object for the output file
        File output = new File(this.inputFile.getPath());

        // read the input file into a string
        String encrypted = FileIntoStringFormat(output);

        // create a FileWriter for the output file
        FileWriter decryptedFile = new FileWriter(this.outputFile.getPath());

        // shift decryption algorithm
        for (int i = 0; i < encrypted.length(); i++) {

            if (encrypted.charAt(i) > (char) ( ('a' - 1) + this.key ) && encrypted.charAt(i) <= 'z'
                    || encrypted.charAt(i) > (char) ( ('A' - 1) + this.key ) && encrypted.charAt(i) <= 'Z') {

                decryptedFile.append( ((char) (encrypted.charAt(i) - this.key)) );

            } else if (encrypted.charAt(i) >= 'A' && encrypted.charAt(i) <= (char) ( ('A' - 1) + this.key)){

                decryptedFile.append( (char) ( ('Z' - ( ('A'- 1) - (encrypted.charAt(i) - this.key) ) ) ) );

            } else if (encrypted.charAt(i) >= 'a' && (encrypted.charAt(i) - this.key) <= (char) ( ('a' - 1) + this.key)){

                decryptedFile.append( (char) ( ('z' - ( ('a'- 1) - (encrypted.charAt(i) - this.key) ) ) ) );

            } else {
                decryptedFile.append( encrypted.charAt(i) );
            }
        }

        // check if the output file name is empty
        if(this.outputFile.getPath().equals("")) {

            // if the output file name is empty, print the decrypted text to the console
            System.out.println(FileIntoStringFormat(this.outputFile));
        }

        // close the FileWriter
        decryptedFile.close();
    }

    /**
     * Converts the contents of a file into a string.
     *
     * @param file the file to be converted
     * @return the contents of the file as a string
     * @throws IOException if there is an error reading the file
     */
    private static String FileIntoStringFormat(File file) throws IOException {
        FileReader fr = new FileReader(file); // create a FileReader for the specified file

        BufferedReader br = new BufferedReader(fr); // create a BufferedReader for the FileReader

        String Line; // variable to hold each line of the file as it is read

        StringBuilder Builder = new StringBuilder();

        // read each line of the file until there are no more lines to read
        while ((Line = br.readLine()) != null) {
            // append the current line to the StringBuilder, including a newline character
            Builder.append(Line).append("\n");
        }
        // close the FileReader and BufferedReader
        fr.close();
        br.close();

        // return the contents of the file as a single String
        return Builder.toString();
    }

}