package com.console;

import com.console.reader.EpubReader;
import com.console.reader.HTMLContentExtractor;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        EpubReader reader = new EpubReader();
        BookListService bookListService = new BookListService();
        HTMLContentExtractor contentExtractor = new HTMLContentExtractor();
        Gui gui = new Gui(bookListService);
        String bookTitle = gui.bookSelection();
        File bookFile = bookListService.getFileWithName(bookTitle);
        reader.unzipFile(bookFile);
        contentExtractor.extractContentFromFile(System.getProperty("user.dir") + "/temporaryUnzipped/OEBPS/Text/9780062310712_Chapter_1.xhtml");
    }
}
