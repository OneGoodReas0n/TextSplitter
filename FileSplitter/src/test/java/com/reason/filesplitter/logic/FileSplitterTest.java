/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reason.filesplitter.logic;

import com.reason.filesplitter.model.LineInfo;
import com.reason.filesplitter.model.FileInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reason
 */
public class FileSplitterTest {

    public FileSplitterTest() {
    }
    
    /**
     * Test of split method, of class FileSplitter.
     */
    @Test
    public void testGetFileInfo() {
        System.out.println("getFileInfo");
        File file = new File("test2.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo textFile = new FileInfo();
        List<List<String>> expResult = new ArrayList<List<String>>();
        List<List<String>> result = new ArrayList<List<String>>();
        FileSplitter splitter = new FileSplitter();
        Class cl = splitter.getClass();

        String str = "Herzliche wilcommen in Deutchland";
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(str.getBytes());
            Method getInfoMethod = cl.getDeclaredMethod("getFileInfo", File.class);
            getInfoMethod.setAccessible(true);
            result = (List<List<String>>) getInfoMethod.invoke(splitter, file);
            try (Scanner fileScan = new Scanner(file)) {
                while (fileScan.hasNextLine()) {
                    String line = fileScan.nextLine();
                    try (Scanner lineScan = new Scanner(file)) {
                        List<String> wordsInLine = new ArrayList<>();
                        while (lineScan.hasNext()) {
                            String word = lineScan.next();
                            wordsInLine.add(word);
                        }
                        expResult.add(wordsInLine);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
        assertEquals(expResult, result);
    }

    /**
     * Test of split method, of class FileSplitter.
     */
    @Test
    public void testGetLineInfo() {
        System.out.println("getLineInfo");
        String str = "Herzliche wilcommen in Deutchland";
        List<String> line = Arrays.asList(str.split(" "));
        FileInfo textFile = new FileInfo();
        LineInfo expResult = new LineInfo(str, 10, 2, 30, 4, textFile.getId());
        LineInfo result = new LineInfo();
        FileSplitter splitter = new FileSplitter();
        Class cl = splitter.getClass();
        try {
            Method getLineInfo = cl.getDeclaredMethod("getLineInfo", List.class, FileInfo.class);
            getLineInfo.setAccessible(true);
            result = (LineInfo) getLineInfo.invoke(splitter, line, textFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of split method, of class FileSplitter.
     */
    @Test
    public void testGetAllFilesFromPath() {
        System.out.println("getAllFilesFromPath");
        List<File> files = new ArrayList<>();
        File directory = new File("test");
        File deepDirectory = new File(directory, "deep");
        File file1 = new File(directory, "test.txt");
        File file2 = new File(deepDirectory, "test.txt");
        directory.mkdir();
        deepDirectory.mkdir();
        FileSplitter splitter = new FileSplitter();
        Class cl = splitter.getClass();
        try {
            file1.createNewFile();
            file2.createNewFile();

            Method getAllFilesFromPath = cl.getDeclaredMethod("getAllFilesFromPath", File.class);
            getAllFilesFromPath.setAccessible(true);
            files = (List<File>) getAllFilesFromPath.invoke(splitter, directory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        file1.delete();
        file2.delete();
        deepDirectory.delete();
        directory.delete();
        assertEquals(files.size(), 2);
    }

    /*@Test
    public void smallTest() {
        File directory = new File("dir");
        File testFile = new File(directory,"tesst.txt");
        File testFile2 = new File(directory,"tesst2.txt");
        List<File> files = new ArrayList<>();
        directory.mkdir();
        if (!testFile.exists()) {
            try {
                testFile.createNewFile();
                testFile2.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str = "Hello, world!";
        try (FileOutputStream out = new FileOutputStream(testFile)) {
            out.write(str.getBytes());
            if(directory.isDirectory()){
                for (File listFile : directory.listFiles()) {
                    files.add(listFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        testFile.delete();
        testFile2.delete();
        directory.delete();
        assertEquals(files.size(), 2);
    }*/

}
