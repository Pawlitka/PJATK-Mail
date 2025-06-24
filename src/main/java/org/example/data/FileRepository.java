package org.example.data;

import org.example.model.Contact;
import org.example.model.IRepository;
import org.example.model.Message;
import org.example.model.RepositoryException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository {
    private final static String CONTACTS_FILE_PATH = "contacts.csv";
    private final static String MESSAGE_FILE_PATH = "messages.csv";

    public FileRepository() {
    }

    public List<Contact> loadContacts() throws RepositoryException {
        try {
            List<Contact> contacts = new ArrayList<>();
            Path path = Paths.get(CONTACTS_FILE_PATH);

            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String name = parts[0].trim();
                        String surname = parts[1].trim();
                        String email = parts[2].trim();
                        contacts.add(new Contact(name, surname, email));
                    }
                }
            }

            return contacts;
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void saveContacts(Contact contact) throws RepositoryException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTACTS_FILE_PATH, true))) {
            writer.newLine();
            writer.write(contact.toCsv());
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void saveMessages(Message message) throws RepositoryException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MESSAGE_FILE_PATH, true))) {
            writer.newLine();
            writer.write(message.toCsv());
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Message> loadMessages() throws RepositoryException {
        try {
            List<Message> messages = new ArrayList<>();
            Path path = Paths.get(MESSAGE_FILE_PATH);

            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String receiverMail = parts[0].trim();
                        String topic = parts[1].trim();
                        String content = parts[2].trim();
                        messages.add(new Message(receiverMail, topic, content));
                    }
                }
            }

            return messages;
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
