package onlineVotingSystem.ui;

import javax.swing.*;
import java.awt.*;
import onlineVotingSystem.Services.VoterServices;

    public class Voter extends JPanel {

        private VotingSystemGUI mainFrame;
        private ElectionPanel electionVotingPanel;

        public Voter(VotingSystemGUI frame, ElectionPanel electionVotingPanel) {
            this.mainFrame=frame;
            this.electionVotingPanel=electionVotingPanel;

            setLayout(new GridLayout(3,1,10,10));

            JButton loginBtn=new JButton("Login");
            JButton registerBtn=new JButton("Register");
            JButton backBtn=new JButton("Back");

            add(new JLabel("Voter Panel", SwingConstants.CENTER));
            add(loginBtn);
            add(registerBtn);
            add(backBtn);

            loginBtn.addActionListener(e -> login());
            registerBtn.addActionListener(e -> register());
            backBtn.addActionListener(e -> mainFrame.showPanel("home"));
        }

        private void login() {
            String cnic=JOptionPane.showInputDialog(this, "Enter CNIC:");
            String password = JOptionPane.showInputDialog(this, "Enter Password:");
            try {
                if (VoterServices.login(cnic, password)) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    electionVotingPanel.setVoterCNIC(cnic);
                    mainFrame.showPanel("vote");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void register() {
            String name=JOptionPane.showInputDialog(this, "Enter Name:");
            String cnic=JOptionPane.showInputDialog(this, "Enter CNIC:");
            String password=JOptionPane.showInputDialog(this, "Enter Password:");
            try {
                if (VoterServices.register(name, cnic, password)) {
                    JOptionPane.showMessageDialog(this, "Registered Successfully! You can login now.");
                    login();
                } else {
                    JOptionPane.showMessageDialog(this, "CNIC already registered!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
