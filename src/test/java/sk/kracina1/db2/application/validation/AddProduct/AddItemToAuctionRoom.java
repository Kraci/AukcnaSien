package sk.kracina1.db2.application.validation.AddProduct;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemToAuctionRoom {

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<AuctionItem> auctionItems= new ArrayList<>();
    ArrayList<AuctionRoom> auctionRooms = new ArrayList<>();

    User findUserByMail(String mail) {
        List<User> response = users.stream().filter(user -> user.getMail().equals(mail)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    Category findCategoryById(int id) {
        List<Category> response = categories.stream().filter(category -> category.getId().equals(id)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    Item findItemById(int id) {
        List<Item> response = items.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    AuctionRoom findAuctionRoomByCategoryId(int id) {
        List<AuctionRoom> response = auctionRooms.stream().filter(auctionRoom -> auctionRoom.getCategory_id().equals(id)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    AuctionItem findAuctionItemById(int id) {
        List<AuctionItem> response = auctionItems.stream().filter(auctionItem -> auctionItem.getId().equals(id)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    private Required required;
    private IsInteger isInteger;
    private IsDouble isDouble;
    private IsDate isDate;
    private IsInAuctionItem isInAuctionItem;
    private IsAfter isAfter;
    private Validation validation;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        required = new Required();
        isInteger = new IsInteger();
        isDouble = new IsDouble();
        isDate = new IsDate();
        isAfter = new IsAfter(1);
        validation = Validation.getInstance();

        for (int i = 1; i <= 10; i++) {
            User a = new User();
            String mail = "user" + String.valueOf(i) + "@gmail.com";
            a.setId(i);
            a.setMail(mail);
            users.add(a);
        }

        for (int i = 1; i <= 10; i++) {
            Category a = new Category();
            a.setId(i);
            String name = "category " + String.valueOf(i);
            a.setName(name);
            categories.add(a);
        }

        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setId(i);
            item.setCount(1);
            item.setUser_id(i);
            item.setCategory_id(i);
            item.setDescription("skvele");
            item.setName("item "+String.valueOf(i));
            items.add(item);
        }
    }

    @org.junit.jupiter.api.Test
    void successInsert() throws SQLException {
        AuctionItem auctionItem = new AuctionItem();
        Item item = findItemById(1);

        AuctionRoom auctionRoom = findAuctionRoomByCategoryId(item.getCategory_id());

        if (auctionRoom == null) {
            auctionRoom = new AuctionRoom();
            auctionRoom.setCategory_id(item.getCategory_id());
            auctionRooms.add(auctionRoom);
        }
        auctionItem.setId(auctionItems.size()+1);
        auctionItem.setRoom_id(auctionRoom.getId());
        auctionItem.setItem_id(item.getId());
        auctionItem.setStarting_price(1000.0);
        auctionItem.setMin_bid_price(20.0);
        auctionItem.setEnd_date(Date.valueOf("2020-10-10"));
        auctionItems.add(auctionItem);

        AuctionItem insertedAuctionItem = findAuctionItemById(auctionItem.getId());

        assertNotNull(insertedAuctionItem);
    }

    @org.junit.jupiter.api.Test
    void successManyInsert() throws SQLException {
        int count = auctionItems.size();
        for (int i = 1; i <= 10; i++) {
            AuctionItem auctionItem = new AuctionItem();
            Item item = findItemById(i);

            AuctionRoom auctionRoom = findAuctionRoomByCategoryId(item.getCategory_id());

            if (auctionRoom == null) {
                auctionRoom = new AuctionRoom();
                auctionRoom.setCategory_id(item.getCategory_id());
                auctionRooms.add(auctionRoom);
            }
            auctionItem.setId(auctionItems.size()+1);
            auctionItem.setRoom_id(auctionRoom.getId());
            auctionItem.setItem_id(item.getId());
            auctionItem.setStarting_price(1000.0);
            auctionItem.setMin_bid_price(20.0);
            auctionItem.setEnd_date(Date.valueOf("2020-10-10"));
            auctionItems.add(auctionItem);

            AuctionItem insertedAuctionItem = findAuctionItemById(auctionItem.getId());

            assertNotNull(insertedAuctionItem);
        }

        assertEquals(auctionItems.size(), count + 10);
    }

}
