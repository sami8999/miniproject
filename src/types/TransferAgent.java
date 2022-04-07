package types;

import investmentAttributes.CurrencyAttributes;
import investmentTypes.Investment;
import investmentTypes.Bond;
import investmentTypes.CurrencyPair;
import investmentTypes.Stock;

import java.io.*;
import java.util.ArrayList;

public class TransferAgent {

    private static final TransferAgent instance = new TransferAgent();

    public static TransferAgent getInstance() {
        return instance;
    }

    private TransferAgent(){}

    public void syncInvestments(User user, Stock appleStock, Stock amazonStock, Stock microsoftStock,
                                CurrencyPair eurUsd, CurrencyPair gbpUsd, CurrencyPair audUsd, Bond treasuryBond,
                                Bond corporateBond, Bond mortgageBond){

        for(Investment investment: user.getInvestments()){

            switch (investment.getAttributes().getTicker()) {
                case "AAPL" -> appleStock.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "AMZN" -> amazonStock.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "MSFT" -> microsoftStock.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "EURUSD=X" -> eurUsd.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "GBPUSD=X" -> gbpUsd.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "AUDUSD=X" -> audUsd.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "US Treasury bond" -> treasuryBond.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "Vanguard Corporate bond" -> corporateBond.getAttributes().setAmount(investment.getAttributes().getAmount());
                case "Subprime mortgage bond" -> mortgageBond.getAttributes().setAmount(investment.getAttributes().getAmount());
            }

        }


    }


    public void buyInvestment(Investment investment, int amount, User user) throws TransactionsException {

        // exceptions
        double price = amount * investment.getAttributes().getMarketPrice();
        if (amount<=0) throw new TransactionsException("You cannot buy 0 or negative amounts");
        if (price>user.getCashBalance()) throw new TransactionsException("Cash available must cover a trades unit price");

        // dealing with leverage
        if (investment instanceof CurrencyPair){
            user.setCashBalance(user.getCashBalance() - price * ((CurrencyAttributes) investment.getAttributes()).getMargin());
            user.setLeveragedPosition(user.getLeveragedPosition() + price * (1- ((CurrencyAttributes) investment.getAttributes()).getMargin()));
        }
        else user.setCashBalance(user.getCashBalance()-price);

        // updating amount in investment and amount in accounts
        investment.buy(amount);
        int indexFound = findInvestment(investment.getAttributes().getTicker(), user);
        if (indexFound!=-1)
            user.getInvestments().set(indexFound, investment);
        else
            user.getInvestments().add(investment);
    }

    public void sellInvestment(Investment investment, int amount, User user) throws TransactionsException {

        double price = amount * investment.getAttributes().getMarketPrice();
        int compareAmount = investment.getAttributes().getAmount();
        int indexFound = findInvestment(investment.getAttributes().getTicker(), user);

        // exceptions
        if (amount<=0) throw new TransactionsException("You cannot sell 0 or negative amounts");
        if (amount>compareAmount) throw new TransactionsException("You do not own this many units");
        if (indexFound ==-1) throw new TransactionsException("You do not own this investment");

        // dealing with leverage
        if (investment instanceof CurrencyPair){
            double surplus = user.getLeveragedPosition()-price;
            // if negative we have paid back the leverage and increased cash position
            // if positive we have paid back some leverage but not increased our cash position
            user.setCashBalance(surplus<0 ? user.getCashBalance()-surplus : user.getCashBalance());
            user.setLeveragedPosition(surplus>0 ? user.getLeveragedPosition()-price : 0);
        }
        else user.setCashBalance(user.getCashBalance()+price);

        // updating amount in investment and amount in accounts
        investment.sell(amount);
        if (amount==compareAmount) user.getInvestments().remove(indexFound);
        else user.getInvestments().set(indexFound, investment);

    }

    public int findIndexOfUser(ArrayList<User> usersList, String username, String password ){

//        for (User user: usersList) if (user.getUsername().equals(username) & user.getPassword().equals(password)) return user;
//        return null;

        for (int i = 0; i<usersList.size(); i++){

            if(usersList.get(i).getUsername().equals(username) & usersList.get(i).getPassword().equals(password)){
                return i;
            }

        }
        return -1;



    }

    public int findInvestment(String ticker, User user){
        int indexFound = -1;
        for(int i=0; i<user.getInvestments().size(); i++){

            if (user.getInvestments().get(i).getAttributes().getTicker().equals(ticker)){
                indexFound = i;
            }
        }
        return indexFound;
    }

    public void writeToDatabase(ArrayList<User> listOfUsers) throws IOException {

        FileOutputStream fop=new FileOutputStream("database.txt"); // src/database.txt
        ObjectOutputStream oos=new ObjectOutputStream(fop);
        oos.writeObject(listOfUsers);
        oos.close();

    }

    //serialized list will only ever contain objects of type User,
    //so we can safely ignore unchecked cast of deserialized object warning
    @SuppressWarnings("unchecked")
    public ArrayList<User> readFromDatabase() throws IOException, ClassNotFoundException {

        try{
            File file= new File("database.txt"); // src/database.txt
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            ArrayList<User> listOfUsers = (ArrayList<User>) ois.readObject();
            ois.close();
            return listOfUsers;
        }
        catch(IOException e){return new ArrayList<User>();}


    }

    public void resetInvestments(Stock appleStock, Stock amazonStock, Stock microsoftStock,
                             CurrencyPair eurUsd, CurrencyPair gbpUsd, CurrencyPair audUsd, Bond treasuryBond,
                             Bond corporateBond, Bond mortgageBond){

    appleStock.getAttributes().setAmount(0);
    amazonStock.getAttributes().setAmount(0);
    microsoftStock.getAttributes().setAmount(0);
    eurUsd.getAttributes().setAmount(0);
    gbpUsd.getAttributes().setAmount(0);
    audUsd.getAttributes().setAmount(0);
    treasuryBond.getAttributes().setAmount(0);
    corporateBond.getAttributes().setAmount(0);
    mortgageBond.getAttributes().setAmount(0);

}

}

