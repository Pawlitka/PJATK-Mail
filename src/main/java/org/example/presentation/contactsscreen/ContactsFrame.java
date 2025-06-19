package org.example.presentation.contactsscreen;

import org.example.model.Message;

import javax.swing.*;
import java.awt.*;

public class ContactsFrame extends JFrame {
    private final JPanel mainPanel;
//    private final JList<String> list;
    private final JPanel buttonsContainer;

    public ContactsFrame() {
        mainPanel = new JPanel(new GridBagLayout());
        buttonsContainer = new JPanel();
        setContent();
    }

    private void setContent() {
        setMainPanel();
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        GridBagConstraints constraints = new GridBagConstraints();
        JTextField field = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 6;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(field, constraints);
    }

    private void setButtons() {
        chooseButton();
        cancelButton();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.weightx = 10;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonsContainer, constraints);
    }

    private JButton chooseButton() {
        JButton button = new JButton("Choose");
        buttonsContainer.add(button);
        return button;
    }

    private JButton cancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> dispose());
        buttonsContainer.add(button);
        return button;
    }
}
