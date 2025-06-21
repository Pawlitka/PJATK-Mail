package org.example.presentation.newemailscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMessageController {
    private static NewMessageController INSTANCE;
    private NewMessageView view;

    private NewMessageController() {
    }

    public static NewMessageController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewMessageController();
        }
        return INSTANCE;
    }

    public void setView(NewMessageView view) {
        this.view = view;
        bindListeners();
    }


    private void bindListeners() {
        view.bindOnClickCreateButton(new OnClickCreateButtonListener());
        view.bindOnClickChooseContactButton(new OnClickChooseContactButtonListener());
        view.bindOnClickCancelButton(new OnClickCancelButtonListener());
    }

    private class OnClickCreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }

    private class OnClickChooseContactButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }

    private class OnClickCancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }
}
