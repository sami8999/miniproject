package Types;
import InvestmentTypes.Investment;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private final String username;
    private final String password;
    private final double STARTING_BALANCE = 100000;
    private double cashBalance;
    private final ArrayList<Investment> investments;
    private double onMargin;


    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.cashBalance = STARTING_BALANCE;
        this.investments = new ArrayList<>();
        this.onMargin = 0;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){return this.password;}

    public double getCashBalance(){
        return this.cashBalance;
    }
    public void setCashBalance(double cashBalance){
        this.cashBalance = cashBalance;
    }

    public ArrayList<Investment> getInvestments(){
        return this.investments;
    }

    public double getLeveragedPosition(){
        return this.onMargin;
    }
    public void setLeveragedPosition(double onMargin){
        this.onMargin = onMargin;
    }

    public double calculateTotalPortfolioValue() {
        return investments.stream()
                .mapToDouble(investment -> investment.getAttributes().getAmount() * investment.getAttributes().getMarketPrice())
                .sum()  + this.cashBalance - this.onMargin;
    }

    public double calculateTotalReturn(){

        return (calculateTotalPortfolioValue() - STARTING_BALANCE) /STARTING_BALANCE * 100;

    }




}
