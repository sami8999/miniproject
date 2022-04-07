package GUIS;

import testGUIS.Graphs;
import types.Currency;
import types.User;
import types.TransactionsException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ForexPanel extends JPanel {

    JButton buyButton;
    JButton sellButton;

    public ForexPanel(Currency currencyPair, User user){

        this.setLayout(null);
        this.setBackground(new Color(0x123456));

        // graph
        GraphPanel graph = new GraphPanel(currencyPair);
        this.add(graph);
        graph.setBounds(0,10, 420, 295);

        // slider
        Slider slider = new Slider(currencyPair);
        this.add(slider);
        slider.setBounds(430,10, 60, 295);
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                graph.getPlot().setDataset(graph.getGraphData(currencyPair, slider.getValue()));
            }
        });

        // currency pair info
        JTextArea currencyPairInformation =new JTextArea("Cash Available: £"+String.format("%.2f",user.getCashBalance())
                + "\n" + "\n" + "Leveraged Position: £" + String.format("%.2f",user.getLeveragedPosition())
                + "\n" + "\n" + "Margin: "+currencyPair.getMargin()
                + "\n" + "\n" + "Exchange Rate: "+String.format("%.2f",currencyPair.getMarketPrice()) +
                "\n" + "\n" + "Units bought: " + currencyPair.getAmount());
        currencyPairInformation.setFont(new Font("TimesRoman", Font.BOLD, 13));
        currencyPairInformation.setBackground(new Color(0x123456));
        currencyPairInformation.setForeground(new Color(255,255,255));
        this.add(currencyPairInformation);
        currencyPairInformation.setBounds(500, 10, 350, 163);

        // amount label
        JLabel amountLabel = new JLabel("Units:");
        amountLabel.setForeground(new Color(255,255,255));
        amountLabel.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(amountLabel);
        amountLabel.setBounds(500, 145, 100, 100);

        // amount input
        JTextField amount = new JTextField();
        amount.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(amount);
        amount.setBounds(600, 185, 250, 17);

        // units price label
        JLabel priceLabel = new JLabel("Units Price: £");
        priceLabel.setForeground(new Color(255,255,255));
        priceLabel.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(priceLabel);
        priceLabel.setBounds(500, 180, 100, 100);

        // units price output
        JTextField price = new JTextField();
        price.setEditable(false);
        price.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(price);
        price.setBounds(600, 220, 250, 17);


        // leveragedPrice label
        JLabel leveragedPriceLabel = new JLabel("Leveraged Price: £");
        leveragedPriceLabel.setForeground(new Color(255,255,255));
        leveragedPriceLabel.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(leveragedPriceLabel);
        leveragedPriceLabel.setBounds(500, 210, 150, 100);

        // leveragedPrice output
        JTextField leveragedPrice = new JTextField();
        leveragedPrice.setEditable(false);
        leveragedPrice.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(leveragedPrice);
        leveragedPrice.setBounds(650, 253, 200, 17);

        amount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if(amount.getText().equals("")){
                    price.setText("");
                    leveragedPrice.setText("");
                }
                else{
                    try{
                        double totalPrice = Integer.parseInt(amount.getText()) * currencyPair.getMarketPrice();
                        double userPrice = Integer.parseInt(amount.getText()) * currencyPair.getMarketPrice() * currencyPair.getMargin();
                        if (totalPrice<=0){
                            throw new NumberFormatException();
                        }
                        price.setText(String.format("%.2f", totalPrice));
                        leveragedPrice.setText(String.format("%.2f", userPrice));
                    } catch (NumberFormatException error){
                        price.setText("Invalid");
                        leveragedPrice.setText("Invalid");
                    }
                }
            }
        });

        // buyButton
        buyButton = new JButton("Buy");
        buyButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(buyButton);
        buyButton.setBounds(500, 280, 170, 25);
        buyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

                try{
                    user.buyInvestment(currencyPair, Integer.parseInt(amount.getText()));
                    currencyPairInformation.setText("Cash Available: £"+String.format("%.2f",user.getCashBalance())
                            + "\n" + "\n" + "Leveraged Position: £" + String.format("%.2f",user.getLeveragedPosition())
                            + "\n" + "\n" + "Margin: "+currencyPair.getMargin()
                            + "\n" + "\n" + "Exchange Rate: "+String.format("%.2f",currencyPair.getMarketPrice()) +
                            "\n" + "\n" + "Units bought: " + currencyPair.getAmount());
                } catch (NumberFormatException error){;
                    JOptionPane.showMessageDialog(null, "Invalid amount entered",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } catch (TransactionsException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(),
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }

                amount.setText("");
                price.setText("");
                leveragedPrice.setText("");

            }
        });

        //sellButton
        sellButton = new JButton("Sell");
        sellButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(sellButton);
        sellButton.setBounds(680, 280, 170, 25);
        sellButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

                try{
                    user.sellInvestment(currencyPair, Integer.parseInt(amount.getText()));
                    currencyPairInformation.setText("Cash Available: £"+String.format("%.2f",user.getCashBalance())
                            + "\n" + "\n" + "Leveraged Position: £" + String.format("%.2f",user.getLeveragedPosition())
                            + "\n" + "\n" + "Margin: "+currencyPair.getMargin()
                            + "\n" + "\n" + "Exchange Rate: "+String.format("%.2f",currencyPair.getMarketPrice()) +
                            "\n" + "\n" + "Units bought: " + currencyPair.getAmount());
                } catch (NumberFormatException error){;
                    JOptionPane.showMessageDialog(null, "Invalid amount entered",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } catch (TransactionsException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(),
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }

                amount.setText("");
                price.setText("");
                leveragedPrice.setText("");

            }
        });

    }

}
