package com.console;

import com.console.reader.EpubReader;
import com.console.reader.HTMLContentExtractor;
import com.console.utils.ChapterNamesComparator;
import com.console.utils.LastReadService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gui {

    private final HTMLContentExtractor contentExtractor;
    private final LastReadService lastReadService;
    private final EpubReader reader;
    private final BookListService bookListService;
    private final ChapterNamesComparator chapterNamesComparator;

    public Gui(BookListService bookListService, ChapterNamesComparator chapterNamesComparator,
               EpubReader reader, LastReadService lastReadService, HTMLContentExtractor contentExtractor) {
        this.bookListService = bookListService;
        this.chapterNamesComparator = chapterNamesComparator;
        this.reader = reader;
        this.lastReadService = lastReadService;
        this.contentExtractor = contentExtractor;
    }

    public void start() {
        System.out.println();
        System.out.println("Pick your action: ");
        System.out.println("0: Read next chapter");
        System.out.println("1: Book selection");
        System.out.println("2: End");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 0: {
                List<String> chapters = new ArrayList<>(bookListService.listChaptersInDirectory(System.getProperty("user.dir") + "/temporaryUnzipped"));
                chapters.sort(chapterNamesComparator);
                String nextChapter = chapters.get(chapters.indexOf(lastReadService.lastChapter()) + 1);
                String book = lastReadService.lastBookTitle();
                contentExtractor.extractContentFromFile(nextChapter);
                lastReadService.updateLastRead(book, nextChapter);
                start();
                break;
            }
            case 1: {
                String bookTitle = bookSelection();
                File bookFile = bookListService.getFileWithName(bookTitle);
                reader.unzipFile(bookFile);
                String chapter = chapterSelection();
                contentExtractor.extractContentFromFile(chapter);
                lastReadService.updateLastRead(bookTitle, chapter);
                start();
                break;
            }
            default:
                break;
        }
    }

    public String bookSelection() {
        List<String> titles = bookListService.listBookFilesInDirectory(System.getProperty("user.dir") + "/books")
                .stream()
                .toList();
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ". " + titles.get(i));
        }
        System.out.print("Select book to open. \nNumber: ");
        Scanner scanner = new Scanner(System.in);
        return titles.get(scanner.nextInt());
    }

    public String chapterSelection() {
        List<String> chapters = new ArrayList<>(bookListService.listChaptersInDirectory(System.getProperty("user.dir") + "/temporaryUnzipped"));
        chapters.sort(chapterNamesComparator);
        for (int i = 0; i < chapters.size(); i++) {
            System.out.println(i + ". " + removeExtension(chapters.get(i)));
        }
        System.out.print("Select chapter to open. \nNumber: ");
        Scanner scanner = new Scanner(System.in);
        return chapters.get(scanner.nextInt());
    }

    private static String removeExtension(String filePath) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, lastDotIndex);
    }

}
