package org.example.presentation.contactsscreen;

import org.example.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ContactsListController implements EventListener {
    private static ContactsListController INSTANCE;
    private  List<Contact> contacts;
    private final IRepository repository;
    private final NotificationService notificationService;
    private ContactsListView view;
    private Consumer<Contact> onEmailSelected;

    private ContactsListController(IRepository repository, NotificationService notificationService, Consumer<Contact> onEmailSelected) throws RepositoryException {
        this.repository = repository;
        this.notificationService = notificationService;
        this.onEmailSelected = onEmailSelected;
    }

    public static ContactsListController getInstance() {
        return INSTANCE;
    }

    public static ContactsListController getInstance(IRepository repository, NotificationService notificationService, Consumer<Contact> onEmailSelected) throws RepositoryException {
        if (INSTANCE == null) {
            INSTANCE = new ContactsListController(repository, notificationService, onEmailSelected);
        }
        return INSTANCE;
    }

    public void setView(ContactsListView view) throws RepositoryException {
        this.view = view;
        bindListeners();
        initData();
    }

    private void bindListeners() {
        view.bindOnClickSetButton(new OnClickSetButtonListener());
        view.bindOnClickCancelButton(new OnClickCancelButtonListener());
    }

    public void initData() throws RepositoryException {
        contacts = repository.loadContacts();
        view.setContactsList(contacts);
    }

    @Override
    public void update(EventType eventType) {
        if(eventType.equals(EventType.NEW_CONTACT)) {
            try {
                initData();
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class OnClickSetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Contact selectedContact = view.getSelectedContact();
            if (selectedContact != null) {
                onEmailSelected.accept(selectedContact);
                view.showDialog("Chosen email: " + selectedContact);
                view.dispose();
                notificationService.unsubscribe(EventType.NEW_CONTACT, ContactsListController.getInstance());

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
