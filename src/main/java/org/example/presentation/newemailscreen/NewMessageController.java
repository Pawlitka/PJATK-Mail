package org.example.presentation.newemailscreen;

import org.example.App;
import org.example.data.FileRepository;
import org.example.model.Contact;
import org.example.model.IRepository;
import org.example.model.Message;
import org.example.model.RepositoryException;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.swing.text.html.HTML;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMessageController {
    private static NewMessageController INSTANCE;
    private NewMessageView view;
    private App app;
    private final String USERNAME = "";
    private final String PASSWORD = "";
    private final String SMTP_HOST = "smtp.gmail.com";
    private final Integer PORT = 587;
    private final IRepository repository;

    private NewMessageController(App app) {
        this.app = app;
        this.repository = new FileRepository();
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
                Message message = new Message(view.getMessageReceiver(), view.getTopic(), view.getTextArea());
                repository.saveMessages(message);
            } catch (RepositoryException ex) {
                view.showError("Error" + ex.getMessage());
            }

            Email email = EmailBuilder.startingBlank()
                    .from("pawel.kapusta2004@gmail.com")
                    .to(view.getMessageReceiver())
                    .withSubject(view.getTopic())
                    .withHTMLText(view.getTextArea())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer(SMTP_HOST, PORT, USERNAME, PASSWORD)
                    .async()
                    .buildMailer().sendMail(email);

            view.dispose();
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
