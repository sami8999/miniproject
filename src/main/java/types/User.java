package types;

import java.util.ArrayList;

import static java.lang.Math.round;

public class User {

    private final String username;
    private final double STARTING_BALANCE = 100000;
    private double cashBalance;
    private final ArrayList<Investment> investments;
    private double onMargin;


    public User(String username){
        this.username = username;
        this.cashBalance = STARTING_BALANCE;
        this.investments = new ArrayList<>();
        this.onMargin = 0;
    }

    public String getUsername(){
        return this.username;
    }

    public double getCashBalance(){
        return this.cashBalance;
    }

    public int findInvestment(String ticker){
        int indexFound = -1;
        for(int i=0; i<investments.size(); i++){

            if (investments.get(i).getTicker().equals(ticker)){
                indexFound = i;
            }
        }
        return indexFound;
    }

    public void buyInvestment(Investment investment, int amount) throws TransactionsException {

        if (amount<=0 ){throw new TransactionsException("You cannot buy 0 or negative amounts");}
        double price = amount * investment.getMarketPrice();
        int indexFound = findInvestment(investment.getTicker());

        if (price<=this.cashBalance){

            // dealing with updating cash values
            if (investment instanceof Currency){
                this.cashBalance -= amount * investment.getMarketPrice() * ((Currency) investment).getMargin();
                this.onMargin+= amount * investment.getMarketPrice() * (1-((Currency) investment).getMargin());
            }
            else{this.cashBalance -= price;}

            // checking if user owns investment yet
            investment.buy(amount);
            if (indexFound!=-1){investments.set(indexFound, investment);}
            else{investments.add(investment);}



        }
        else{
            if (investment instanceof Currency){
                throw new TransactionsException("Cash available must cover a trades unit price");
            }
            throw new TransactionsException("You do not have enough cash");
        }


    }

    public void sellInvestment(Investment investment, int amount) throws TransactionsException {

        if (amount<=0 ){
            throw new TransactionsException("You cannot sell 0 or negative amounts");
        }

        double price = amount * investment.getMarketPrice();
        int indexFound = findInvestment(investment.getTicker());

        if (indexFound!=-1){

            if (amount>investment.getAmount()){
                System.out.println("Invalid amount entered");
            }
            else{
                if (investment instanceof Currency){
                    double totalValue = investment.getMarketPrice() * amount;
                    if (totalValue <= this.onMargin){
                        this.onMargin -= totalValue;
                    }
                    else{
                        double surplus = totalValue - this.onMargin;
                        this.onMargin = 0.0;
                        this.cashBalance += surplus;
                    }
                }
                else {this.cashBalance += price;}

                if (amount==investment.getAmount()){
                    investments.remove(indexFound);
                    investment.sell(amount);
                }
                else if (amount<investment.getAmount()){
                    investment.sell(amount);
                    investments.set(indexFound, investment);
                }

            }

        }
        else{throw new TransactionsException("You do not own this investment");}

    }

    public double calculateTotalPortfolioValue() {
        double investmentValue = investments.stream()
                .mapToDouble(investment -> investment.getAmount() *investment.getMarketPrice())
                .sum();

        return investmentValue + this.cashBalance - this.onMargin;
    }

    public double calculateTotalReturn(){

        double total = (calculateTotalPortfolioValue() - STARTING_BALANCE) /STARTING_BALANCE * 100;
        total = round(total*100.0)/100.0;
        return total;

    }

    public double getLeveragedPosition(){
        return this.onMargin;
    }

    public ArrayList<Investment> getInvestments(){
        return this.investments;
    }
}

// if want to save userdata will need to revamp the buy and sell investment function to carry over data