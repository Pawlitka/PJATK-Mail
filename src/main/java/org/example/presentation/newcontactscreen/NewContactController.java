package org.example.presentation.newcontactscreen;

import org.example.App;
import org.example.data.FileRepository;
import org.example.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewContactController {
    private static NewContactController INSTANCE;
    private final App app;
    private final IRepository repository;
    private final NotificationService notificationService;
    private NewContactView view;

    private NewContactController(App app, NotificationService notificationService) {
        this.app = app;
        this.notificationService = notificationService;
        this.repository = new FileRepository();
    }

    public static NewContactController getInstance(App app, NotificationService notificationService) {
        if (INSTANCE == null) {
            INSTANCE = new NewContactController(app, notificationService);
        }
        return INSTANCE;
    }

    public void setView(NewContactView view) {
        this.view = view;
        bindListeners();
    }

    private void bindListeners() {
        view.bindOnClickCreateButton(new OnClickCreateButtonListener());
        view.bindOnClickCancelButton(new OnClickCancelButtonListener());
    }

    private class OnClickCreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInputValue();
            String surname = view.getSurnameInputValue();
            String email = view.getEmailInputValue();
            if (name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
                view.showError("All fields are required!");
                return;
            }

            if (!EmailValidator.validate(email)) {
                view.setInvalidEmailInputStyle();
                view.showError("Invalid email address!");
                return;
            } else {
                view.setValidEmailInputStyle();
            }

            try {
                Contact contact = new Contact(name, surname, email);
                repository.saveContacts(contact);
                view.showContactSavedDialog();
                notificationService.notify(EventType.NEW_CONTACT);
                view.clearFields();
                view.dispose();

            } catch (RepositoryException ex) {
                view.showError("Error reading or saving contacts: " + ex.getMessage());
            }
        }
    }


    private class OnClickCancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.close();
        }
    }
}
