package org.example.presentation.emailscreen.components;

import org.example.model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class BottomPanel extends JPanel {
    private final JSplitPane mainContainer;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private final JList<Message> listOfSentEmails = new JList<>();
    private JTextArea textArea;
    private final JPanel recevierAndTopicPanel;

    public BottomPanel() {
        super(new BorderLayout());
        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());
        recevierAndTopicPanel = new JPanel(new GridBagLayout());
        mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        setup();
    }

    private void setup() {
        mainContainer.setResizeWeight(0.2);
        mainContainer.setDividerLocation(0.2);
        mainContainer.setContinuousLayout(true);
        mainContainer.setEnabled(false);
        setLeftPanel();
        setRightPanel();
        this.add(mainContainer, BorderLayout.CENTER);
    }

    private void setLeftPanel() {
        sentEmailsLabel();
        listOfSentEmails();
    }

    private void setRightPanel() {
//        receiverLabel();
//        themeLabel();
        receiverAndTopicContent();
        messageContent();
//        receiverContent();
    }

    private void sentEmailsLabel() {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel textLabel = new JLabel("Sent emails: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        leftPanel.add(textLabel, constraints);
    }

    private void listOfSentEmails() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 10;
        constraints.weighty = 10;
        constraints.fill = GridBagConstraints.BOTH;
        leftPanel.add(listOfSentEmails, constraints);
    }

//    private void receiverLabel() {
//        GridBagConstraints constraints = new GridBagConstraints();
//        JLabel textLabel = new JLabel("TO: ");
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        constraints.gridheight = 1;
//        constraints.weighty = 0.5;
//        constraints.anchor = GridBagConstraints.NORTHWEST;
//        rightPanel.add(textLabel, constraints);
//    }
//
//    private void themeLabel() {
//        GridBagConstraints constraints = new GridBagConstraints();
//        JLabel textLabel = new JLabel("THEME: ");
//        constraints.gridx = 0;
//        constraints.gridy = 1;
//        constraints.gridheight = 1;
//        constraints.weighty = 0.5;
//        constraints.anchor = GridBagConstraints.NORTHWEST;
//        rightPanel.add(textLabel, constraints);
//    }
    private void receiverAndTopicContent() {
        GridBagConstraints constraints = new GridBagConstraints();
        recevierAndTopicPanel.setBackground(Color.red);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.weighty = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        rightPanel.add(recevierAndTopicPanel,constraints);
    }

    private void messageContent() {
        GridBagConstraints constraints = new GridBagConstraints();
        textArea = new JTextArea();
        textArea.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.weighty = 10;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        rightPanel.add(textArea, constraints);
    }

    public void bindOnClickSentMessage(Consumer<Message> listener) {
        listOfSentEmails.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                Message selectedMessage = listOfSentEmails.getSelectedValue();
                if (selectedMessage != null) {
                    listener.accept(selectedMessage);
                }
            }
        });
    }

    public void setMessageContent(String content) {
        textArea.setText("");
        textArea.append(content);
    }

    public void setListOfSentEmails(ArrayList<Message> messages) {
//        listOfSentEmails = new JList<>(messages.toArray(new Message[0]));
        listOfSentEmails.clearSelection();
        DefaultListModel<Message> listModel = new DefaultListModel<>();
        listModel.addAll(messages);
        listOfSentEmails.setModel(listModel);
        listOfSentEmails.updateUI();
    }

    private void receiverContent() {
        GridBagConstraints constraints = new GridBagConstraints();
        textArea = new JTextArea();
        textArea.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        rightPanel.add(recevierAndTopicPanel, constraints);
    }
}
