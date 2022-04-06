package guis.Panels;

import guis.Components.GraphPanel;
import guis.Components.Slider;
import investmentTypes.Stock;
import types.TransactionsException;
import types.TransferAgent;
import types.User;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class StocksPanel extends JPanel {

    JButton buyButton;
    JButton sellButton;

    public StocksPanel(Stock stock, User user){
        this.setLayout(null);
        this.setBackground(new Color(0x123456));

        // graph
        GraphPanel graph = new GraphPanel(stock);
        this.add(graph);
        graph.setBounds(0,10, 420, 295);

        // slider
        Slider slider = new Slider(stock);
        this.add(slider);
        slider.setBounds(430,10, 60, 295);
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                graph.getPlot().setDataset(graph.getGraphData(stock,slider.getValue()));
            }
        });


        // stock info
        JTextArea stockInformation =new JTextArea("Cash Available: $"+String.format("%.2f",user.getCashBalance())
                + "\n" + "\n" + "\n"+ "Share Price: $"+String.format("%.2f",stock.getAttributes().getMarketPrice()) +
                "\n" + "\n" + "\n" + "Shares bought: " + stock.getAttributes().getAmount());
        stockInformation.setFont(new Font("TimesRoman", Font.BOLD, 13));
        stockInformation.setBackground(new Color(0x123456));
        stockInformation.setForeground(new Color(255,255,255));
        this.add(stockInformation);
        stockInformation.setBounds(500, 10, 350, 140);

        // amount label
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(new Color(255,255,255));
        amountLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(amountLabel);
        amountLabel.setBounds(500, 130, 100, 100);

        // amount input
        JTextField amount = new JTextField();
        amount.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(amount);
        amount.setBounds(600, 172, 250, 20);

        // price label
        JLabel priceLabel = new JLabel("Price: $");
        priceLabel.setForeground(new Color(255,255,255));
        priceLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(priceLabel);
        priceLabel.setBounds(500, 180, 100, 100);

        // price output
        JTextField price = new JTextField();
        price.setEditable(false);
        price.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(price);
        price.setBounds(600, 222, 250, 20);
        amount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if(amount.getText().equals("")){
                    price.setText("");
                }
                else{
                    try{
                        double actualPrice = Integer.parseInt(amount.getText()) * stock.getAttributes().getMarketPrice();
                        if (actualPrice<=0){
                            throw new NumberFormatException();
                        }
                        price.setText(String.format("%.2f", actualPrice));
                    } catch (NumberFormatException error){
                        price.setText("Invalid");
                    }
                }
            }
        });

        // buy button
        buyButton = new JButton("Buy");
        buyButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(buyButton);
        buyButton.setBounds(500, 265, 170, 40);
        buyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

            try{
                TransferAgent.getInstance().buyInvestment(stock, Integer.parseInt(amount.getText()), user);
                stockInformation.setText("Cash Available: $"+String.format("%.2f",user.getCashBalance())
                        + "\n" + "\n" + "\n"+ "Share Price: $"+String.format("%.2f",stock.getAttributes().getMarketPrice()) +
                        "\n" + "\n" + "\n" + "Shares bought: " + stock.getAttributes().getAmount());
            } catch (NumberFormatException error){;
                JOptionPane.showMessageDialog(null, "Invalid amount entered",
                        "Invalid", JOptionPane.ERROR_MESSAGE);
            } catch (TransactionsException error) {
                JOptionPane.showMessageDialog(null, error.getMessage(),
                        "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            amount.setText("");
            price.setText("");

            }
        });

        // sell button
        sellButton = new JButton("Sell");
        sellButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(sellButton);
        sellButton.setBounds(680, 265, 170, 40);
        sellButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

                try{
                    TransferAgent.getInstance().sellInvestment(stock, Integer.parseInt(amount.getText()), user);
                    stockInformation.setText("Cash Available: $"+String.format("%.2f",user.getCashBalance())
                            + "\n" + "\n" + "\n"+ "Share Price: $"+String.format("%.2f",stock.getAttributes().getMarketPrice()) +
                            "\n" + "\n" + "\n" + "Shares bought: " + stock.getAttributes().getAmount());
                }catch (NumberFormatException error){
                    JOptionPane.showMessageDialog(null, "Invalid amount entered",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } catch (TransactionsException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(),
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
                amount.setText("");
                price.setText("");
            }
        });
    }
}
