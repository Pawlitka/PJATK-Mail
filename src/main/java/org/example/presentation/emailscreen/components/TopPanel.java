package org.example.presentation.emailscreen.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private final JButton emailButton;
    private final JButton contactButton;

    public TopPanel() {
        super(new GridBagLayout());
        super.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1,true));
        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());
        emailButton = createEmailButton();
        contactButton = createContactButton();
        setup();
    }

    private void setup() {
        setLeftPanel();
        setRightPanel();
    }

    private void setRightPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.weightx = 10;
        constraints.weighty = 0.8;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(rightPanel, constraints);
    }

    private void setLeftPanel() {
        addEmailButton();
        addContactButton();
        addLeftPanel();
    }

    public void addEmailButton() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(16,2,16,5);
        constraints.weighty = 0.7;
        constraints.weightx = 0.3;
        constraints.fill = GridBagConstraints.BOTH;
        leftPanel.add(emailButton, constraints);
    }

    public void addContactButton() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = 0.7;
        constraints.weightx = 0.3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(16,5,16,2);
        leftPanel.add(contactButton, constraints);
    }

    public void addLeftPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        this.add(leftPanel, constraints);
    }

    private JButton createEmailButton() {
        JButton button = new JButton();
        ImageIcon createEmailIcon = new ImageIcon("src/main/java/add_email.png");
        button.setIcon(createEmailIcon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Arial",Font.BOLD, 12));
        button.setText("Create Email");
        return button;
    }

    private JButton createContactButton() {
        ImageIcon createContactIcon = new ImageIcon("src/main/java/add_contact_icon.png");
        JButton button = new JButton();
        button.setText("Create Contact");
        button.setIcon(createContactIcon);
        button.setFont(new Font("Arial",Font.BOLD, 12));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
//        button.addActionListener(e -> EMAIL_VIEW_CONTROLLER.onCreateContact());
        return button;
    }
}
