package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LoggedMenu extends Menu {

    @Override
    public void print() {
        System.out.println("*************************************");
        System.out.println("* 1. Add Item                       *");
        System.out.println("* 2. Add Item To Auction            *");
        System.out.println("* 3. Add funds                      *");
        System.out.println("* 4. Edit profile                   *");
		System.out.println("* 5. List all items in auction room *");
        System.out.println("* 6. List all active auction rooms  *");
        System.out.println("* 7. Show auction item details      *");
        System.out.println("* 8. Bid on item                    *");
        System.out.println("* 9. Finish auction                 *");
        System.out.println("* 10. Log out                       *");
        System.out.println("*************************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1":   addItem(); break;
                case "2":   addItemToAuction(); break;
                case "3":   addFunds(); break;
                case "4":   editProfile(); break;
                case "5":   listAllItemsInAuction(); break;
                case "6":   listOfAllAuctions(); break;
                case "7":   showAuctionItemDetails(); break;
                case "8":   bidOnItem(); break;
                case "9":   finishAuctions(); break;
                case "10":  logout(); break;

                default:    System.out.println("Unknown option"); break;
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
                Arrays.asList(new Required(), new IsPositiveDouble()));
        auctionItem.setStarting_price(Double.parseDouble(startingPrice));
        auctionItem.setPrice(Double.parseDouble(startingPrice));

        String bidPrice = Validation.getInstance().validate("Bid Price: ",
                Arrays.asList(new Required(), new IsPositiveDouble()));
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

    private void bidOnItem() throws SQLException {
        System.out.println("Auction item ID?");
        String name = Validation.getInstance().validate("id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsInAuctionItem()));

        AuctionItem item = AuctionItemFinder.getInstance().findById(Integer.parseInt(name));

        String newPrice = Validation.getInstance().validate("Your price: ",
                Arrays.asList(new Required(), new IsPositiveDouble(), new isPriceHigherThanPrevious(item),
                        new UserHasSufficentMoney(Login.getInstance().getUserID())));

        if(item.getHighest_bidder() != -1) {
            User userPrev = UserFinder.getInstance().findById(item.getHighest_bidder());
            double prevMoney = userPrev.getMoney();
            userPrev.setMoney(prevMoney + item.getPrice());
            userPrev.update();
        }

        User you = UserFinder.getInstance().findById(Login.getInstance().getUserID());
        double youMoney = you.getMoney();
        you.setMoney(youMoney - Double.parseDouble(newPrice));
        you.update();

        item.setPrice(Double.parseDouble(newPrice));
        item.setHighest_bidder(you.getId());
        item.update();

        System.out.println("OK!");
    }

    private void finishAuctions() throws SQLException {
        AuctionItemFinder aif = AuctionItemFinder.getInstance();
        List<AuctionItem> lai = aif.findFinished();
        if (lai.size() == 0) {
            System.out.println("No finished autction items found.");
            return;
        }
        for (AuctionItem auctionItem : lai) {
            System.out.println("Nasiel sa AuctionItem.");
            ItemFinder itemFinder = ItemFinder.getInstance();
            Item item = itemFinder.findById(auctionItem.getItem_id());
            UserFinder uf = UserFinder.getInstance();
            User owner = uf.findById(item.getUser_id());
            User bidder = uf.findById(auctionItem.getHighest_bidder());
            if (bidder != null) {
                // dat penaze povodnemu majitelovi
                owner.setMoney(owner.getMoney() + auctionItem.getPrice());

                // item.user_id prehodit na vyhercu
                item.setUser_id(bidder.getId());
            }

            // dat prec auctionItem
            auctionItem.delete();

            // vymazat prazdne aukcne siene? naaah. Toto zmaz potom rasto xD
            // TODO(miroslav.mrozek): veru nie

            // update vsetkoho v databaze
            item.update();
            owner.update();
            if (bidder != null)
                bidder.update();
        }
    }

    public void listAllItemsInAuction() throws SQLException {
        for (Category category : CategoryFinder.getInstance().findAll()) {
            CategoryPrinter.getInstance().print(category);
        }

        String category = Validation.getInstance().validate("Category id: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsInCategories()
        ));

        Integer category_id = Integer.parseInt(category);

        AuctionRoom auctionRoom = AuctionRoomFinder.getInstance().findByCategory(category_id);
        if (auctionRoom == null){
            System.out.println("Auction room does not exist!");
            return;
        }
        List<AuctionItem> auctionItems = AuctionItemFinder.getInstance().findByAuctionRoomId(auctionRoom.getId());

        for (AuctionItem auctionItem: auctionItems) {
            Item item = ItemFinder.getInstance().findById(auctionItem.getItem_id());
            AuctionItemPrinter.getInstance().printForRoom(auctionItem, item);
        }
    }

    public void listOfAllAuctions() throws SQLException {
        for (AuctionRoom auctionRoom: AuctionRoomFinder.getInstance().findRoomsWithItems()) {
            Category category = CategoryFinder.getInstance().findById(auctionRoom.getCategory_id());
            AuctionRoomPrinter.getInstance().print(auctionRoom, category);
        }
    }

    private void showAuctionItemDetails() throws SQLException {
        System.out.println("Auction item ID?");
        String name = Validation.getInstance().validate("id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsInAuctionItem()));

        AuctionItem ai = AuctionItemFinder.getInstance().findById(Integer.parseInt(name));
        Item item = ItemFinder.getInstance().findById(ai.getItem_id());
        AuctionItemPrinter.getInstance().printAll(ai,item);
    }

}
