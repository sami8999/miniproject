package InvestmentTypes;
import InvestmentAttributes.InvestmentAttributes;
import InvestmentAttributes.StockAttributes;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class Stock implements Investment, Serializable {

    private final StockAttributes data;

    public Stock(String investmentName, Map<String, yahoofinance.Stock> stocks) throws IOException {
        this.data = new StockAttributes(investmentName, stocks);

    }

    @Override
    public void changeMarketPrice() {
        data.setChangePrice(data.getChangePrice() +1);
        if (data.getChangePrice() >= 30){
            data.setChangePrice(1);
        }
        data.setMarketPrice(data.getHistory().get(data.getHistory().size()-data.getChangePrice()).getHigh().doubleValue());

    }

    @Override
    public void buy(int number) {
        data.setAmount(data.getAmount()+number);
        changeMarketPrice();
    }

    @Override
    public void sell(int number) {
        data.setAmount(data.getAmount()-number);
        changeMarketPrice();
    }

    @Override
    public InvestmentAttributes getAttributes() {
        return this.data;
    }

}
