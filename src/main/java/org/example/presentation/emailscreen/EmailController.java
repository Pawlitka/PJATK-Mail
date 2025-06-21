package org.example.presentation.emailscreen;

import org.example.App;
import org.example.model.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EmailController {
    private static EmailController INSTANCE;
    private final App app;
    public final ArrayList<Message> sentMessages = new ArrayList<>(List.of(
            new Message("marcin.test@gmail.com", "testowy", "Test, test lalali lalala"),
            new Message("ancymon@gmail.com", "kotki", "Lubie kotki, pozdrawiam"),
            new Message("pawel@gmail.com", "rre", "Lubie kotki 2137, pozdrawiam Lubie kotki 2137 ")
    ));
    private EmailView view;

    private EmailController(EmailView view, App app) {
        this.view = view;
        this.app = app;
        initData();
        bindListeners();
    }

    public static EmailController getInstance(EmailView view, App app) {
        if (INSTANCE == null) {
            INSTANCE = new EmailController(view, app);
        }
        return INSTANCE;
    }

    private void bindListeners() {
        view.getBottomPanel().bindOnClickSentMessage(new OnClickSentMessageListener());
        view.getTopPanel().bindOnClickContactButton(new OnClickContactButtonListener());
        view.getTopPanel().bindOnClickEmailButton(new OnClickEmailButtonListener());
    }

    private void initData() {

        view.getBottomPanel().setListOfSentEmails(sentMessages);
    }

    private class OnClickSentMessageListener implements Consumer<Message> {
        @Override
        public void accept(Message message) {
            view.getBottomPanel().setMessageContent(message.getContent());
            view.getBottomPanel().setReceiverInputFieldValue(message.getReceiverEmail());
            view.getBottomPanel().setTopicInputFieldValue(message.getTitle());
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
