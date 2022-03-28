package ru.job4j.concurrent;

import java.lang.Thread;

public class ConsoleProgress implements Runnable {
    private static final String LOAD = "\rLoading ... ";
    private static final String[] LOADPARTS = {"\\", "|", "/", "--"};

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }

    @Override
    public void run() {
        int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print(LOAD + LOADPARTS[count % 4]);
                count++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }

    }
}
