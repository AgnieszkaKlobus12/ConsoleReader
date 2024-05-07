package com.console;

import java.util.List;
import java.util.Scanner;

public class Gui {

    private final BookListService bookListService;

    public Gui(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    public int bookSelection() {
        List<String> titles = bookListService.listBookFilesInDirectory(System.getProperty("user.dir"))
                .stream()
                .toList();
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ". " + titles.get(i));
        }
        System.out.println("Select book to open - write number");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
