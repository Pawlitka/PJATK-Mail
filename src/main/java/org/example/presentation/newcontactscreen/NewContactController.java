package org.example.presentation.newcontactscreen;

import org.example.App;
import org.example.data.FileRepository;
import org.example.model.Contact;
import org.example.model.EmailValidator;
import org.example.model.IRepository;
import org.example.model.RepositoryException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewContactController {
    private static NewContactController INSTANCE;
    private final App app;
    private final IRepository repository;
    private NewContactView view;

    private NewContactController(NewContactView view, App app) {
        this.view = view;
        this.app = app;
        this.repository = new FileRepository();
        bindListeners();
    }

    public static NewContactController getInstance(NewContactView view, App app) {
        if (INSTANCE == null) {
            INSTANCE = new NewContactController(view, app);
        }
        return INSTANCE;
    }

    private void bindListeners() {
        view.bindOnClickCreateButton(new OnClickCreateButtonListener());
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
                repository.save(contact);
                view.showContactSavedDialog();
                view.clearFields();
            } catch (RepositoryException ex) {
                view.showError("Error reading or saving contacts: " + ex.getMessage());
            }
        }
    }
}
