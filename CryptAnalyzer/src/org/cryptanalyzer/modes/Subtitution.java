package org.cryptanalyzer.modes;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public  class Subtitution extends Cryptanalysis{

    private final String ALPHABET;
    private final String SUBSTITUTION;

    private final Map<Character,Character> ENCODE_MAP;
    private final Map<Character,Character> DECODE_MAP;

    //whenever the subtitution class object is created this object will executed before that object creation because its non static block
    {
        ALPHABET = " .abcdefghijklmnopqrstuvwxyz,ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SUBSTITUTION = "MNBVCXZLKJHGFDSAQWERTYUIOP .,lkjhgfdsazxcvbnmpoiuytrewq";
        DECODE_MAP = new HashMap<>();
        ENCODE_MAP = new HashMap<>();

        for (int i = 0; i < ALPHABET.length(); i++) {
            ENCODE_MAP.put(ALPHABET.charAt(i), SUBSTITUTION.charAt(i));
            DECODE_MAP.put(SUBSTITUTION.charAt(i),ALPHABET.charAt(i));
        }
    }
    public Subtitution(String sourseFile) {
        super(sourseFile);
    }

    @Override
    public void encrypt(int key) {
        String sourseFile = getSourseFile();
        String resultingFile="E:\\Users\\SAINATH\\eclipse-workspace\\CryptAnalyzer\\src\\org\\cryptanalyzer\\files\\ResultingFile.txt";

        try (FileReader fileReader = new FileReader(sourseFile);
             FileWriter writer = new FileWriter(resultingFile)) {
            int i=0;
            while ((i= fileReader.read()) != -1){
                char iTochar = (char) i;
                char charcterInFile= ENCODE_MAP.containsKey(iTochar) ? ENCODE_MAP.get(iTochar) : iTochar;
                writer.write(charcterInFile);
            }
            System.out.println("Your data is encrypted. you can check in:- "+ Path.of(resultingFile));
        }catch (IOException e){
        throw new RuntimeException();
        }
        }

    @Override
    public void decrypt(int key) {
        String sourseFile= getSourseFile();
        String resultingFiles="E:\\Users\\SAINATH\\eclipse-workspace\\CryptAnalyzer\\src\\org\\cryptanalyzer\\files\\ResultingFile.txt";

        try(FileReader fileReader = new FileReader(sourseFile);
        FileWriter fileWriter = new FileWriter(resultingFiles)){
            int i =0;
            while((i=fileReader.read()) != -1){
                char iTochar =(char)i;
                char characterInFile = DECODE_MAP.containsKey(iTochar) ? DECODE_MAP.get(iTochar):iTochar;
                fileWriter.write(characterInFile);
            }
            System.out.println("Your data is decrypted. You can check in:- " + Path.of(resultingFiles));
        }catch (IOException e){
            throw new RuntimeException();
        }

        }
    }

