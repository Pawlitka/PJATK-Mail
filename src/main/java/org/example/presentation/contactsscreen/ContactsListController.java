package org.example.presentation.contactsscreen;

import org.example.model.Contact;
import org.example.model.IRepository;
import org.example.model.RepositoryException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ContactsListController {
    private static ContactsListController INSTANCE;
    private final List<Contact> contacts;
    private final IRepository repository;
    private ContactsListView view;
    private Consumer<Contact> onEmailSelected;

    private ContactsListController(IRepository repository, Consumer<Contact> onEmailSelected) throws RepositoryException {
        this.repository = repository;
        this.onEmailSelected = onEmailSelected;
        contacts = repository.load();
    }

    public static ContactsListController getInstance() {
        return INSTANCE;
    }

    public List<Contact> getContacts() {
     return contacts;
    }

    public static ContactsListController getInstance(IRepository repository, Consumer<Contact> onEmailSelected) throws RepositoryException {
        if (INSTANCE == null) {
            INSTANCE = new ContactsListController(repository, onEmailSelected);
        }
        return INSTANCE;
    }

    public void setView(ContactsListView view) {
        this.view = view;
        bindListeners();
        view.setContactsList();
    }

    private void bindListeners() {
        view.bindOnClickSetButton(new OnClickSetButtonListener());
        view.bindOnClickCancelButton(new OnClickCancelButtonListener());
    }

    private class OnClickSetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Contact selectedContact = view.getSelectedContact();
            if (selectedContact != null) {
                onEmailSelected.accept(selectedContact);
                view.showDialog("Chosen email: " + selectedContact);
                view.dispose();
            } else {
                view.showDialog("You haven't chose email.");
            }
        }
    }

    private class OnClickCancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }
}
