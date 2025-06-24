package org.example.presentation.emailscreen;

import org.example.App;
import org.example.model.IRepository;
import org.example.model.Message;
import org.example.model.RepositoryException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EmailController {
    private static EmailController INSTANCE;
    private final App app;
    public final List<Message> messages;
    private EmailView view;
    private final IRepository repository;


    private EmailController(IRepository repository,EmailView view, App app) throws RepositoryException {
        this.repository = repository;
        this.view = view;
        this.app = app;
        messages = repository.loadMessages();
        initData();
        bindListeners();
    }

    public static EmailController getInstance(IRepository repository, EmailView view, App app) throws RepositoryException {
        if (INSTANCE == null) {
            INSTANCE = new EmailController(repository, view, app);
        }
        return INSTANCE;
    }

    private void bindListeners() {
        view.getBottomPanel().bindOnClickSentMessage(new OnClickSentMessageListener());
        view.getTopPanel().bindOnClickContactButton(new OnClickContactButtonListener());
        view.getTopPanel().bindOnClickEmailButton(new OnClickEmailButtonListener());
    }

    private void initData() throws RepositoryException {

        view.getBottomPanel().setListOfSentEmails(messages);
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
