package onlineVotingSystem.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import onlineVotingSystem.model.Election;

public class Admin extends JPanel {

    VotingSystemGUI mainFrame;

    public Admin(VotingSystemGUI frame) {
        this.mainFrame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // ---- Title ----
        JLabel title = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // ---- Button Panel ----
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton createElectionBtn = styledButton("Create New Election");
        JButton addCandidateBtn = styledButton("Add Candidate");
        JButton logoutBtn = styledButton("Logout");

        buttonPanel.add(createElectionBtn);
        buttonPanel.add(addCandidateBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Event Listeners
        createElectionBtn.addActionListener(e -> createElection());
        addCandidateBtn.addActionListener(e -> addCandidate());
        logoutBtn.addActionListener(e -> mainFrame.showPanel("home"));
    }

    // ---- Stylish Button Method ----
    private JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }

    // ---- Create Election ----
    private void createElection() {
        String title = JOptionPane.showInputDialog(this, "Enter Election Title:");
        try {
            int id = onlineVotingSystem.Services.AdminServices.createElection(title);
            JOptionPane.showMessageDialog(this, "Election Created (ID: " + id + ")");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ---- Add Candidate ----
    private void addCandidate() {
        try {
            List<Election> elections = onlineVotingSystem.dao.ElectionDao.getAll();

            StringBuilder sb = new StringBuilder("Choose Election:\n\n");
            for (Election e : elections) {
                sb.append(e.getId()).append(" — ").append(e.getTitle()).append("\n");
            }

            String electionIdStr = JOptionPane.showInputDialog(this, sb.toString());
            int electionId = Integer.parseInt(electionIdStr);

            String name = JOptionPane.showInputDialog(this, "Candidate Name:");
            String party = JOptionPane.showInputDialog(this, "Party Name:");

            onlineVotingSystem.Services.AdminServices.addCandidate(electionId, name, party);

            JOptionPane.showMessageDialog(this, "Candidate Added Successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
