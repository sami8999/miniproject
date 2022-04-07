package types;

import org.jfree.data.xy.DefaultHighLowDataset;
import yahoofinance.histquotes.HistoricalQuote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

public class Bond extends Investment {

    private final double parValue;
    private final double couponRate;
    private final String couponDate;
    private final String bondRating;
    private final int yearsToMaturity;

    public Bond(String investmentName,double parValue, double couponRate, String couponDate,
                  String bondRating, int yearsToMaturity, double marketPrice) {
        super(investmentName);
        this.parValue = parValue;
        this.couponRate = couponRate;
        this.couponDate = couponDate;
        this.bondRating = bondRating;
        this.yearsToMaturity = yearsToMaturity;
        this.marketPrice = marketPrice;
        this.history = getHistory();
    }

    @Override
    public void changeMarketPrice() {
        this.marketPrice += Math.random() >= 0.5 ? Math.random()*50 : -Math.random()*50;
        this.marketPrice = (double) round(this.marketPrice *100)/100;
    }

    @Override
    public void buy(int number) {
        this.amount += number;
        changeMarketPrice();
    }

    @Override
    public void sell(int number) {
        this.amount -= number;
        changeMarketPrice();
    }

    @Override
    public List<HistoricalQuote> getHistory() {
        int days = 728;
        List<HistoricalQuote> history = new ArrayList<>();
        for (int i=0; i<days; i++){
            Calendar calendarDay = Calendar.getInstance();
            calendarDay.add(Calendar.DATE,-days+i);

            // high must be larger then low
            double high = (Math.random() >= 0.5 ? Math.random()*50 : -Math.random()*50);
            double low = high - Math.random() * 25;

            HistoricalQuote day = new HistoricalQuote(this.ticker, calendarDay,
                    new BigDecimal(this.marketPrice + (Math.random() >= 0.5 ? Math.random()*50 : -Math.random()*50)), //open
                    new BigDecimal(this.marketPrice + low),  // low
                    new BigDecimal(this.marketPrice + high) ,  //high
                    new BigDecimal(this.marketPrice + (Math.random() >= 0.5 ? Math.random()*50 : -Math.random()*50)), //close
                    new BigDecimal(this.marketPrice+ Math.random() * 50), //adjust close
                    (long) (10+Math.random()*100) //volume
            );
            history.add(day);
        }
        return history;
    }

    public double getParValue(){
        return this.parValue;
    }

    public double getCouponRate(){
        return this.couponRate;
    }

    public String getCouponDate(){
        return this.couponDate;
    }

    public String getBondRating(){
        return this.bondRating;
    }

    public int getYearsToMaturity(){
        return this.yearsToMaturity;
    }
}
