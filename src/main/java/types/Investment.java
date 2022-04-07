package types;

import yahoofinance.histquotes.HistoricalQuote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Investment {

    String ticker;
    Date dateOfPurchase;
    int amount;
    double marketPrice;
    List<HistoricalQuote> history;

    public Investment(String investmentName){

        this.ticker = investmentName;
        this.dateOfPurchase = new Date();
        this.amount = 0;


    }

    public abstract void changeMarketPrice();
    public abstract void buy(int number);
    public abstract void sell(int number);
    public abstract List<HistoricalQuote> getHistory();

    public String getTicker(){
        return this.ticker;
    }

    public String getDateOfPurchase(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(this.dateOfPurchase);
    }

    public int getAmount(){
        return this.amount;
    }

    public double getMarketPrice(){
        return this.marketPrice;
    }
}
