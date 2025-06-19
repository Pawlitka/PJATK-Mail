package org.example;

import org.example.presentation.emailscreen.EmailController;
import org.example.presentation.emailscreen.EmailView;

public class Main {
    private static EmailController emailViewController;

    public static void main(String[] args) {
//        myFrame frame = new myFrame();
        EmailView emailView = new EmailView();
        emailViewController = new EmailController(emailView);
//        EmailFrame frame1 = new EmailFrame();
//        frame1.start();
    }
}