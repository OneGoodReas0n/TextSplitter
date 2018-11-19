package com.reason.filesplitter.model;

public class FileInfo {
    
    private int id;
    private String name;
    private int linesNumber;
    private int wordNumber;
    private int symbolNumber;

    public FileInfo() {
    }

    public FileInfo(int id, String name, int linesNumber,int wordNumber, int symbolNumber) {
        this.id = id;
        this.name = name;
        this.linesNumber = linesNumber;
        this.wordNumber = wordNumber;
        this.symbolNumber = symbolNumber;
    }

    public FileInfo(String name) {
        this.name = name;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    public int getSymbolNumber() {
        return symbolNumber;
    }

    public void setSymbolNumber(int symbolNumber) {
        this.symbolNumber = symbolNumber;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLinesNumber() {
        return linesNumber;
    }

    public void setLinesNumber(int linesNumber) {
        this.linesNumber = linesNumber;
    }
    
    
}
