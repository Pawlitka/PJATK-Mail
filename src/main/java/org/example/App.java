package org.example;

import org.example.data.FileRepository;
import org.example.model.*;
import org.example.presentation.contactsscreen.ContactsListController;
import org.example.presentation.contactsscreen.ContactsListView;
import org.example.presentation.emailscreen.EmailController;
import org.example.presentation.emailscreen.EmailView;
import org.example.presentation.newcontactscreen.NewContactController;
import org.example.presentation.newcontactscreen.NewContactView;
import org.example.presentation.newemailscreen.NewMessageController;
import org.example.presentation.newemailscreen.NewMessageView;

public class App {
    private static App INSTANCE;
    private final IRepository repository;
    private final NotificationService notificationService;

    private App() {
        this.repository = new FileRepository();
        this.notificationService = new NotificationService();
    }

    public static App getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new App();
        }
        return INSTANCE;
    }

    public void openEmailScreen() throws RepositoryException {
        EmailView emailView = new EmailView();
        EmailController.getInstance(repository, notificationService, emailView, INSTANCE);
        notificationService.subscribe(EventType.NEW_MESSAGE, EmailController.getInstance());
    }

    public void openNewContactScreen() {
        NewContactView contactView = new NewContactView();
        NewContactController.getInstance(INSTANCE, notificationService).setView(contactView);
    }

    public void openNewMessageScreen() {
        NewMessageView messageView = new NewMessageView();
        NewMessageController.getInstance(INSTANCE, notificationService).setView(messageView);
    }

    public void openContactsListScreen() throws RepositoryException {
        ContactsListView contactsListView = new ContactsListView();
        ContactsListController.getInstance(repository, notificationService, this::setSelectedContact).setView(contactsListView);
        notificationService.subscribe(EventType.NEW_CONTACT, ContactsListController.getInstance());
    }

    private void setSelectedContact(Contact contact) {
        NewMessageController.getInstance().setContact(contact);
    }
}
