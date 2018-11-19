package com.reason.filesplitter.logic;

import com.reason.filesplitter.model.LineInfo;
import com.reason.filesplitter.model.FileInfo;
import com.reason.filesplitter.service.FileService;
import com.reason.filesplitter.service.LineService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSplitter {

    private List<List<String>> listLines = new ArrayList<List<String>>();
    private List<File> files = new ArrayList<>();
    private FileService fileService = new FileService();
    private LineService lineService = new LineService();

    /**
     *
     * @param file
     * @return
     */
    public void splitAsync(File directory) {
        if (directory.exists()) {
            System.out.println("Directory exist");
            List<File> files = getAllFilesFromPath(directory);
            if (files.size() > 0) {
                for (File file : files) {
                    new Thread(() -> {
                        System.out.println("Process scanning...");
                        int lineCounter = 0, wordCounter = 0, symbolCounter = 0;
                        fileService.save(new FileInfo(file.getName()));
                        FileInfo textFile = fileService.getOneByName(file.getName());
                        if (file.length() > 0) {
                            List<List<String>> lines = getFileInfo(file);
                            for (List<String> line : lines) {
                                saveInfoOfLine(line, textFile);
                                for (String word : line) {
                                    wordCounter++;
                                    symbolCounter += word.length();
                                }
                            }
                            lineCounter = lines.size();
                        }
                        textFile.setLinesNumber(lineCounter);
                        textFile.setWordNumber(wordCounter);
                        textFile.setSymbolNumber(symbolCounter);
                        fileService.update(textFile);
                        System.out.println("Process writing...");
                    }).run();
                }
            }
        } else {
            System.out.println("Неверно заданная директория!");
        }
    }

    public void splitSync(File directory) {
        if (directory.exists()) {
            System.out.println("Directory exist");
            List<File> files = getAllFilesFromPath(directory);
            if (files.size() > 0) {
                for (File file : files) {
                    System.out.println("Process scanning...");
                    int lineCounter = 0, wordCounter = 0, symbolCounter = 0;
                    fileService.save(new FileInfo(file.getName()));
                    FileInfo textFile = fileService.getOneByName(file.getName());
                    if (file.length() > 0) {
                        List<List<String>> lines = getFileInfo(file);
                        for (List<String> line : lines) {
                            saveInfoOfLine(line, textFile);
                            for (String word : line) {
                                wordCounter++;
                                symbolCounter += word.length();
                            }
                        }
                        lineCounter = lines.size();
                    }
                    textFile.setLinesNumber(lineCounter);
                    textFile.setWordNumber(wordCounter);
                    textFile.setSymbolNumber(symbolCounter);
                    fileService.update(textFile);
                    System.out.println("Process writing...");
                }
            }
        } else {
            System.out.println("Неверно заданная директория!");
        }
    }

    /**
     *
     * @param line
     * @return
     */
    private void saveInfoOfLine(List<String> line, FileInfo textFile) {
        LineInfo info = getLineInfo(line, textFile);
        lineService.save(info);
    }

    private List<File> getAllFilesFromPath(File directory) {
        if (directory.exists()) {
            if (directory.isDirectory()) {
                for (File path : directory.listFiles()) {
                    getAllFilesFromPath(path);
                }
            }
            if (directory.isFile()) {
                files.add(directory);
            }
        } else {
            System.err.println("Неверный путь к директории!");
        }
        return files;
    }

    private List<List<String>> getFileInfo(File file) {
        List<List<String>> lines = new ArrayList<List<String>>();
        try (Scanner fileScan = new Scanner(file)) {
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();
                if (line.length() > 0) {
                    try (Scanner lineScan = new Scanner(line)) {
                        List<String> wordList = new ArrayList<>();
                        while (lineScan.hasNext()) {
                            String word = lineScan.next();
                            wordList.add(word);
                        }
                        lines.add(wordList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private LineInfo getLineInfo(List<String> line, FileInfo textFile) {
        int maxWordLength = 0, minWordLength = 0;
        int wordCounter = 0, summaryWordLength = 0;
        String textLine = "";
        for (String string : line) {
            textLine += string + " ";
            if (SymbolsPattern.checkOnPunctSymbols(string)) {
                string = SymbolsPattern.removePunctSymbols(string);
            }
            if (string.length() > 0) {
                if (wordCounter == 0) {
                    maxWordLength = string.length();
                    minWordLength = string.length();
                } else {
                    if (maxWordLength < string.length()) {
                        maxWordLength = string.length();
                    } else if (minWordLength > string.length()) {
                        minWordLength = string.length();
                    }

                }
                summaryWordLength += string.length();
                wordCounter++;
            }
        }
        return new LineInfo(textLine, maxWordLength, minWordLength, summaryWordLength,
                wordCounter, textFile.getId());
    }

}
