package com.console;

import com.console.reader.EpubReader;
import com.console.reader.HTMLContentExtractor;
import com.console.utils.ChapterNamesComparator;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        EpubReader reader = new EpubReader();
        BookListService bookListService = new BookListService();
        HTMLContentExtractor contentExtractor = new HTMLContentExtractor();
        Gui gui = new Gui(bookListService, new ChapterNamesComparator());
        String bookTitle = gui.bookSelection();
        File bookFile = bookListService.getFileWithName(bookTitle);
        reader.unzipFile(bookFile);
        String chapter = gui.chapterSelection();
        contentExtractor.extractContentFromFile(chapter);
    }
}
