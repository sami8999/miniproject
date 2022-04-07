package InvestmentTypes;

import InvestmentAttributes.CurrencyAttributes;
import InvestmentAttributes.InvestmentAttributes;
import yahoofinance.quotes.fx.FxQuote;

import java.io.Serializable;
import java.util.Map;

public class CurrencyPair implements Investment, Serializable {

    private final CurrencyAttributes data;

    public CurrencyPair(String investmentName, Map<String, FxQuote> currencies, double margin) {
        this.data = new CurrencyAttributes(investmentName, currencies, margin);
    }

    @Override
    public void changeMarketPrice() {
        data.setMarketPrice(data.getMarketPrice() + (Math.random() >= 0.5 ? Math.random()*0.1 : -Math.random()*0.1));
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
