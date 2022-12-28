import java.io.File;
import java.io.IOException;

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
 * The {@code Menu} class provides a command-line interface for interacting
 * with the {@code Encryption} and {@code Decryption} classes.
 *
 * The {@code Menu} class has two instance variables:
 * - {@code encryption}: an instance of the {@code Encryption} class, used for encrypting text
 * - {@code decryption}: an instance of the {@code Decryption} class, used for decrypting text
 *
 * The {@code Menu} class provides methods for displaying the menu options to the user, accepting user input, and
 * calling the appropriate methods in the {@code Encryption} and {@code Decryption} classes to perform the requested
 * operations.
 *
 * @author Beaudelaire Tsoungui Nzodoumkouo
 */
public class Menu {
   private Encryption encryption;
   private Decryption decryption;

    /**
     * When starting the program, the necessary information should be specified by the appropriate argument.
     * If there is no -alg argument, the default is shift algorithm.
     * If there is no -mode, the program work in the enc mode;
     * If there is no -key, the program consider that key is 0;
     * If there is no -data and no -in the program assumes that the data is an empty string;
     * If there is no -out argument, the program print data to the standard output;
     * If there are both -data and -in arguments,the program prefer -data over -in.
     * @param mode : a string indicating whether to perform encryption ("enc") or decryption ("dec")
     * @param alg : a string indicating the algorithm to use for the encryption or decryption ("unicode" or "shift")
     * @param data : a String indicating the data to be encrypted or decrypted (if {@code in} is an empty string)
     * @param in : a string containing the name of the input file (if provided)
     * @param out : a string containing the name of the output file (if provided)
     * @param key : an integer representing the key to use for the encryption or decryption
     * @throws IOException :  if there is an error reading or writing the files
     */

   public void choice(String mode, String alg, String data, String in, String out, int key) throws IOException {
       try {
           if (alg.equals("unicode")) {
               if (in.equals("")) {
                   switch (mode) {
                       case "enc" -> unicodeEnc(data, key);
                       case "dec" -> unicodeDec(data, key);
                   }
               } else {
                   switch (mode) {
                       case "enc" -> unicodeFileEnc(in, out, key);
                       case "dec" -> unicodeFileDec(in, out, key);
                   }
               }
           } else {
               if (in.equals("")) {
                   switch (mode) {
                       case "enc" -> shiftEnc(data, key);
                       case "dec" -> shiftDec(data, key);
                   }
               } else {
                   switch (mode) {
                       case "enc" -> shiftFileEnc(in, out, key);
                       case "dec" -> shiftFileDec(in, out, key);
                   }
               }
           }
       } catch (IOException e){
           System.out.println(e.getMessage());
       }
   }

    /**
     * Decrypts a file using a shift cipher and writes the decrypted content to a new file.
     *
     * @param in :the file to be decrypted
     * @param out :the file to write the decrypted content to
     * @param key :the key to use for decryption
     * @throws IOException if there is an error reading or writing the files
     */
    private void shiftFileDec(String in, String out, int key) throws IOException {
        this.decryption = new Decryption(new File(in), new File(out), key);
        System.out.print("The file was decrypted successfully with shift algorithm, \nYou can find the decryption in : " + this.decryption.getOutputFile().getName() );
        this.decryption.shiftFileDecryption();
    }

    /**
     * Encrypts a file using a shift cipher and writes the encrypted content to a new file.
     *
     * @param in :the file to be decrypted
     * @param out :the file to write the decrypted content to
     * @param key :the key to use for decryption
     * @throws IOException if there is an error reading or writing the files
     */
    private void shiftFileEnc(String in, String out, int key) throws IOException {
       this.encryption = new Encryption(new File(in), new File(out), key);
        System.out.print("The file was encrypted successfully with shift algorithm, \nYou can find the encryption in : " + this.encryption.getOutputFile().getName() );
        this.encryption.shiftFileEncryption();
    }

    /**
     * Decrypts the given data using the shift decryption method with the specified key.
     *
     * @param data the data to be decrypted
     * @param key the key to be used for decryption
     */
    private void shiftDec(String data, int key) {
       this.decryption = new Decryption(data,key);
        System.out.print("The data was decrypted successfully with shift algorithm, \nHere is the decryption : " );
       this.decryption.shiftTextDecryption();
    }

    /**
     * Encrypts the given data using the shift encryption method with the specified key.
     *
     * @param data the data to be decrypted
     * @param key the key to be used for decryption
     */
    private void shiftEnc(String data, int key) {
       this.encryption = new Encryption(data,key);
        System.out.print("The data was encrypted successfully with shift algorithm, \nHere is the encryption : " );
       this.encryption.shiftTextEncryption();
    }

    /**
     * Decrypts a file using a unicode cipher and writes the decrypted content to a new file.
     *
     * @param in :the file to be decrypted
     * @param out :the file to write the decrypted content to
     * @param key :the key to use for decryption
     * @throws IOException if there is an error reading or writing the files
     */
    private void unicodeFileDec(String in, String out, int key) throws IOException {
       this.decryption = new Decryption(new File(in), new File(out), key);
        System.out.print("The file was decrypted successfully with unicode algorithm, \nYou can find the decryption in : " + this.decryption.getOutputFile().getName() );
        this.decryption.unicodeFileDecryption();
    }

    /**
     * Encrypts a file using a unicode cipher and writes the encrypted content to a new file.
     *
     * @param in :the file to be decrypted
     * @param out :the file to write the decrypted content to
     * @param key :the key to use for decryption
     * @throws IOException if there is an error reading or writing the files
     */
    private void unicodeFileEnc(String in, String out, int key) throws IOException {
        this.encryption = new Encryption(new File(in), new File(out), key);
        System.out.print("The file was encrypted successfully with unicode algorithm, \nYou can find the encryption in : " + this.encryption.getOutputFile().getName() );
        this.encryption.unicodeFileEncryption();
    }

    /**
     * Decrypts the given data using the unicode decryption method with the specified key.
     *
     * @param data the data to be decrypted
     * @param key the key to be used for decryption
     */
    private void unicodeDec(String data, int key) {
        this.decryption = new Decryption(data,key);
        System.out.print("The data was decrypted successfully with unicode algorithm, \nHere is the decryption : " );
        this.decryption.unicodeTextDecryption();
    }

    /**
     * Encrypts the given data using the unicode encryption method with the specified key.
     *
     * @param data the data to be decrypted
     * @param key the key to be used for decryption
     */
    private void unicodeEnc(String data, int key) {
        this.encryption = new Encryption(data,key);
        System.out.print("The data was encrypted successfully with unicode algorithm, \nHere is the encryption : " );
        this.encryption.unicodeTextEncryption();
    }

    /**
     * Prints the list of available commands and their descriptions to the console.
     */
    public void printMenu(){
        System.out.println("For more information on a specific command, type help command-name.");
        System.out.println("-mode \t takes argument enc for encryption and dec for decryption.");
        System.out.println("-key \t takes an integer argument and is used to encrypt/decrypt message/file.");
        System.out.println("-data \t takes in a text to be encrypt/decrypt as argument pls used for \"\" for text with spaces.");
        System.out.println("-in \t takes as argument the relative/absolute path to the file you wish to encrypt/decrypt.");
        System.out.println("-out \t takes as argument the file you want to store your encrypted data (it also creates a new file.");
        System.out.println("-alg \t takes as argument unicode or shift, which are the algorithm used for encryption.");
    }
}