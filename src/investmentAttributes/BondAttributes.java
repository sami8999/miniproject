package investmentAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BondAttributes extends InvestmentAttributes {

    private final double parValue;
    private final double couponRate;
    private final String couponDate;
    private final String bondRating;
    private final int yearsToMaturity;

    public BondAttributes(String investmentName,double parValue, double couponRate, String couponDate,
                          String bondRating, int yearsToMaturity, double marketPrice) {
        super(investmentName);
        this.parValue = parValue;
        this.couponRate = couponRate;
        this.couponDate = couponDate;
        this.bondRating = bondRating;
        this.yearsToMaturity = yearsToMaturity;
        setHistory(createHistory());
        setMarketPrice(marketPrice);
    }

    public List<HistoricalData> createHistory() {
        int days = 728;
        List<HistoricalData> history = new ArrayList<>();
        for (int i=0; i<days; i++){
            Calendar calendarDay = Calendar.getInstance();
            calendarDay.add(Calendar.DATE,-days+i);

            // high must be larger then low
            double high = (Math.random() >= 0.5 ? Math.random()*50 : -Math.random()*50);
            double low = high - Math.random() * 25;

            HistoricalData day = new HistoricalData(getTicker(), calendarDay,
                    BigDecimal.valueOf(getMarketPrice() + ((Math.random() >= 0.5 ? Math.random() * 50 : -Math.random() * 50))), //open
                    BigDecimal.valueOf(getMarketPrice() + low),  // low
                    BigDecimal.valueOf(getMarketPrice() + high) ,  //high
                    BigDecimal.valueOf(getMarketPrice() + (Math.random() >= 0.5 ? Math.random() * 50 : -Math.random() * 50)), //close
                    BigDecimal.valueOf(getMarketPrice() + (Math.random() * 50)), //adjust close
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
