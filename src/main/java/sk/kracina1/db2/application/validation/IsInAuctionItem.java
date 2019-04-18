package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.AuctionItem;
import sk.kracina1.db2.application.rdg.AuctionItemFinder;

import java.sql.SQLException;

public class IsInAuctionItem implements Rule {

    private static final String errorMessage = "Auction item does not exist.";

    @Override
    public boolean passes(String value) {
        try {
            return AuctionItemFinder.getInstance().findById(Integer.parseInt(value)) != null;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String message() {
        return null;
    }
}
