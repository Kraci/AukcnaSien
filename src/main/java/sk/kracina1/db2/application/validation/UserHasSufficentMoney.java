package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.AuctionItem;
import sk.kracina1.db2.application.rdg.UserFinder;

import java.sql.SQLException;

public class UserHasSufficentMoney implements Rule {

  private static final String errorMessage = "Bid is not high enough.";

  private int id;

  public UserHasSufficentMoney(int userId) {
    this.id = userId;
  }

  @Override
  public boolean passes(String price) {
    try {
      return UserFinder.getInstance().findById(this.id).getMoney() >= Integer.parseInt(price);
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public String message() {
    return errorMessage;
  }
}
