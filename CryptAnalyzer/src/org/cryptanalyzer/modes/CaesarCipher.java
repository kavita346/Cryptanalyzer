package org.cryptanalyzer.modes;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CaesarCipher extends Cryptanalysis{
//    implements decryption functionality
    public CaesarCipher(String sourseFile)
    {
        super(sourseFile);
    }

    @Override
    public void encrypt(int key) {
        String sourseFile=this.getSourseFile();
        String destinationFile ="E:\\Users\\SAINATH\\eclipse-workspace\\CryptAnalyzer\\src\\org\\cryptanalyzer\\files\\ResultingFile.txt";
        //Reading data from source provided by the user and writing it in the destination file.
        try(FileReader fileReader= new FileReader(sourseFile);
            FileWriter fileWriter=new FileWriter(destinationFile)){
            int data;
            while ((data=fileReader.read()) != -1){
                fileWriter.write((char)(data+key));
            }
            System.out.println("Your data is encrypted. You can check in:- "+ Path.of(destinationFile));
        } catch (IOException e) {
            System.err.println("An error occurred during encryption: " + e.getMessage());
        }
        }

    @Override
    public void decrypt(int key) {
        String sourceFile = this.getSourseFile();
        String destinationFile = "E:\\Users\\SAINATH\\eclipse-workspace\\CryptAnalyzer\\src\\org\\cryptanalyzer\\files\\ResultingFile.txt";
        //Reading data from source provided by the user and writing it in the destination file.
        try(FileReader fileReader = new FileReader(sourceFile);
            FileWriter fileWriter = new FileWriter(destinationFile))
        {
            int data;
            while ((data = fileReader.read()) != -1)
            {
                fileWriter.write((char) (data - key));
            }
            System.out.println("Your data is decrypted. You can check in:- " + Path.of(destinationFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
