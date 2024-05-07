package com.console;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookListService {

    public static final String EPUB_EXTENSION = "epub";

    public Set<String> listBookFilesInDirectory(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .filter(name -> FilenameUtils.getExtension(name).equals(EPUB_EXTENSION))
                .collect(Collectors.toSet());
    }

    public File getFileWithName(String bookTitle) {
        return new File(System.getProperty("user.dir") + "/" + bookTitle);
    }

}
