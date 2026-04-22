package onlineVotingSystem;

import javax.swing.SwingUtilities;
import onlineVotingSystem.ui.VotingSystemGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VotingSystemGUI();
        });
    }
}

