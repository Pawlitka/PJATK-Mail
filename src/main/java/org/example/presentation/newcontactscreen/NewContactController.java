package org.example.presentation.newcontactscreen;

import org.example.data.FileRepository;
import org.example.model.Contact;
import org.example.model.IRepository;
import org.example.model.RepositoryException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class NewContactController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$");
    private static NewContactController INSTANCE;
    private final IRepository repository;
    private NewContactView view;

    private NewContactController(NewContactView view) {
        this.view = view;
        this.repository = new FileRepository();
        bindListeners();
    }

    public static NewContactController getInstance(NewContactView view) {
        if (INSTANCE == null) {
            INSTANCE = new NewContactController(view);
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

            if (!isValidEmail(email)) {
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

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
