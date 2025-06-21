package org.example.presentation.emailscreen;

import org.example.App;
import org.example.presentation.emailscreen.components.BottomPanel;
import org.example.presentation.emailscreen.components.TopPanel;

import javax.swing.*;
import java.awt.*;

public class EmailView extends JFrame {
    private final JPanel mainPanel;
    private final TopPanel topPanel;
    private final BottomPanel bottomPanel;


    public EmailView() {
        mainPanel = new JPanel(new GridBagLayout());
        topPanel = new TopPanel();
        bottomPanel = new BottomPanel();
        setContent();
    }

    private void setContent() {
        setMainPanel();
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("PJATK EMAIL");
    }

    private void setMainPanel() {
        mainPanel.setBackground(Color.GRAY);
        addTopPanel();
        setBottomPanel();
    }

    private void addTopPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(topPanel, constraints);
    }

    private void setBottomPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 15;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(bottomPanel, constraints);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }
}
