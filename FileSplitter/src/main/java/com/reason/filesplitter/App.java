package com.reason.filesplitter;

import com.reason.filesplitter.logic.FileSplitter;
import java.io.File;
import java.util.Timer;

public class App {

    public static void main(String[] args) {

        FileSplitter splitter = new FileSplitter();
        File directory = new File("files");
        splitter.splitAsync(directory);
    }
}
