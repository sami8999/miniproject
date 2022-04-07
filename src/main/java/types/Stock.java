package types;
import org.jfree.data.xy.DefaultHighLowDataset;
import yahoofinance.histquotes.HistoricalQuote;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Stock extends Investment {

    private int changePrice;

    public Stock(String ticker, Map<String, yahoofinance.Stock> stocks) throws IOException  {

        super(ticker);

        this.history = YahooFinanceAPI.getHistory(stocks, ticker);
        int size = this.history.size();
        this.marketPrice = this.history.get(size-30).getHigh().doubleValue();
        this.changePrice = 1;

    }

    @Override
    public List<HistoricalQuote> getHistory(){
        return this.history;
    }

    @Override
    public void changeMarketPrice() {
        this.changePrice +=1;
        if (this.changePrice >= 30){
            this.changePrice = 1;
        }
        this.marketPrice = this.history.get(this.history.size()-changePrice).getHigh().doubleValue();

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


}
