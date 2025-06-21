package org.example.presentation.newcontactscreen;

import org.example.presentation.emailscreen.EmailController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class NewContactView extends JFrame {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$");
    private final JPanel mainPanel;
    private final JPanel nameContainer;
    private final JPanel surnameContainer;
    private final JPanel emailContainer;
    private final JPanel buttonsContainer;
    private final EmailController.ContactSaver contactSaver = new EmailController.ContactSaver("contacts.txt");
    private  JTextField nameInputField;
    private  JTextField surnameInputField;
    private  JTextField emailInputField;


    public NewContactView() {
        mainPanel = new JPanel(new GridBagLayout());
        nameContainer = new JPanel(new GridBagLayout());
        surnameContainer = new JPanel(new GridBagLayout());
        emailContainer = new JPanel(new GridBagLayout());
        buttonsContainer = new JPanel();
        setContent();
    }

    private void setContent() {
        setMainPanel();
        this.add(mainPanel);
        this.setSize(400, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("CREATE NEW CONTACT");
    }

    private void setMainPanel() {
        setNameContainer();
        setSurnameContainer();
        setEmailContainer();
        setButtonsContainer();
    }

    private void setNameContainer() {
        createNameLabel();
        createNameField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(nameContainer, constraints);
    }

    private void setSurnameContainer() {
        createSurnameLabel();
        createSurnameField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(surnameContainer, constraints);
    }

    private void setEmailContainer() {
        createEmailField();
        createEmailLabel();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(emailContainer, constraints);
    }

    private void setButtonsContainer() {
        createButton();
        cancelButton();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonsContainer, constraints);
    }

    private void createNameLabel() {
        JLabel label = new JLabel("Name:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 6;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        nameContainer.add(label, constraints);
    }

    private void createNameField() {
        nameInputField = new JTextField();
        nameInputField.setEditable(true);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.weightx = 4;
        constraints.fill = GridBagConstraints.BOTH;
        nameContainer.add(nameInputField, constraints);
    }

    private void createSurnameLabel() {
        JLabel label = new JLabel("Surname:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 6;

        constraints.fill = GridBagConstraints.BOTH;
        surnameContainer.add(label, constraints);
    }

    private void createSurnameField() {
        surnameInputField = new JTextField();
        surnameInputField.setEditable(true);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.weightx = 4.28;
        constraints.fill = GridBagConstraints.BOTH;
        surnameContainer.add(surnameInputField, constraints);
    }

    private void createEmailLabel() {
        JLabel label = new JLabel("Email:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 6;

        constraints.fill = GridBagConstraints.BOTH;
        emailContainer.add(label, constraints);
    }

    private void createEmailField() {
        emailInputField = new JTextField();
        emailInputField.setEditable(true);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.weightx = 4;
        constraints.fill = GridBagConstraints.BOTH;
        emailContainer.add(emailInputField, constraints);
    }

    private void createButton() {
        JButton button = new JButton("Add");

        button.addActionListener(e -> {
            String name = nameInputField.getText().trim();
            String surname = surnameInputField.getText().trim();
            String email = emailInputField.getText().trim();
            if(name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
                showError("All fields are required!");
                return;
            }

            if (!isValidEmail(email)) {
                emailInputField.setBorder(BorderFactory.createLineBorder(Color.RED));
                showError("Invalid email address!");
                return;
            } else {
                emailInputField.setBorder(BorderFactory.createLineBorder(Color.white));
            }

            try {
                EmailController.Contact contact = new EmailController.Contact(name, surname, email);
                contactSaver.save(contact);
                JOptionPane.showMessageDialog(this, "Contact saved!");
                clearFields();
                dispose();

            } catch (IOException ex) {
                showError("Error reading or saving contacts: " + ex.getMessage());
            }
            dispose();
        });

        buttonsContainer.add(button);
    }

    private JButton cancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> dispose());
        buttonsContainer.add(button);
        return button;
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private void clearFields() {
        nameInputField.setText("");
        surnameInputField.setText("");
        emailInputField.setText("");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


}
