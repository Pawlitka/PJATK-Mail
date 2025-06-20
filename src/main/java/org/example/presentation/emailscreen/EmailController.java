package org.example.presentation.emailscreen;

import org.example.model.Message;

import javax.imageio.IIOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EmailController {
    private static EmailController INSTANCE;
    public final ArrayList<Message> sentMessages = new ArrayList<>(List.of(
            new Message("marcin.test@gmail.com", "testowy", "Test, test lalali lalala"),
            new Message("ancymon@gmail.com", "kotki", "Lubie kotki, pozdrawiam"),
            new Message("pawel@gmail.com", "rre", "Lubie kotki 2137, pozdrawiam Lubie kotki 2137 ")
    ));
    private Message selectedMessage;
    private EmailView view;

    private EmailController(EmailView view) {
        this.view = view;
        initData();
        bindListeners();
    }

    public static EmailController getInstance(EmailView view) {
        if (INSTANCE == null) {
            INSTANCE = new EmailController(view);
        }
        return INSTANCE;
    }

    private void bindListeners() {
        view.getBottomPanel().bindOnClickSentMessage(new OnClickSentMessageListener());
    }

    private class OnClickSentMessageListener implements Consumer<Message> {
        @Override
        public void accept(Message message) {
            view.getBottomPanel().setMessageContent(message.getContent());
            view.getBottomPanel().setReceiverInputFieldValue(message.getReceiverEmail());
            view.getBottomPanel().setTopicInputFieldValue(message.getTitle());
        }
    }


    public void onCreateEmail() {

    }

    public static class Contact {
        private final String name;
        private final String surname;
        private final String email;

        public Contact(String name, String surname, String email) {
            this.name = name.trim();
            this.surname = surname.trim();
            this.email = email.trim();
        }

        public String toString() {
            return String.format("Name: %s, Surname: %s, Email: %s", name, surname, email);
        }

        public String getEmail() {
            return email;
        }

    }

    public static class ContactSaver {
        private final String filePath;

        public ContactSaver(String filePath) {
            this.filePath = filePath;
        }

        public List<EmailController.Contact> loadAll() throws IOException {
            List<EmailController.Contact> contacts = new ArrayList<>();
            Path path = Paths.get(filePath);

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
        }

        public void save(Contact contact) throws IIOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
                writer.write(contact.toString());
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onCreateContact() {

    }

    private void initData() {

        view.getBottomPanel().setListOfSentEmails(sentMessages);
    }
}
