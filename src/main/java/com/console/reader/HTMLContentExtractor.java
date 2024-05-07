package com.console.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class HTMLContentExtractor {

    public void extractContentFromFile(String filePath) {
        try {
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");

            Elements paragraphs = doc.select("p");

            for (Element paragraph : paragraphs) {
                String paragraphText = paragraph.text();
                System.out.println(paragraphText);
            }

        } catch (IOException e) {
            System.err.println("Error reading HTML file: " + e.getMessage());
        }
    }
}
