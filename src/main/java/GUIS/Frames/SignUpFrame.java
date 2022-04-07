package GUIS.Frames;

import GUIS.Frames.MainFrame;
import InvestmentTypes.Bond;
import InvestmentTypes.CurrencyPair;
import InvestmentTypes.Stock;
import Types.TransferAgent;
import Types.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class SignUpFrame extends JFrame {

    public SignUpFrame(Stock appleStock, Stock amazonStock, Stock microsoftStock,
                       CurrencyPair eurUsd, CurrencyPair gbpUsd, CurrencyPair audUsd, Bond treasuryBond,
                       Bond corporateBond, Bond mortgageBond){

        JTextField userIDField = new JTextField();
        JPasswordField userPasswordField = new JPasswordField();
        JLabel userIDLabel = new JLabel("Username:");
        userIDLabel.setForeground(new Color(255,255,255));
        JLabel userPasswordLabel = new JLabel("Password:");
        userPasswordLabel.setForeground(new Color(255,255,255));
        JButton signUpButton = new JButton("Create Account");
        JButton backToLogin = new JButton("Back to Login");

        JLabel userRepeatPasswordLabel = new JLabel("Repeat Password:");
        userRepeatPasswordLabel.setForeground(new Color(255,255,255));
        JPasswordField userRepeatPasswordField = new JPasswordField();

        userIDLabel.setBounds(50,100,75,25);
        userIDField.setBounds(125,100,200,25);

        userPasswordLabel.setBounds(50,150,75,25);
        userPasswordField.setBounds(125,150,200,25);

        userRepeatPasswordLabel.setBounds(50,200,110,25);
        userRepeatPasswordField.setBounds(175,200,150,25);
        signUpButton.setBounds(175,250,150,25);
        signUpButton.setFocusable(false);
        backToLogin.setBounds(50, 250, 120, 25);
        backToLogin.setFocusable(false);

        signUpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

                ArrayList<User> usersList = null;
                try {
                    usersList = TransferAgent.getInstance().readFromDatabase();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                String userID = userIDField.getText();
                String password = String.valueOf(userPasswordField.getPassword());
                String RepeatPassword = String.valueOf(userRepeatPasswordField.getPassword());

                if(userID.length()<4){
                    JOptionPane.showMessageDialog(null, "Username must be 4 or more characters",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
                else if (password.length()<6){
                    JOptionPane.showMessageDialog(null, "Password must be 6 or more characters",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
                else if(!password.equals(RepeatPassword)){
                    JOptionPane.showMessageDialog(null, "Passwords do not match",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }

                else if(TransferAgent.getInstance().findIndexOfUser(usersList, userID, password)!=-1){
                    JOptionPane.showMessageDialog(null, "Account already exists",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        User newUser = new User(userID, password);
                        usersList.add(newUser);
                        int indexOfUser = usersList.size()-1;
                        TransferAgent.getInstance().writeToDatabase(usersList);
                        setVisible(false);
                        dispose();
                        new MainFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                                audUsd, treasuryBond, corporateBond, mortgageBond, usersList, newUser, indexOfUser);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                userIDField.setText("");
                userPasswordField.setText("");
                userRepeatPasswordField.setText("");



            }
        });
        backToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                setVisible(false);
                dispose();
                try {
                    new LoginFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                            audUsd, treasuryBond, corporateBond, mortgageBond);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        this.add(userIDLabel);
        this.add(userPasswordLabel);
        this.add(userIDField);
        this.add(userPasswordField);
        this.add(signUpButton);
        this.add(backToLogin);
        this.add(userRepeatPasswordField);
        this.add(userRepeatPasswordLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x123456));
        this.setSize(420,420);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



}



