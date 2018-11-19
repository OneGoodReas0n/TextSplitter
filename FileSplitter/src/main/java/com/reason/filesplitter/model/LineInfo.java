package com.reason.filesplitter.model;

public class LineInfo {
    
    private int id;
    private String line;
    private int maxWordLength;
    private int minWordLength;
    private int lineLength;
    private int wordNumber;
    private double averageWordLength;
    private int fileId;

    public LineInfo() {
    }

    public LineInfo(String line,int maxWordLength, int minWordLength, int lineLength, int wordNumber, int fileId) {
        this.line = line;
        this.maxWordLength = maxWordLength;
        this.minWordLength = minWordLength;
        this.lineLength = lineLength;
        this.wordNumber = wordNumber;
        countAverageWordLength(lineLength, wordNumber);
        this.fileId = fileId;
    }
    
    public LineInfo(int id, String line,int maxWordLength, int minWordLength, int lineLength, double averageWordLength, int fileId) {
        this.id = id;
        this.line = line;
        this.maxWordLength = maxWordLength;
        this.minWordLength = minWordLength;
        this.lineLength = lineLength;
        this.averageWordLength = averageWordLength;
        this.fileId = fileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    public double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public void countAverageWordLength(int lineLength, int wordNumber) {
        this.averageWordLength = Math.round(((double)lineLength/(double)wordNumber));
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    
    

    @Override
    public String toString() {
        return "MaxWordLength: "+maxWordLength +" MinWordLength: "+ minWordLength+
                " LineLength: "+ lineLength + " AverageWordLength: "+ averageWordLength;
    }

    @Override
    public boolean equals(Object o) {
        if(this == null || getClass()!=o.getClass()){return false;}
        if(this == o) {return true;}
        
        LineInfo info = (LineInfo) o;
        return this.averageWordLength == info.averageWordLength &&
                this.lineLength == info.lineLength &&
                this.maxWordLength == info.maxWordLength &&
                this.minWordLength == info.minWordLength;
    }

    @Override
    public int hashCode() {
        return this.lineLength * (int)this.averageWordLength * this.maxWordLength * this.minWordLength;
    }
        
    
}
