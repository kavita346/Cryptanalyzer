import org.cryptanalyzer.Exceptions.IllegalMethodException;
import org.cryptanalyzer.modes.CaesarCipher;
import org.cryptanalyzer.modes.Cryptanalysis;
import org.cryptanalyzer.modes.Subtitution;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking file path as input
        System.out.println("Hello, this is a program for 'Cryptanalysis'.");
        System.out.print("Please enter your .txt file's Absolute Path:- ");
        try {
            String sourceFile = scanner.nextLine();
            if(!Files.isRegularFile(Path.of(sourceFile))){
                System.out.println("The path provided is not correct. Please retry!!!");
                System.exit(0);
            }

            // Asking for the cryptanalysis mode
            System.out.println("Which of the following techniques you want to use (1-2):-\n1. Substitution\n2. CaesarCipher");
            int optionSelected = scanner.nextByte();
            scanner.nextLine();

            // Creating object of the selected cryptanalysis mode
            Cryptanalysis mode;
            mode = switch (optionSelected) {
                case 1 -> new Subtitution(sourceFile);
                case 2 -> new CaesarCipher(sourceFile);
                default -> throw new IllegalMethodException("Illegal mode selected. Select from 1 and 2 only.");
            };

            // Asking for choice (Encrypt or Decrypt)
            System.out.println("What do you want to do? (1 or 2)\n1. Encrypt\n2. Decrypt");
            byte choice = scanner.nextByte();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    if (mode instanceof CaesarCipher) {
                        System.out.println("Please enter the key (1-100): ");
                        int key = scanner.nextInt();
                        mode.encrypt(key);
                    } else {
                        mode.encrypt(0);//providing default key 0 for substitution.
                    }
                }
                case 2 -> {
                    if (mode instanceof CaesarCipher) {
                        System.out.println("Please enter the key (1-100): ");
                        int key = scanner.nextInt();
                        mode.decrypt(key);
                    } else {
                        mode.decrypt(0);
                    }
                }
                default -> throw new IllegalMethodException("Illegal choice selected. Must be 1 or 2.");
            }

        } catch (IllegalMethodException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
