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
 * <B>Note:
 * The shift algorithm doesn't handle keys greater than 26 as they are outside the range of
 * the alphabet thus a key like 30 will result in bad encryption and hence bad decryption of the
 * data or file.</B>
 */

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize a Menu object
        Menu menu = new Menu();

        // Initialize variables to hold the command-line arguments
        String mode = "enc";
        String data = "";
        String in = "";
        String out = "";
        String alg ="";
        int key = 0;

        // Parse the command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> data = args[i + 1];
                case "-in" -> in = args[i + 1];
                case "-out" -> out = args[i + 1];
                case "-alg" -> alg = args[i + 1];
                case "help" -> menu.printMenu();
            }
        }

        // Call the choice method of the Menu object with the parsed arguments
        menu.choice(mode,alg,data,in,out,key);

    }
}