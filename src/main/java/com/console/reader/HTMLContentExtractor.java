package com.console.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.File;
import java.io.IOException;

public class HTMLContentExtractor {

    public void extractContentFromFile(String filePath) {
        try {
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");
            printTextNodes(doc);
        } catch (IOException e) {
            System.err.println("Error reading HTML file: " + e.getMessage());
        }
    }

    private static void printTextNodes(Node node) {
        if (node instanceof TextNode) {
            if (!((TextNode) node).isBlank()) {
                System.out.println(((TextNode) node).text());
            }
        } else {
            for (Node child : node.childNodes()) {
                printTextNodes(child);
            }
        }
    }
}
