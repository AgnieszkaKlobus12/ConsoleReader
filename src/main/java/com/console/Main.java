package com.console;

public class Main {

    public static void main(String[] args) {
        Gui gui = new Gui(new BookListService());
        gui.listOptions();
    }
}
