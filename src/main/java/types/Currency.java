package types;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static java.lang.Math.round;

public class Currency extends Investment{

    private final double margin;

    public Currency(String ticker, Map<String, FxQuote> currencies, double margin) throws IOException {
        super(ticker);
        this.margin = margin;
        this.marketPrice = currencies.get(ticker).getPrice().doubleValue();
        this.history = getHistory();
    }

    public double getMargin(){
        return this.margin;
    }

    @Override
    public List<HistoricalQuote> getHistory() {
        int days = 728;
        List<HistoricalQuote> history = new ArrayList<>();
        for (int i=0; i<days; i++){
            Calendar calendarDay = Calendar.getInstance();
            calendarDay.add(Calendar.DATE,-days+i);

            // high must be larger then low
            double high = (Math.random() >= 0.5 ? Math.random()*0.1 : -Math.random()*0.1);
            double low = high - Math.random() * 0.05;

            HistoricalQuote day = new HistoricalQuote(this.ticker, calendarDay,
                    new BigDecimal(this.marketPrice + (Math.random() >= 0.5 ? Math.random()*0.1 : -Math.random()*0.1)), //open
                    new BigDecimal(this.marketPrice + low),  // low
                    new BigDecimal(this.marketPrice + high) ,  //high
                    new BigDecimal(this.marketPrice + (Math.random() >= 0.5 ? Math.random()*0.1 : -Math.random()*0.1)), //close
                    new BigDecimal(this.marketPrice+ Math.random() * 0.1), //adjust close
                    (long) (10+Math.random()*100) //volume
            );
            history.add(day);
        }
        return history;
    }

    @Override
    public void changeMarketPrice() {
        this.marketPrice += Math.random() >= 0.5 ? Math.random()*0.1 : -Math.random()*0.1;
        this.marketPrice = (double) round(this.marketPrice *100)/100;
    }

    @Override
    public void buy(int number) { //100,000
        this.amount += number;
        changeMarketPrice();
    }

    @Override
    public void sell(int number) {
        this.amount -= number;
        changeMarketPrice();
    }
}
