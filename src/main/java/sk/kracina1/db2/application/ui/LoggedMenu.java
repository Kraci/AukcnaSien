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
        System.out.println("* 7. Bid on item                    *");
        System.out.println("* 8. Finish auction                 *");
        System.out.println("* 9. Log out                        *");
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
                case "7":   bidOnItem(); break;
                case "8":   finishAuctions(); break;
                case "9":   logout(); break;

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
        String name = Validation.getInstance().validate("Auction id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsAuctionRoom()));
        String itemId = Validation.getInstance().validate("Item id: ",
                Arrays.asList(new Required(), new IsInteger(), new IsInAuctionItem()));

        List<AuctionItem> l = AuctionItemFinder.getInstance().findByAuctionRoomId(Integer.parseInt(name));
        AuctionItem item = l.get(0);
        String newPrice = Validation.getInstance().validate("Your price: ",
                Arrays.asList(new Required(), new IsPositiveDouble(), new isPriceHigherThanPrevious(item),
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

            // vymazat prazdne aukcne siene? naaah. Toto zmaz potom rasto xd

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
}
