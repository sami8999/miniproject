package InvestmentTypes;

import InvestmentAttributes.BondAttributes;
import InvestmentAttributes.InvestmentAttributes;

import java.io.Serializable;

public class Bond implements Investment, Serializable {

    private final BondAttributes data;

    public Bond(String investmentName, double parValue, double couponRate, String couponDate,
                String bondRating, int yearsToMaturity, double marketPrice) {
        this.data = new BondAttributes(investmentName, parValue, couponRate, couponDate,
                bondRating, yearsToMaturity, marketPrice);
    }

    @Override
    public void changeMarketPrice() {
        data.setMarketPrice(data.getMarketPrice() + (Math.random() >= 0.5 ? Math.random()*50 : -Math.random()*50));
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
