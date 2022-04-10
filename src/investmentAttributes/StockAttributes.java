package investmentAttributes;

import types.YahooFinanceAPI;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.Map;

public class StockAttributes extends InvestmentAttributes {
    private int changePrice;
    public StockAttributes(String investmentName, Map<String, Stock> stocks) throws IOException {
        super(investmentName);
        setHistory(YahooFinanceAPI.getHistory(stocks, investmentName));
        int size = getHistory().size();
        setMarketPrice(getHistory().get(size-30).getHigh().doubleValue());
        this.changePrice = 1;
    }
    public int getChangePrice() {return this.changePrice;}
    public void setChangePrice(int changePrice){
        this.changePrice = changePrice;
    }
}

