package org.example.model;

public class Contact {
    private final String name;
    private final String surname;
    private final String email;

    public Contact(String name, String surname, String email) {
        this.name = name.trim();
        this.surname = surname.trim();
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", name, surname, email);
    }

    public String getEmail() {
        return email;
    }
}
