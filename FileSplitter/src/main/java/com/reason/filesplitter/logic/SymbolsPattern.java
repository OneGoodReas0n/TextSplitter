package com.reason.filesplitter.logic;

public class SymbolsPattern {
    
    public static boolean checkOnPunctSymbols(String line){
        boolean result = false;
        if(line.matches(".*\\p{Punct}")){result = true;}
        if (line.matches("\\p{Punct}.*")){result = true;}
        return result ;
    }
    
    public static String removePunctSymbols(String line){
        line = line.replaceAll("^\\p{Punct}", "");
        line = line.replaceAll("\\p{Punct}$", "");
        return line;
    }
}
