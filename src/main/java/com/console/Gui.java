package com.console;

import java.util.List;

public class Gui {

    private final BookListService bookListService;

    public Gui(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    public void listOptions() {
        List<String> titles = bookListService.listBookFilesInDirectory(System.getProperty("user.dir"))
                .stream()
                .toList();
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ". " + titles.get(i));
        }
    }
}
