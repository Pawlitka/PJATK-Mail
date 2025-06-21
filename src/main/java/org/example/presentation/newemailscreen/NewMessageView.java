package org.example.presentation.newemailscreen;

import org.example.presentation.contactsscreen.ContactsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewMessageView extends JFrame {
    private final JPanel mainPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JPanel recevierAndTopicPanel;
    private  JTextField receiverInputField;
    private  JTextField topicInputField;
    private final JPanel topicContainer;
    private final JPanel receiverContainer;
    private JTextArea textArea;
    private final JPanel buttonsContainer;
    private JButton createButton;
    private JButton chooseContactButton;
    private JButton cancelButton;


    public NewMessageView() {
        mainPanel = new JPanel(new GridBagLayout());
        topPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel(new GridBagLayout());
        recevierAndTopicPanel = new JPanel(new GridBagLayout());
        topicContainer = new JPanel(new GridBagLayout());
        receiverContainer = new JPanel(new GridBagLayout());
        buttonsContainer = new JPanel();
        setContent();
    }

    private void setContent() {
        setMainPanel();
        this.add(mainPanel);
        this.setSize(350, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Create new mail");
    }

    private void setMainPanel() {
        setTopPanel();
        setBottomPanel();
        setButtonsContainer();
    }

    private void setTopPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 3;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(topPanel, constraints);
        createReceiverInputField();
        createTopicInputField();
    }

    private void setBottomPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 12;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(textArea, constraints);

    }
    private void setButtonsContainer() {
        createButton();
        cancelButton();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonsContainer, constraints);
    }

    private void createReceiverInputField() {
        createReceiverLabel();
        createReceiverField();
        createChooseContactButton();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        topPanel.add(receiverContainer, constraints);
    }

    private void createReceiverLabel() {
        JLabel label = new JLabel("TO:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.2;
        constraints.fill = GridBagConstraints.BOTH;
        receiverContainer.add(label, constraints);
    }

    private void createReceiverField() {
        receiverInputField = new JTextField();
        receiverInputField.setEditable(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 9.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        receiverContainer.add(receiverInputField, constraints);
    }

    private void createChooseContactButton() {
        chooseContactButton = new JButton("Choose");
        chooseContactButton.addActionListener(e -> new ContactsView(this::setReceiverEmail));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 3.0;
        constraints.fill = GridBagConstraints.BOTH;
        receiverContainer.add(chooseContactButton, constraints);
    }

    private void createTopicInputField() {
        createTopicLabel();
        createTopicField();
        createTopicBlankField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        topPanel.add(topicContainer, constraints);
    }

    private void createTopicLabel() {
        JLabel label = new JLabel("TOPIC:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        topicContainer.add(label, constraints);
    }

    private void createTopicField() {
        topicInputField = new JTextField();
        topicInputField.setEditable(true);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        topicContainer.add(topicInputField, constraints);
    }

    private void createTopicBlankField() {
        JLabel label = new JLabel();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        topicContainer.add(label, constraints);
    }

    private JButton createButton() {
        createButton = new JButton("Add");
        buttonsContainer.add(createButton);
        return createButton;
    }

    private JButton cancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> dispose());
        buttonsContainer.add(button);
        return button;
    }

    private void setReceiverEmail(String email) {
        receiverInputField.setText(email);
    }

    public void bindOnClickCreateButton(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void bindOnClickChooseContactButton(ActionListener listener) {
        chooseContactButton.addActionListener(listener);
    }

    public void bindOnClickCancelButton(ActionListener listener) {
        chooseContactButton.addActionListener(listener);
    }


}
