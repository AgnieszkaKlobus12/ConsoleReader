package com.console.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class HTMLContentExtractor {

    public void extractContentFromFile(String filePath) {
        try {
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");

            String textContent = doc.text();
            System.out.println(textContent);
        } catch (IOException e) {
            System.err.println("Error reading HTML file: " + e.getMessage());
        }
    }
}
