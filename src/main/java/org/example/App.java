package org.example;

import org.example.data.FileRepository;
import org.example.model.Contact;
import org.example.model.IRepository;
import org.example.model.RepositoryException;
import org.example.presentation.contactsscreen.ContactsListController;
import org.example.presentation.contactsscreen.ContactsListView;
import org.example.presentation.emailscreen.EmailController;
import org.example.presentation.emailscreen.EmailView;
import org.example.presentation.newcontactscreen.NewContactController;
import org.example.presentation.newcontactscreen.NewContactView;
import org.example.presentation.newemailscreen.NewMessageController;
import org.example.presentation.newemailscreen.NewMessageView;

import java.util.function.Consumer;

public class App {
    private static App INSTANCE;
    private final IRepository repository;

    private App() {
        this.repository = new FileRepository();
    }

    public static App getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new App();
        }
        return INSTANCE;
    }

    public void openEmailScreen() throws RepositoryException {
        EmailView emailView = new EmailView();
        EmailController.getInstance(repository, emailView, INSTANCE);
    }

    public void openNewContactScreen() {
        NewContactView contactView = new NewContactView();
        NewContactController.getInstance(INSTANCE).setView(contactView);
    }

    public void openNewMessageScreen() {
        NewMessageView messageView = new NewMessageView();
        NewMessageController.getInstance(INSTANCE).setView(messageView);
    }

    public void openContactsListScreen() throws RepositoryException {
        ContactsListView contactsListView = new ContactsListView();
        ContactsListController.getInstance(repository, this::setSelectedContact).setView(contactsListView);
    }

    private void setSelectedContact(Contact contact) {
        NewMessageController.getInstance().setContact(contact);
    }
}
