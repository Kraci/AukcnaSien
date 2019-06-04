package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.UserFinder;

import java.sql.SQLException;

public class UserHasSufficentMoney implements Rule {

  private static final String errorMessage = "You dont have enough money.";
  private int id;

  public UserHasSufficentMoney(int userId) {
    this.id = userId;
  }

  protected double money() throws SQLException {
    return UserFinder.getInstance().findById(this.id).getMoney();
  }

  @Override
  public boolean passes(String price) {
    try {
      return money() >= Integer.parseInt(price);
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public String message() {
    return errorMessage;
  }
}
