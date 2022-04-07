package GUIS;

import GUIS.BondsPanel;
import GUIS.ForexPanel;
import GUIS.PortfolioPanel;
import GUIS.StocksPanel;
import types.Bond;
import types.Currency;
import types.Stock;
import types.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(Stock appleStock, Stock amazonStock, Stock microsoftStock,
                     Currency eurUsd, Currency gbpUsd, Currency audUsd, Bond treasuryBond,
                     Bond corporateBond, Bond mortgageBond, User newUser
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

        this.setSize(900,415);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainTabs);
        this.setResizable(false);
        this.setVisible(true);





    }
}
