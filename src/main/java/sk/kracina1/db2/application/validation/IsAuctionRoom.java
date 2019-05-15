package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.AuctionRoomFinder;

import java.sql.SQLException;

public class IsAuctionRoom implements Rule {

    private static final String errorMessage = "Auction room does not exist.";

    @Override
    public boolean passes(String value) {
        try {
            return AuctionRoomFinder.getInstance().findById(Integer.parseInt(value)) != null;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
