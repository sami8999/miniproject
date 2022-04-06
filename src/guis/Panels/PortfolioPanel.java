package guis.Panels;

import types.User;

import javax.swing.*;
import java.awt.*;

public class PortfolioPanel extends JPanel {

    public PortfolioPanel(User user){

        this.setLayout(null);
        this.setBackground(new Color(0x123456));

        JLabel portfolioValue = new JLabel("Total Portfolio Value: $" + String.format("%.2f",user.calculateTotalPortfolioValue()));
        portfolioValue.setForeground(new Color(255,255,255));
        portfolioValue.setFont(new Font("TimesRoman", Font.BOLD, 20));
        this.add(portfolioValue);
        portfolioValue.setBounds(50, 10, 500, 100);


        JLabel totalReturn = new JLabel("Total Returns: " + String.format("%.2f",+user.calculateTotalReturn()) + "%");
        totalReturn.setForeground(new Color(255,255,255));
        totalReturn.setFont(new Font("TimesRoman", Font.BOLD, 20));
        this.add(totalReturn);
        totalReturn.setBounds(500, 10, 400, 100);

        JLabel cashAvailable = new JLabel("Cash Available: $" + String.format("%.2f",user.getCashBalance()));
        cashAvailable.setForeground(new Color(255,255,255));
        cashAvailable.setFont(new Font("TimesRoman", Font.BOLD, 20));
        this.add(cashAvailable);
        cashAvailable.setBounds(50, 60, 400, 100);

        JLabel margin = new JLabel("Leveraged Position: $" + String.format("%.2f",user.getLeveragedPosition()));
        margin.setForeground(new Color(255,255,255));
        margin.setFont(new Font("TimesRoman", Font.BOLD, 20));
        this.add(margin);
        margin.setBounds(500, 60, 400, 100);

        JLabel openPositions = new JLabel("Open Positions:");
        openPositions.setForeground(new Color(255,255,255));
        openPositions.setFont(new Font("TimesRoman", Font.BOLD, 20));
        this.add(openPositions);
        openPositions.setBounds(50, 110, 400, 100);

        int rows = user.getInvestments().size();
        String[][] data = new String[rows][4];

        for (int i = 0; i<rows; i++){
            data[i][0] = user.getInvestments().get(i).getAttributes().getTicker();
            data[i][1] = user.getInvestments().get(i).getAttributes().getDateOfPurchase();
            data[i][2] = String.valueOf(user.getInvestments().get(i).getAttributes().getAmount());
            data[i][3] = String.format("%.2f", user.getInvestments().get(i).getAttributes().getMarketPrice()
                    * user.getInvestments().get(i).getAttributes().getAmount());
        }
        String[] column ={"Investment","Date of purchase","Amount", "Market Price"};

        JTable table=new JTable(data,column);
        JScrollPane scrollableTable=new JScrollPane(table);
        this.add(scrollableTable);
        scrollableTable.setBounds(50, 190, 775, 130);




    }

}
