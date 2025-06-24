package org.example.presentation.emailscreen;

import org.example.App;
import org.example.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EmailController implements EventListener {
    private static EmailController INSTANCE;
    private final App app;
    public List<Message> messages;
    private EmailView view;
    private final IRepository repository;
    private final NotificationService notificationService;


    private EmailController(IRepository repository, NotificationService notificationService, EmailView view, App app) throws RepositoryException {
        this.repository = repository;
        this.notificationService = notificationService;
        this.view = view;
        this.app = app;
        initData();
        bindListeners();
    }

    public static EmailController getInstance() {
        return INSTANCE;
    }

    public static EmailController getInstance(IRepository repository, NotificationService notificationService, EmailView view, App app) throws RepositoryException {
        if (INSTANCE == null) {
            INSTANCE = new EmailController(repository, notificationService, view, app);
        }
        return INSTANCE;
    }

    private void bindListeners() {
        view.getBottomPanel().bindOnClickSentMessage(new OnClickSentMessageListener());
        view.getTopPanel().bindOnClickContactButton(new OnClickContactButtonListener());
        view.getTopPanel().bindOnClickEmailButton(new OnClickEmailButtonListener());
    }

    private void initData() throws RepositoryException {
        messages = repository.loadMessages();
        view.getBottomPanel().setListOfSentEmails(messages);
    }

    @Override
    public void update(EventType eventType) {
        if(eventType.equals(EventType.NEW_MESSAGE)) {
            try {
                initData();
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class OnClickSentMessageListener implements Consumer<Message> {
        @Override
        public void accept(Message message) {
            view.getBottomPanel().setMessageContent(message.getContent());
            view.getBottomPanel().setReceiverInputFieldValue(message.getReceiverEmail());
            view.getBottomPanel().setTopicInputFieldValue(message.getTopic());
        }
    }

    private class OnClickContactButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            app.openNewContactScreen();
        }
    }

    private class OnClickEmailButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            app.openNewMessageScreen();
        }
    }
}
