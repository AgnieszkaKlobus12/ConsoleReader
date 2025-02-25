package com.console;

import com.console.reader.EpubReader;
import com.console.reader.HTMLContentExtractor;
import com.console.utils.ChapterNamesComparator;
import com.console.utils.LastReadService;

public class Main {

    public static void main(String[] args) {
        new Gui(new BookListService(), new ChapterNamesComparator(), new EpubReader(), new LastReadService(), new HTMLContentExtractor())
                .start();
    }
}
