package org.example.presentation.contactsscreen;

import org.example.model.Message;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ContactsFrame extends JFrame {
    private final JPanel mainPanel;
    private final JPanel buttonsContainer;
    private final JList<String> emailList;
    private final Consumer<String> onEmailSelected;

    public ContactsFrame(Consumer<String> onEmailSelected) {
        mainPanel = new JPanel(new GridBagLayout());
        buttonsContainer = new JPanel();
        emailList = new JList<>();
        this.onEmailSelected = onEmailSelected;

        setContent();
    }

    private void setContent() {
        setMainPanel();
        this.add(mainPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Choose contact");
    }

    public void setMainPanel() {
        setLabel();
        setContactList();
        setButtons();
    }

    private void setLabel() {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel textLabel = new JLabel("Contacts: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(textLabel, constraints);
    }

    private void setContactList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String email : readEmailsFromFile("contacts.txt")) {
            listModel.addElement(email);
        }

        emailList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(emailList);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 6;
        constraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(scrollPane, constraints);
    }

    private ArrayList<String> readEmailsFromFile(String filePath) {
        ArrayList<String> emails = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String email = extractEmail(line);
                if (email != null) {
                    emails.add(email);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }

        return emails;
    }

    private String extractEmail(String line) {
        String[] parts = line.split("Email:");
        if (parts.length == 2) {
            return parts[1].trim();
        }
        return null;
    }

    private void setButtons() {
        JButton choose = new JButton("Choose");
        choose.addActionListener(e -> {
            String selectedEmail = emailList.getSelectedValue();
            if (selectedEmail != null) {
                onEmailSelected.accept(selectedEmail);
                JOptionPane.showMessageDialog(this, "Chosen email: " + selectedEmail);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "You haven't chose email.");
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> dispose());

        buttonsContainer.add(choose);
        buttonsContainer.add(cancel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.weightx = 10;
        constraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(buttonsContainer, constraints);
    }
}
