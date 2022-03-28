package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        System.out.println(first.getState() + " Нить:" + first.getName());
        System.out.println(second.getState() + " Нить:" + second.getName());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState() + " Нить:" + first.getName());
            System.out.println(second.getState() + " Нить:" + second.getName());
        }
        System.out.println("Работа нитей завершена:");
        System.out.println(first.getState() + " Нить:" + first.getName());
        System.out.println(second.getState() + " Нить:" + second.getName());
    }
}