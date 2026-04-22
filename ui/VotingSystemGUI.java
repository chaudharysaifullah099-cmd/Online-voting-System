package onlineVotingSystem.ui;

import javax.swing.*;
import java.awt.*;

public class VotingSystemGUI extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    Home homePanel;
    Admin adminPanel;
    Voter voterPanel;
    ElectionPanel electionVotingPanel;

    public VotingSystemGUI() {
        setTitle("Online Voting System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize panels
        homePanel = new Home(this);
        adminPanel = new Admin(this);
        electionVotingPanel = new ElectionPanel(this);
        voterPanel = new Voter(this, electionVotingPanel); // pass reference

        // Add panels to CardLayout
        mainPanel.add(homePanel, "home");
        mainPanel.add(adminPanel, "admin");
        mainPanel.add(voterPanel, "voter");
        mainPanel.add(electionVotingPanel, "vote");

        add(mainPanel);
        showPanel("home");
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VotingSystemGUI::new);
    }
}


