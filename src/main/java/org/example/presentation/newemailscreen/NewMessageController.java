package org.example.presentation.newemailscreen;

import org.example.App;
import org.example.model.Contact;
import org.example.model.RepositoryException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMessageController {
    private static NewMessageController INSTANCE;
    private NewMessageView view;
    private Contact contact;
    private App app;

    private NewMessageController(App app) {
        this.app = app;
    }

    public static NewMessageController getInstance() {
        return INSTANCE;
    }

    public static NewMessageController getInstance(App app) {
        if (INSTANCE == null) {
            INSTANCE = new NewMessageController(app);
        }
        return INSTANCE;
    }

    public void setView(NewMessageView view) {
        this.view = view;
        bindListeners();
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        view.setReceiverEmail(contact.getEmail());
    }


    private void bindListeners() {
        view.bindOnClickCreateButton(new OnClickCreateButtonListener());
        view.bindOnClickChooseContactButton(new OnClickChooseContactButtonListener());
        view.bindOnClickCancelButton(new OnClickCancelButtonListener());
    }

    private class OnClickCreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                app.openContactsListScreen();
            } catch (RepositoryException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class OnClickChooseContactButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                app.openContactsListScreen();
            } catch (RepositoryException ex) {
                throw new RuntimeException(ex);
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
