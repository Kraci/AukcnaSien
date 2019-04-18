package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LoggedMenu extends Menu {

    @Override
    public void print() {
        System.out.println("***********************************");
        System.out.println("* 1. Add Item                     *");
        System.out.println("* 2. Add Item To Auction          *");
        System.out.println("* 3. Log out                      *");
        System.out.println("***********************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1":   addItem(); break;
                case "2":   addItemToAuction(); break;
                case "3":   logout(); break;

                default:    System.out.println("Unknown option"); break;
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void logout() {
        Login.getInstance().logout();
        exit();
    }

    private void addItem() throws SQLException {
        Item item = new Item();

        // TODO auth user
        item.setUser_id(Login.getInstance().getUserID());

        System.out.println("Add new Item.");
        String name = Validation.getInstance().validate("Name: ", Arrays.asList(
                new Required()
        ));
        item.setName(name);
        String count = Validation.getInstance().validate("Count: ", Arrays.asList(
                new Required(),
                new IsInteger()
        ));
        item.setCount(Integer.parseInt(count));

        String description = Validation.getInstance().validate("Description: ", Arrays.asList(
                new Required()
        ));
        System.out.println();
        item.setDescription(description);

        for (Category category : CategoryFinder.getInstance().findAll()) {
            CategoryPrinter.getInstance().print(category);
        }
        String category = Validation.getInstance().validate("Category id: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsInCategories()
        ));
        item.setCategory_id(CategoryFinder.getInstance().findById(Integer.parseInt(category)).getId());

        item.insert();
        System.out.println();
        ItemPrinter.getInstance().print(item);
    }

    public void addItemToAuction() throws SQLException {
        AuctionItem auctionItem = new AuctionItem();

        List<Item> items = ItemFinder.getInstance().findFreeByUserId(Login.getInstance().getUserID());

        for (Item item: items) {
            ItemPrinter.getInstance().print(item);
        }

        String itemId = Validation.getInstance().validate("Item id: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsInItems()
        ));
        auctionItem.setItem_id(Integer.parseInt(itemId));

        String startingPrice = Validation.getInstance().validate("Starting Price: ", Arrays.asList(
                new Required(),
                new IsDouble()
        ));
        auctionItem.setStarting_price(Double.parseDouble(startingPrice));

        String bidPrice = Validation.getInstance().validate("Bid Price: ", Arrays.asList(
                new Required(),
                new IsDouble()
        ));
        auctionItem.setMin_bid_price(Double.parseDouble(bidPrice));

        String endDate = Validation.getInstance().validate("End Date: ", Arrays.asList(
                new Required(),
                new IsDate(),
                new IsAfter(1)
        ));
        auctionItem.setEnd_date(Date.valueOf(endDate));

        Integer categoryId = ItemFinder.getInstance().findById(auctionItem.getItem_id()).getCategory_id();

        AuctionRoom auctionRoom = AuctionRoomFinder.getInstance().findByCategory(categoryId);

        if (auctionRoom == null){
            auctionRoom = new AuctionRoom();
            auctionRoom.setCategory_id(categoryId);
            auctionRoom.insert();
        }

        auctionItem.setRoom_id(auctionRoom.getId());

        auctionItem.insert();
        System.out.println("Item was successfully assigned to auction room.");
        System.out.println();
    }

}
