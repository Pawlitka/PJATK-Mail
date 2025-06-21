package org.example.presentation.contactsscreen;

import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ContactsListView extends JFrame {
    private final JPanel mainPanel;
    private final JPanel buttonsContainer;
    private final JList<Contact> emailList;
    private JButton chooseButton;
    private JButton cancelButton;

    public ContactsListView() {
        mainPanel = new JPanel(new GridBagLayout());
        buttonsContainer = new JPanel();
        emailList = new JList<>();


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
        JScrollPane scrollPane = new JScrollPane(emailList);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 6;
        constraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(scrollPane, constraints);
    }

    private void setButtons() {
        chooseButton = new JButton("Choose");
        cancelButton = new JButton("Cancel");

        buttonsContainer.add(chooseButton);
        buttonsContainer.add(cancelButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.weightx = 10;
        constraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(buttonsContainer, constraints);
    }

    public void bindOnClickSetButton(ActionListener listener) {
        chooseButton.addActionListener(listener);
    }

    public void bindOnClickCancelButton(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public Contact getSelectedContact() {
        return emailList.getSelectedValue();
    }

    public void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setContactsList() {
        DefaultListModel<Contact> listModel = new DefaultListModel<>();
        listModel.addAll(ContactsListController.getInstance().getContacts());

        emailList.setModel(listModel);
    }
}
