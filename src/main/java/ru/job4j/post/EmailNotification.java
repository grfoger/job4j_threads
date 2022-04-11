package ru.job4j.post;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = "Notification %s to email %s.";
        String body = "Add a new event to %s";
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(String.format(subject, user.getName(), user.getEmail()),
                        String.format(body, user.getName()),
                        user.getEmail());
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
