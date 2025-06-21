package org.example.data;

import org.example.model.Contact;
import org.example.model.RepositoryException;
import org.example.model.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository {
    private final static String FILE_PATH = "contacts.txt";

    public FileRepository() {}

    public List<Contact> load() throws RepositoryException {
        try {
            List<Contact> contacts = new ArrayList<>();
            Path path = Paths.get(FILE_PATH);

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

    public void save(Contact contact) throws RepositoryException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))){
            writer.newLine();
            writer.write(contact.toCsv());
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
