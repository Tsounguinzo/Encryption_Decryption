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
 * The {@code Encryption} class provides a simple implementation of  a character based
 * encryption algorithms.
 *
 * The {@code Encryption} class has three instance variables:
 *  - {@code text}: a string representing the text to be encrypted
 *  - {@code inputFile}: a {@code File} object representing the input file containing the text to be encrypted
 *  - {@code outputFile}: a {@code File} object representing the output file to which the encrypted text will be written
 *  - {@code key}: an integer which represents the key value used to
 *  encrypt the text.
 *
 * The {@code Encryption} class provides methods for encrypting text using the key value, as well as
 * methods for reading text from files and writing encrypted text to files.
 *
 * @author Beaudelaire Tsoungui Nzodoumkouo
 */
public class Encryption {
    private String text;
    private File inputFile;
    private File outputFile;
    private final int key;

    /**
     * Encrypts the given text using the given key.
     *
     * @param text the text to encrypt
     * @param key the key used to encrypt the text
     */
    public Encryption(String text, int key){
        this.text = text;
        this.key = key;
    }

    /**
     * Encrypts the contents of the input file and writes the resulting encrypted text to the output file.
     *
     * @param inputFile  the input file containing the text
     * @param outputFile the output file to write the encrypted text to
     * @param key        the key used to encrypt the text
     */
    public Encryption(File inputFile, File outputFile, int key){
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
     * @return encryption key for this object
     */
    public int getKey() {
        return key;
    }

    /**
     * Encrypts a string of text using unicode algorithm.
     */
    public void unicodeTextEncryption(){
        StringBuilder printable = new StringBuilder();// Create a StringBuilder to store the encrypted text
        // Iterate over each character in the text
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) { // check if the character is a letter
                if (Character.isUpperCase(c)) { // check if the letter is uppercase
                    c = (char) (((c - 'A' + key) % 26) + 'A'); // shift the uppercase letter according to the key
                } else {
                    c = (char) (((c - 'a' + key) % 26) + 'a'); // shift the lowercase letter according to the key
                }
            }
            printable.append(c); // append the encrypted character to the encrypted text
        }
        System.out.println(printable); // print the encrypted text
    }

    /**
     *A method for performing a simple form of encryption on a piece of text.
     *
     * The method uses the value of the key field to shift each character in the text field a certain number of places
     * in the alphabet. Non-alphabetic characters are left unchanged.
     *
     */
    public void shiftTextEncryption() {
        StringBuilder printable = new StringBuilder(); // create a new StringBuilder to hold the encrypted text

        // Iterate through each character in the  text
        for (int i = 0; i < this.text.length(); i++) {
            // check if the character is between 'a' and ('z' - this.key)
            if (this.text.charAt(i) >= 'a' && this.text.charAt(i) <= (char) ('z' - this.key)
                    // or if the character is between 'A' and ('Z' - this.key)
                    || this.text.charAt(i) >= 'A' && this.text.charAt(i) <= (char) ('Z' - this.key)) {

                // Shift the character by the value of the instance variable "key"
                printable.append( (char) (this.text.charAt(i) + this.key) );

                // check if the character is above Z in the ASCII code
            } else if ( (char) (this.text.charAt(i) + this.key) > 'Z') {

                // Shift the character back within the range of 'A' to 'Z'
            	printable.append( (char)( ( (this.text.charAt(i) + this.key) - 'Z') + ('A'-1) ) );

                // check if the character is above z in the ASCII code
            } else if ( (char) (this.text.charAt(i) + this.key) > 'z') {

                // Shift the character back within the range of 'a' to 'z'
                printable.append( (char)( ( (this.text.charAt(i) + this.key) - 'z') + ('a'-1) ) );

                // If the character is not in any of the ranges specified above
            } else {

                // Append the character to the StringBuilder object without modifying it
                printable.append( this.text.charAt(i) );
            }

        }
        // Print out the encrypted text stored in the StringBuilder object
        System.out.println(printable);
    }

    /**
     * Encrypts the contents of an input file using a simple Unicode-based algorithm and writes the resulting
     * encrypted text to an output file.
     *
     * @throws IOException if an I/O error occurs while reading from or writing to the input or output file
     */
    public void unicodeFileEncryption() throws IOException {
        // Create a File object for the input file
        File input = new File(this.inputFile.getPath());

        // Read the contents of the input file into a String
        String toBeEncrypted = FileIntoStringFormat(input);

        // Create a FileWriter for the output file
        FileWriter protectedFile = new FileWriter(this.outputFile.getPath());

        // Iterate over each character in the input file's contents and write the encrypted version to the output file
        for (int i = 0; i < toBeEncrypted.length(); i++) {
            char c = toBeEncrypted.charAt(i);
            if (Character.isLetter(c)) { // check if the character is a letter
                if (Character.isUpperCase(c)) { // check if the letter is uppercase
                    c = (char) (((c - 'A' + this.key) % 26) + 'A'); // shift the uppercase letter according to the key
                } else {
                    c = (char) (((c - 'a' + this.key) % 26) + 'a'); // shift the lowercase letter according to the key
                }
            }
            protectedFile.append(c); // append the encrypted character to the encrypted text
        }

        // check if the output file name is empty
        if(this.outputFile.getPath().equals("")) {

            // if the output file name is empty, print the decrypted text to the console
            System.out.println(FileIntoStringFormat(this.inputFile));
        }

        // close the FileWriter
        protectedFile.close();
    }

    /**
     * Encrypts the contents of a file using a simple substitution cipher.
     *
     * @throws IOException if an I/O error occurs while opening or writing to the output file
     */

    public void shiftFileEncryption() throws IOException {
        // Create a File object for the input file using the name stored in the instance variable "inputFile"
        File input = new File(this.inputFile.getPath());

        // Convert the contents of the input file into a String object using the method "FileIntoStringFormat()"
        String toBeEncrypted = FileIntoStringFormat(input);

        // Create a FileWriter object for the output file using the name stored in the instance variable "outputFile"
        FileWriter protectedFile = new FileWriter(this.outputFile.getPath());

        //shift encryption algorithm
        for (int i = 0; i<toBeEncrypted.length(); i++) {

            if (toBeEncrypted.charAt(i) >= 'a' && toBeEncrypted.charAt(i) <= (char) ('z' - this.key)
                    || toBeEncrypted.charAt(i) >= 'A' && toBeEncrypted.charAt(i) <= (char) ('Z' - this.key)) {

                protectedFile.append( (char) (toBeEncrypted.charAt(i) + this.key) );

            } else if((char)(toBeEncrypted.charAt(i) + this.key) > 'Z'){

                protectedFile.append( (char)( ( (toBeEncrypted.charAt(i) + this.key) - 'Z') + ('A'-1) ) );

            } else if((char)(toBeEncrypted.charAt(i) + this.key) > 'z'){

                protectedFile.append( (char)( ( (toBeEncrypted.charAt(i) + this.key) - 'z') + ('a'-1) ) );

            } else {
                protectedFile.append( toBeEncrypted.charAt(i) );
            }
        }

        // If the output file has the default name
        if(this.outputFile.getPath().equals("")) {
            // Print the contents of the protectedFile object to the console
            System.out.println(FileIntoStringFormat(this.inputFile));
        }
        // Close the FileWriter object for the output file
        protectedFile.close();
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