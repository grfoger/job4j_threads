package ru.job4j.concurrent;

import java.lang.Thread;

public class ConsoleProgress implements Runnable {
    private static final String LOAD = "\rLoading ... ";

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }

    @Override
    public void run() {
        int count = 0;
            while (!Thread.currentThread().isInterrupted()) {

                switch (count % 4) {
                    case 0:
                        System.out.print(LOAD + "\\");
                        break;
                    case 1:
                        System.out.print(LOAD + "|");
                        break;
                    case 2:
                        System.out.print(LOAD + "/");
                        break;
                    default:
                        System.out.print(LOAD + "--");
                        break;
                }
                count++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

    }
}
