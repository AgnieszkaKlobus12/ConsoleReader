package com.console.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LastReadService {

    private static final String FILE = "lastRead.txt";

    public String lastBookTitle() {
        String bookTitle = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            bookTitle = reader.readLine();
            System.out.println("First Line: " + bookTitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookTitle;
    }

    public String lastChapter() {
        String chapterName = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            reader.readLine();
            chapterName = reader.readLine();
            System.out.println("First Line: " + chapterName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chapterName;
    }

    public void updateLastRead(final String bookName, final String chapterName){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            writer.write(bookName);
            writer.newLine();
            writer.write(chapterName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
