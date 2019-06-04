package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.AuctionItem;

public class isPriceHigherThanPrevious implements Rule {

    private static final String errorMessage = "Bid is not high enough.";

    private AuctionItem ai;

    public isPriceHigherThanPrevious(AuctionItem abi) {
        this.ai = abi;
    }

    @Override
    public boolean passes(String price) {
        if (ai.getMin_bid_price() == 0) {
            return Double.parseDouble(price) > (ai.getPrice() + ai.getMin_bid_price());
        }
        return Double.parseDouble(price) >= (ai.getPrice() + ai.getMin_bid_price());
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
