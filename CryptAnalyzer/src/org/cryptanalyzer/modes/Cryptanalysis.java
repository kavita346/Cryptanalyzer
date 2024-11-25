package org.cryptanalyzer.modes;

public abstract class Cryptanalysis {
    private String sourseFile;

    public Cryptanalysis(String sourseFile){
        this.sourseFile=sourseFile;
    }

    public abstract void encrypt(int key);
    public abstract  void decrypt(int key);

    public  String getSourseFile(){
        return sourseFile;
    }

}
