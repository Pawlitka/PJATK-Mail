package org.example;

import org.example.presentation.emailscreen.EmailController;
import org.example.presentation.emailscreen.EmailView;
import org.example.presentation.newcontactscreen.NewContactController;
import org.example.presentation.newcontactscreen.NewContactView;

public class App {
    private static App INSTANCE;

    private App() {
    }

    public static App getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new App();
        }
        return INSTANCE;
    }

    public void openEmailScreen() {
        EmailView emailView = new EmailView();
        EmailController.getInstance(emailView, INSTANCE);
    }

    public void openNewContactScreen() {
        NewContactView contactView = new NewContactView();
        NewContactController.getInstance(INSTANCE).setView(contactView);
    }

    public void openNewMessageScreen() {
//        EmailView emailView = new EmailView(INSTANCE);
//        EmailController.getInstance(emailView);
    }
}
