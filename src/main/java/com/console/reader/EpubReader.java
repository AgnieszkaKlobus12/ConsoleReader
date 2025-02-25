package com.console.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class EpubReader {

    public void unzipFile(File file) {
        try {
            cleanDirectory();
            File destDir = new File(System.getProperty("user.dir") + "/temporaryUnzipped");
            byte[] buffer = new byte[1024];

            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                File newFile = new File(destDir.getName(), zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.close();
                }
                zipEntry = zipInputStream.getNextEntry();
            }

            zipInputStream.closeEntry();
            zipInputStream.close();

        } catch (IOException e) {
            System.out.println("Unable to open book " + file.getName());
        }
    }

    private void cleanDirectory() throws IOException {
        Path directoryPath = Paths.get(System.getProperty("user.dir") + "/temporaryUnzipped");

        Files.walkFileTree(directoryPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
