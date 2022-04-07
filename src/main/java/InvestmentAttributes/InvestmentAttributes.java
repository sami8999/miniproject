package InvestmentAttributes;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class InvestmentAttributes implements Serializable {

    private final String ticker;
    private final Date dateOfPurchase;
    private int amount;
    private double marketPrice;
    private List<HistoricalData> history;

    public InvestmentAttributes(String investmentName){

        this.ticker = investmentName;
        this.dateOfPurchase = new Date();
        this.amount = 0;

    }

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
    public void setAmount(int amount){
        this.amount = amount;

    }
    public double getMarketPrice(){
        return this.marketPrice;
    }
    public void setMarketPrice(double marketPrice){
        this.marketPrice = marketPrice;
    }
    public List<HistoricalData> getHistory(){return this.history;}
    public void setHistory(List<HistoricalData> history){
        this.history = history;

    }

}
