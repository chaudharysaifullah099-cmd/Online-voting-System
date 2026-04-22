package onlineVotingSystem.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import onlineVotingSystem.Services.VoterServices;
import onlineVotingSystem.dao.CandidateDao;
import onlineVotingSystem.dao.ElectionDao;
import onlineVotingSystem.model.Candidate;
import onlineVotingSystem.model.Election;

    public class ElectionPanel extends JPanel {

        private VotingSystemGUI mainFrame;
        private String voterCNIC;

        public ElectionPanel(VotingSystemGUI frame) {
            this.mainFrame = frame;
            setLayout(new BorderLayout());

            JButton backBtn = new JButton("Back");
            add(backBtn, BorderLayout.SOUTH);
            backBtn.addActionListener(e -> mainFrame.showPanel("voter"));
        }

        public void setVoterCNIC(String cnic) {
            this.voterCNIC = cnic;
            SwingUtilities.invokeLater(this::showVoting);
        }

        private void showVoting() {
            try {
                List<Election> elections = ElectionDao.getAll();
                StringBuilder sb = new StringBuilder();
                for (Election e : elections) {
                    sb.append(e.getId()).append(": ").append(e.getTitle()).append("\n");
                }
                String electionIdStr = JOptionPane.showInputDialog(this, "Select Election:\n" + sb);
                if (electionIdStr == null) return;

                int electionId = Integer.parseInt(electionIdStr);

                List<Candidate> candidates = CandidateDao.getByElection(electionId);
                StringBuilder sb2 = new StringBuilder();
                for (Candidate c : candidates) {
                    sb2.append(c.getId()).append(": ").append(c.getName()).append(" (").append(c.getParty()).append(")\n");
                }
                String candidateIdStr = JOptionPane.showInputDialog(this, "Select Candidate:\n" + sb2);
                if (candidateIdStr == null) return;

                int candidateId = Integer.parseInt(candidateIdStr);

                VoterServices.vote(voterCNIC, electionId, candidateId);
                JOptionPane.showMessageDialog(this, "Vote Cast Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
