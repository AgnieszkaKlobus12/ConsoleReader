package com.console;

import com.console.utils.ChapterNamesComparator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gui {

    private final BookListService bookListService;
    private final ChapterNamesComparator chapterNamesComparator;

    public Gui(BookListService bookListService, ChapterNamesComparator chapterNamesComparator) {
        this.bookListService = bookListService;
        this.chapterNamesComparator = chapterNamesComparator;
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
