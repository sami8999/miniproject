package GUIS.Frames;

import GUIS.Panels.BondsPanel;
import GUIS.Panels.ForexPanel;
import GUIS.Panels.PortfolioPanel;
import GUIS.Panels.StocksPanel;
import InvestmentTypes.Bond;
import InvestmentTypes.CurrencyPair;
import InvestmentTypes.Stock;
import Types.TransferAgent;
import Types.User;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;


public class MainFrame extends JFrame {

    public MainFrame(Stock appleStock, Stock amazonStock, Stock microsoftStock,
                     CurrencyPair eurUsd, CurrencyPair gbpUsd, CurrencyPair audUsd, Bond treasuryBond,
                     Bond corporateBond, Bond mortgageBond, ArrayList<User> usersList, User newUser, int indexOfUser
                     ){

        // create main tabs
        JPanel portfolioPanel = new PortfolioPanel(newUser);
        JPanel stocksPanel = new JPanel();
        JPanel forexPanel = new JPanel();
        JPanel bondsPanel = new JPanel();

        // create sub tabs
        JPanel applePanel = new StocksPanel(appleStock, newUser);
        JPanel microsoftPanel = new StocksPanel(microsoftStock, newUser);
        JPanel amazonPanel = new StocksPanel(amazonStock, newUser);

        JPanel eurUsdPanel = new ForexPanel(eurUsd, newUser);
        JPanel gbpUsdPanel = new ForexPanel(gbpUsd, newUser);
        JPanel audUsdPanel = new ForexPanel(audUsd,newUser);

        JPanel treasuryBondPanel = new BondsPanel(treasuryBond, newUser);
        JPanel corporateBondPanel = new BondsPanel(corporateBond, newUser);
        JPanel mortgageBondPanel = new BondsPanel(mortgageBond, newUser);

        // adding subTabs into each mainTab
        JTabbedPane stocksTabs = new JTabbedPane();
        stocksTabs.add("AAPL", applePanel);
        stocksTabs.add("MSFT", microsoftPanel);
        stocksTabs.add("AMZN", amazonPanel);
        stocksPanel.setLayout(new BorderLayout());
        stocksPanel.add(stocksTabs);
        // updating subTabs dynamically
        stocksTabs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JPanel applePanel = new StocksPanel(appleStock, newUser);
                JPanel microsoftPanel = new StocksPanel(microsoftStock, newUser);
                JPanel amazonPanel = new StocksPanel(amazonStock, newUser);
                stocksTabs.setComponentAt(0, applePanel);
                stocksTabs.setComponentAt(1, microsoftPanel);
                stocksTabs.setComponentAt(2, amazonPanel);
            }
        });

        JTabbedPane forexTabs = new JTabbedPane();
        forexTabs.add("EUR/USD", eurUsdPanel);
        forexTabs.add("GBP/USD", gbpUsdPanel);
        forexTabs.add("AUD/USD", audUsdPanel);
        forexPanel.setLayout(new BorderLayout());
        forexPanel.add(forexTabs);
        forexTabs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JPanel eurUsdPanel = new ForexPanel(eurUsd, newUser);
                JPanel gbpUsdPanel = new ForexPanel(gbpUsd, newUser);
                JPanel audUsdPanel = new ForexPanel(audUsd,newUser);
                forexTabs.setComponentAt(0, eurUsdPanel);
                forexTabs.setComponentAt(1, gbpUsdPanel);
                forexTabs.setComponentAt(2, audUsdPanel);
            }
        });

        JTabbedPane bondsTabs = new JTabbedPane();
        bondsTabs.add("T-BILL", treasuryBondPanel);
        bondsTabs.add("Corp-Bond", corporateBondPanel);
        bondsTabs.add("Morg-Bond", mortgageBondPanel);
        bondsPanel.setLayout(new BorderLayout());
        bondsPanel.add(bondsTabs);
        bondsTabs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JPanel treasuryBondPanel = new BondsPanel(treasuryBond, newUser);
                JPanel corporateBondPanel = new BondsPanel(corporateBond, newUser);
                JPanel mortgageBondPanel = new BondsPanel(mortgageBond, newUser);
                bondsTabs.setComponentAt(0, treasuryBondPanel);
                bondsTabs.setComponentAt(1, corporateBondPanel);
                bondsTabs.setComponentAt(2, mortgageBondPanel);
            }
        });

        // adding mainTabs into tabs
        JTabbedPane mainTabs = new JTabbedPane();
        mainTabs.add("Portfolio", portfolioPanel);
        mainTabs.add("Stocks", stocksPanel);
        mainTabs.add("Forex", forexPanel);
        mainTabs.add("Bonds", bondsPanel);
        mainTabs.setSelectedIndex(1);
        // updating mainTabs dynamically
        mainTabs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                mainTabs.setComponentAt(0, new PortfolioPanel(newUser));

                JPanel applePanel = new StocksPanel(appleStock, newUser);
                JPanel microsoftPanel = new StocksPanel(microsoftStock, newUser);
                JPanel amazonPanel = new StocksPanel(amazonStock, newUser);
                stocksTabs.setComponentAt(0, applePanel);
                stocksTabs.setComponentAt(1, microsoftPanel);
                stocksTabs.setComponentAt(2, amazonPanel);

                JPanel eurUsdPanel = new ForexPanel(eurUsd, newUser);
                JPanel gbpUsdPanel = new ForexPanel(gbpUsd, newUser);
                JPanel audUsdPanel = new ForexPanel(audUsd,newUser);
                forexTabs.setComponentAt(0, eurUsdPanel);
                forexTabs.setComponentAt(1, gbpUsdPanel);
                forexTabs.setComponentAt(2, audUsdPanel);

                JPanel treasuryBondPanel = new BondsPanel(treasuryBond, newUser);
                JPanel corporateBondPanel = new BondsPanel(corporateBond, newUser);
                JPanel mortgageBondPanel = new BondsPanel(mortgageBond, newUser);
                bondsTabs.setComponentAt(0, treasuryBondPanel);
                bondsTabs.setComponentAt(1, corporateBondPanel);
                bondsTabs.setComponentAt(2, mortgageBondPanel);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                usersList.set(indexOfUser, newUser);
                try {
                    TransferAgent.getInstance().writeToDatabase(usersList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(790, 0 , 100, 20);
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                usersList.set(indexOfUser, newUser);
                try {
                    TransferAgent.getInstance().writeToDatabase(usersList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally{
                    setVisible(false);
                    dispose();
                    TransferAgent.getInstance().resetInvestments(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                            audUsd, treasuryBond, corporateBond, mortgageBond);
                    try {
                        new LoginFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                                audUsd, treasuryBond, corporateBond, mortgageBond);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        this.setSize(900,415);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(logout);
        this.add(mainTabs);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);





    }
}
