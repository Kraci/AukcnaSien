package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class LoggedMenu extends Menu {

    @Override
    public void print() {
        System.out.println("***********************************");
        System.out.println("* 1. Add Item                     *");
        System.out.println("* 2. Add Item To Auction          *");
        System.out.println("* 3. Add funds                    *");
        System.out.println("* 4. Edit profile                 *");
        System.out.println("* 5. Show items                   *");
        System.out.println("* 6. Bid on item                  *");
        System.out.println("* 7. Log out                      *");
        System.out.println("***********************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
            case "1":
                addItem();
                break;
            case "2":
                addItemToAuction();
                break;
            case "3":
                addFunds();
                break;
            case "4":
                editProfile();
                break;
            case "5":
                showItemsInAuctionRoom();
                break;
            case "6":
                bidOnItem();
                break;
            case "7":
                logout();
                break;

            default:
                System.out.println("Unknown option");
                break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void logout() {
        Login.getInstance().logout();
        exit();
    }

    private void addFunds() throws SQLException {
        User loggedUser = UserFinder.getInstance().findById(Login.getInstance().getUserID());
        String money = Validation.getInstance().validate("Add funds: ",
                Arrays.asList(new IsDouble(), new IsPositiveDouble()));
        loggedUser.setMoney(loggedUser.getMoney() + Double.parseDouble(money));
        loggedUser.update();
    }

    private void editProfile() throws IOException, SQLException {
        User loggedUser = UserFinder.getInstance().findById(Login.getInstance().getUserID());
        UserPrinter.getInstance().print(loggedUser);
        EditProfileMenu editMenu = new EditProfileMenu();
        editMenu.setEditedUser(loggedUser);
        editMenu.run();
    }

    private void addItem() throws SQLException {
        Item item = new Item();

        // TODO auth user
        item.setUser_id(Login.getInstance().getUserID());

        System.out.println("Add new Item.");
        String name = Validation.getInstance().validate("Name: ", Arrays.asList(new Required()));
        item.setName(name);
        String count = Validation.getInstance().validate("Count: ", Arrays.asList(new Required(), new IsInteger()));
        item.setCount(Integer.parseInt(count));

        String description = Validation.getInstance().validate("Description: ", Arrays.asList(new Required()));
        System.out.println();
        item.setDescription(description);

        for (Category category : CategoryFinder.getInstance().findAll()) {
            CategoryPrinter.getInstance().print(category);
        }
        String category = Validation.getInstance().validate("Category id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsInCategories()));
        item.setCategory_id(CategoryFinder.getInstance().findById(Integer.parseInt(category)).getId());

        item.insert();
        System.out.println();
        ItemPrinter.getInstance().print(item);
    }

    public void addItemToAuction() throws SQLException {
        AuctionItem auctionItem = new AuctionItem();

        List<Item> items = ItemFinder.getInstance().findFreeByUserId(Login.getInstance().getUserID());

        for (Item item : items) {
            ItemPrinter.getInstance().print(item);
        }

        String itemId = Validation.getInstance().validate("Item id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsInItems()));
        auctionItem.setItem_id(Integer.parseInt(itemId));

        String startingPrice = Validation.getInstance().validate("Starting Price: ",
                Arrays.asList(new Required(), new IsDouble()));
        auctionItem.setStarting_price(Double.parseDouble(startingPrice));
        auctionItem.setPrice(Double.parseDouble(startingPrice));

        String bidPrice = Validation.getInstance().validate("Bid Price: ",
                Arrays.asList(new Required(), new IsDouble()));
        auctionItem.setMin_bid_price(Double.parseDouble(bidPrice));

        String endDate = Validation.getInstance().validate("End Date: ",
                Arrays.asList(new Required(), new IsDate(), new IsAfter(1)));
        auctionItem.setEnd_date(Date.valueOf(endDate));

        Integer categoryId = ItemFinder.getInstance().findById(auctionItem.getItem_id()).getCategory_id();

        AuctionRoom auctionRoom = AuctionRoomFinder.getInstance().findByCategory(categoryId);

        if (auctionRoom == null) {
            auctionRoom = new AuctionRoom();
            auctionRoom.setCategory_id(categoryId);
            auctionRoom.insert();
        }

        auctionItem.setRoom_id(auctionRoom.getId());

        auctionItem.setHighest_bidder(-1);
        auctionItem.insert();
        System.out.println("Item was successfully assigned to auction room.");
        System.out.println();
    }

    private void showItemsInAuctionRoom() throws SQLException {
        System.out.println("Which auction room?");
        String name = Validation.getInstance().validate("id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsAuctionRoom()));

        List<AuctionItem> l = AuctionItemFinder.getInstance().findByAuctionId(Integer.parseInt(name));
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss.SSS");
        for (AuctionItem a : l) {

            Item item = ItemFinder.getInstance().findById(a.getItem_id());
            String dateFormatted = formatter.format(a.getEnd_date());
            System.out.println("name: " + item.getName());
            System.out.println("description: " + item.getDescription());
            System.out.println(String.format("|%-10s|%-18s|%-18s|%-18s|%-18s", "Id", "starting price", "minimal bid",
                    "current price", "end date"));
            System.out.println(String.format("|%-10d|%-18f|%-18f|%-18f|%-18s \n", a.getId(), a.getStarting_price(),
                    a.getStarting_price(), a.getMin_bid_price(), dateFormatted));
        }

    }

    private void bidOnItem() throws SQLException {
        String name = Validation.getInstance().validate("Auction id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsAuctionRoom()));
        String itemId = Validation.getInstance().validate("Item id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsInAuctionItem()));

        List<AuctionItem> l = AuctionItemFinder.getInstance().findByAuctionId(Integer.parseInt(name));
        AuctionItem item = l.get(0);
        String newPrice = Validation.getInstance().validate("Your price: ",
                Arrays.asList(new Required(), new IsInteger(), new isPriceHigherThanPrevious(item),
                        new UserHasSufficentMoney(Login.getInstance().getUserID())));

        User you = UserFinder.getInstance().findById(Login.getInstance().getUserID());
        User userPrev = UserFinder.getInstance().findById(Login.getInstance().getUserID());

        double prevMoney = userPrev.getMoney();
        double youMoney = you.getMoney();

        // remove this user money
        // refund previous top bidder money
        you.setMoney(youMoney - Integer.parseInt(newPrice));
        userPrev.setMoney(prevMoney + item.getPrice());

        // set item to owner
        item.setPrice(Double.parseDouble(newPrice));
        item.setHighest_bidder(you.getId());

        item.update();
        you.update();
        userPrev.update();

        System.out.println("OK!");
    }
}
