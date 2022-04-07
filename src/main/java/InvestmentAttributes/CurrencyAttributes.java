package InvestmentAttributes;

import yahoofinance.quotes.fx.FxQuote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CurrencyAttributes extends InvestmentAttributes implements Serializable {

    private final double margin;

    public CurrencyAttributes(String investmentName, Map<String, FxQuote> currencies, double margin) {
        super(investmentName);
        this.margin = margin;
        setMarketPrice(currencies.get(investmentName).getPrice().doubleValue());
        setHistory(createHistory());
    }

    public List<HistoricalData> createHistory(){

        int days = 728;
        List<HistoricalData> history = new ArrayList<>();
        for (int i=0; i<days; i++){
            Calendar calendarDay = Calendar.getInstance();
            calendarDay.add(Calendar.DATE,-days+i);

            // high must be larger then low
            double high = (Math.random() >= 0.5 ? Math.random()*0.1 : -Math.random()*0.1);
            double low = high - Math.random() * 0.05;

            HistoricalData day = new HistoricalData(getTicker(), calendarDay,
                    BigDecimal.valueOf(getMarketPrice() + (Math.random() >= 0.5 ? Math.random() * 0.1 : -Math.random() * 0.1)), //open
                    BigDecimal.valueOf(getMarketPrice() + low),  // low
                    BigDecimal.valueOf(getMarketPrice() + high) ,  //high
                    BigDecimal.valueOf(getMarketPrice() + (Math.random() >= 0.5 ? Math.random() * 0.1 : -Math.random() * 0.1)), //close
                    BigDecimal.valueOf(getMarketPrice() + Math.random() * 0.1), //adjust close
                    (long) (10+Math.random()*100) //volume
            );
            history.add(day);
        }
        return history;

    }

    public double getMargin(){
        return this.margin;
    }


}
