package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFileLoader {

    private final File file;

    public ParseFileLoader(File file) {
        this.file = file;
    }

    private String content(Predicate<Character> filter) {
        String output = "";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                output += filter.test((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContent() {
        return content(x -> true);
    }

    public String getContentWithoutUnicode() {
        return content(x -> x < 0x80);
    }
}
