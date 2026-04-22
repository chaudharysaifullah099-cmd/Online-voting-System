package onlineVotingSystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

    public class Home extends JPanel {

        VotingSystemGUI mainFrame;

        public Home(VotingSystemGUI frame) {
            this.mainFrame = frame;
            setLayout(new GridLayout(3, 1, 10, 10));

            JLabel title = new JLabel("Welcome to Online Voting System", SwingConstants.CENTER);
            JButton adminBtn = new JButton("Admin Login");
            JButton voterBtn = new JButton("Voter Login/Register");

            add(title);
            add(adminBtn);
            add(voterBtn);

            adminBtn.addActionListener(this::adminLogin);
            voterBtn.addActionListener(e -> mainFrame.showPanel("voter"));
        }

        private void adminLogin(ActionEvent e) {
            String username = JOptionPane.showInputDialog(this, "Admin Username:");
            String password = JOptionPane.showInputDialog(this, "Admin Password:");

            try {
                if (onlineVotingSystem.Services.AdminServices.login(username, password)) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    mainFrame.showPanel("admin");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


