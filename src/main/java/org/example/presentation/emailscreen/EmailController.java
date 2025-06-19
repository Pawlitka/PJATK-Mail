package org.example.presentation.emailscreen;

import org.example.model.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EmailController {
    private static EmailController INSTANCE;
    public final ArrayList<Message> sentMessages = new ArrayList<>(List.of(
            new Message("marcin.test@gmail.com", "testowy", "Test, test lalali lalala"),
            new Message("ancymon@gmail.com", "kotki", "Lubie kotki, pozdrawiam"),
            new Message("pawel@gmail.com", "rre", "Lubie kotki 2137, pozdrawiam ")
    ));
    private Message selectedMessage;
    private EmailView view;

    private EmailController(EmailView view) {
        this.view = view;
        initData();
        bindListeners();
    }

    public static EmailController getInstance(EmailView view) {
        if (INSTANCE == null) {
            INSTANCE = new EmailController(view);
        }
        return INSTANCE;
    }

    private void bindListeners() {
        view.getBottomPanel().bindOnClickSentMessage(new OnClickSentMessageListener());
    }

    private class OnClickSentMessageListener implements Consumer<Message> {
        @Override
        public void accept(Message message) {
            view.getBottomPanel().setMessageContent(message.getContent());
            view.getBottomPanel().setReceiverInputFieldValue(message.getReceiverEmail());
            view.getBottomPanel().setTopicInputFieldValue(message.getTitle());
        }
    }


    public void onCreateEmail() {

    }

    public void onCreateContact() {

    }

    private void initData() {

        view.getBottomPanel().setListOfSentEmails(sentMessages);
    }
}
