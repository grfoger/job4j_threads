package ru.job4j.io;

import java.io.*;

public final class ParseFileSaver {

    private final File file;

    public ParseFileSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
