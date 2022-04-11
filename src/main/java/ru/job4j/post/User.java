package ru.job4j.post;

public class User {
    private final String username;
    private final String email;

    public User(String name, String email) {
        this.username = name;
        this.email = email;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
