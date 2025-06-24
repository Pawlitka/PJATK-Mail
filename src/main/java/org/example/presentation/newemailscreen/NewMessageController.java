package org.example.presentation.newemailscreen;

import org.example.App;
import org.example.data.FileRepository;
import org.example.model.*;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMessageController {
    private static NewMessageController INSTANCE;
    private final String USERNAME = "";
    private final String PASSWORD = "";
    private final String SMTP_HOST = "smtp.gmail.com";
    private final Integer PORT = 587;
    private final IRepository repository;
    private final NotificationService notificationService;
    private NewMessageView view;
    private App app;

    private NewMessageController(App app, NotificationService notificationService) {
        this.app = app;
        this.notificationService = notificationService;
        this.repository = new FileRepository();
    }

    public static NewMessageController getInstance() {
        return INSTANCE;
    }

    public static NewMessageController getInstance(App app, NotificationService notificationService) {
        if (INSTANCE == null) {
            INSTANCE = new NewMessageController(app, notificationService);
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
                notificationService.notify(EventType.NEW_MESSAGE);
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
