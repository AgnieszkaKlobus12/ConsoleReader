package com.console;

import java.util.List;
import java.util.Scanner;

public class Gui {

    private final BookListService bookListService;

    public Gui(BookListService bookListService) {
        this.bookListService = bookListService;
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
        List<String> chapters = bookListService.listChaptersInDirectory().stream().toList();
        for (int i = 0; i < chapters.size(); i++) {
            System.out.println(i + ". " + removeExtension(chapters.get(i)));
        }
        System.out.print("Select chapter to open. \nNumber: ");
        Scanner scanner = new Scanner(System.in);
        return chapters.get(scanner.nextInt());
    }

    private static String removeExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, lastDotIndex);
    }
}
