package org.example.presentation.newcontactscreen;

import org.example.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewContactView extends JFrame {
    private final JPanel mainPanel;
    private final JPanel nameContainer;
    private final JPanel surnameContainer;
    private final JPanel emailContainer;
    private final JPanel buttonsContainer;
    private JTextField nameInputField;
    private JTextField surnameInputField;
    private JTextField emailInputField;
    private JButton createButton;


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
        setCreateButton();
        setCancelButton();

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

    private void setCreateButton() {
        createButton = new JButton("Add");
        buttonsContainer.add(createButton);
    }

    private JButton setCancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> dispose());
        buttonsContainer.add(button);
        return button;
    }

    public void clearFields() {
        nameInputField.setText("");
        surnameInputField.setText("");
        emailInputField.setText("");
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void bindOnClickCreateButton(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public String getNameInputValue() {
        return nameInputField.getText().trim();
    }

    public String getSurnameInputValue() {
        return surnameInputField.getText().trim();
    }

    public String getEmailInputValue() {
        return emailInputField.getText().trim();
    }

    public void setInvalidEmailInputStyle() {
        emailInputField.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public void setValidEmailInputStyle() {
        emailInputField.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    public void showContactSavedDialog() {
        JOptionPane.showMessageDialog(this, "Contact saved!");
    }
}