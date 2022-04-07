package guis.frames;

import investmentTypes.Bond;
import investmentTypes.CurrencyPair;
import investmentTypes.Stock;
import types.TransferAgent;
import types.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class LoginFrame extends JFrame{


    Timer tm;
    int x = 0;
    JLabel pic;

    String[] list = {
            "guis/images/portfolio.png",//0 src/guis/images/portfolio.png
            "guis/images/stocks.png",//1
            "guis/images/forex.png",//2
            "guis/images/bonds.png",//3
    };

    public LoginFrame(Stock appleStock, Stock amazonStock, Stock microsoftStock,
                      CurrencyPair eurUsd, CurrencyPair gbpUsd, CurrencyPair audUsd, Bond treasuryBond,
                      Bond corporateBond, Bond mortgageBond) throws IOException, ClassNotFoundException {


        JLabel title = new JLabel("Sami's Object Oriented Trading Simulator");
        title.setForeground(new Color(255,255,255));
        title.setBounds(30, 30, 400, 25);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        pic = new JLabel();
        pic.setBounds(10, 80, 413, 150);
        SetImageSize(1);
        tm = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0;
            }
        });

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        JTextField userIDField = new JTextField();
        JPasswordField userPasswordField = new JPasswordField();
        JLabel userIDLabel = new JLabel("Username:");
        JLabel userPasswordLabel = new JLabel("Password:");

        userIDLabel.setBounds(50,250,75,25);
        userIDLabel.setForeground(new Color(255,255,255));
        userPasswordLabel.setBounds(50,300,75,25);
        userPasswordLabel.setForeground(new Color(255,255,255));


        userIDField.setBounds(125,250,200,25);
        userPasswordField.setBounds(125,300,200,25);

        loginButton.setBounds(125,350,98,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                ArrayList<User> usersList = null;
                try {
                    usersList = TransferAgent.getInstance().readFromDatabase();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String userID = userIDField.getText();
                String password = String.valueOf(userPasswordField.getPassword());
                int indexOfUser = TransferAgent.getInstance().findIndexOfUser(usersList, userID, password);
                if (indexOfUser==-1){
                    JOptionPane.showMessageDialog(null, "Invalid username and password",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    setVisible(false);
                    dispose();
                    User user = usersList.get(indexOfUser);
                    TransferAgent.getInstance().syncInvestments(user, appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                            audUsd, treasuryBond, corporateBond, mortgageBond);
                    new MainFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                            audUsd, treasuryBond, corporateBond, mortgageBond, usersList, user, indexOfUser);

                }

            }
        });

        signUpButton.setBounds(227,350,98,25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                setVisible(false);
                dispose();
                new SignUpFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                        audUsd, treasuryBond, corporateBond, mortgageBond);
            }
        });

        this.add(title);
        this.add(pic);
        tm.start();
        this.add(userIDLabel);
        this.add(userPasswordLabel);
        this.add(userIDField);
        this.add(userPasswordField);
        this.add(loginButton);
        this.add(signUpButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,450);
        this.getContentPane().setBackground(new Color(0x123456));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
}
