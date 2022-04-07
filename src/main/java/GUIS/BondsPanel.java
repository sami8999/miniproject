package GUIS;

import testGUIS.Graphs;
import types.Bond;
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

public class BondsPanel extends JPanel {

    JButton buyButton;
    JButton sellButton;

    public BondsPanel(Bond bond, User user){

        this.setLayout(null);
        this.setBackground(new Color(0x123456));

        // graph
        GraphPanel graph = new GraphPanel(bond);
        this.add(graph);
        graph.setBounds(0,10, 420, 295);

        Slider slider = new Slider(bond);
        this.add(slider);
        slider.setBounds(430,10, 60, 295);
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                graph.getPlot().setDataset(graph.getGraphData(bond, slider.getValue()));
            }
        });

        // bond info
        JTextArea bondInformation =new JTextArea("Cash Available: £"+String.format("%.2f",user.getCashBalance())
                + "\n" + "\n" + "Par Value: £" + bond.getParValue()
                + "\n" + "Market Price: £" + bond.getMarketPrice()
                +"\n" + "Bond Rating: " + bond.getBondRating()
                + "\n" +"\n" + "Coupon Rate: "+ bond.getCouponRate() +"%"
                + "\n" + "Coupon Frequency: " + bond.getCouponDate()
                + "\n" + "Years To Maturity: " + bond.getYearsToMaturity() + " Years"
                + "\n" + "\n"  + "Bonds bought: " + bond.getAmount());

        bondInformation.setFont(new Font("TimesRoman", Font.BOLD, 13));
        bondInformation.setBackground(new Color(0x123456));
        bondInformation.setForeground(new Color(255,255,255));
        this.add(bondInformation);
        bondInformation.setBounds(500, 10, 350, 210);

        // amount label
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(new Color(255,255,255));
        amountLabel.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(amountLabel);
        amountLabel.setBounds(500, 225, 100, 15);

        // amount input
        JTextField amount = new JTextField();
        amount.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(amount);
        amount.setBounds(600, 225, 250, 15);

        // price label
        JLabel priceLabel = new JLabel("Price: £");
        priceLabel.setForeground(new Color(255,255,255));
        priceLabel.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(priceLabel);
        priceLabel.setBounds(500, 255, 100, 15);

        // price output
        JTextField price = new JTextField();
        price.setEditable(false);
        price.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(price);
        price.setBounds(600, 255, 250, 15);
        amount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if(amount.getText().equals("")){
                    price.setText("");
                }
                else{
                    try{
                        double actualPrice = Integer.parseInt(amount.getText()) * bond.getMarketPrice();
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


        buyButton = new JButton("Buy");
        buyButton.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(buyButton);
        buyButton.setBounds(500, 285, 170, 20);
        buyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

                try{
                    user.buyInvestment(bond, Integer.parseInt(amount.getText()));
                    bondInformation.setText("Cash Available: £"+String.format("%.2f",user.getCashBalance())
                            + "\n" + "\n" + "Par Value: £" + bond.getParValue()
                            + "\n" + "Market Price: £" + bond.getMarketPrice()
                            +"\n" + "Bond Rating: " + bond.getBondRating()
                            + "\n" +"\n" + "Coupon Rate: "+ bond.getCouponRate() +"%"
                            + "\n" + "Coupon Frequency: " + bond.getCouponDate()
                            + "\n" + "Years To Maturity: " + bond.getYearsToMaturity() + " Years"
                            + "\n" + "\n"  + "Bonds bought: " + bond.getAmount());
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

        sellButton = new JButton("Sell");
        sellButton.setFont(new Font("TimesRoman", Font.BOLD, 13));
        this.add(sellButton);
        sellButton.setBounds(680, 285, 170, 20);
        sellButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){

                try{
                    user.sellInvestment(bond, Integer.parseInt(amount.getText()));
                    bondInformation.setText("Cash Available: £"+String.format("%.2f",user.getCashBalance())
                            + "\n" + "\n" + "Par Value: £" + bond.getParValue()
                            + "\n" + "Market Price: £" + bond.getMarketPrice()
                            +"\n" + "Bond Rating: " + bond.getBondRating()
                            + "\n" +"\n" + "Coupon Rate: "+ bond.getCouponRate() +"%"
                            + "\n" + "Coupon Frequency: " + bond.getCouponDate()
                            + "\n" + "Years To Maturity: " + bond.getYearsToMaturity() + " Years"
                            + "\n" + "\n"  + "Bonds bought: " + bond.getAmount());
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
