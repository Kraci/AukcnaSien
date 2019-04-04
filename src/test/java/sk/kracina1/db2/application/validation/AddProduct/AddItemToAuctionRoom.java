package sk.kracina1.db2.application.validation.AddProduct;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemToAuctionRoom {

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
        isInAuctionItem = new IsInAuctionItem();
        isAfter = new IsAfter(1);
        validation = Validation.getInstance();
    }

//    @org.junit.jupiter.api.Test
//    void successInsert() throws SQLException {
//        AuctionItem auctionItem = new AuctionItem();
//        Item item = new Item();
//
//        User user = new User();
//        user.setAge(20);
//        user.setFirstName("Matej");
//        user.setSurname("Rychtarik");
//        user.setMail("test@test.sk");
//        user.setUsername("matej123456");
//        user.setPassword("ahoj1234AHOJ");
//        user.insert();
//
//        item.setUser_id(user.getId());
//        item.setDescription("This is top item.");
//        item.setName("The best item");
//
//        Category category = new Category();
//        category.setName("category 1");
//        category.insert();
//
//        item.setCategory_id(category.getId());
//
//        item.insert();
//
//        AuctionRoom auctionRoom = AuctionRoomFinder.getInstance().findByCategory(category.getId());
//
//        if (auctionRoom == null) {
//            auctionRoom = new AuctionRoom();
//            auctionRoom.setCategory_id(category.getId());
//            auctionRoom.insert();
//        }
//
//        auctionItem.setRoom_id(auctionRoom.getId());
//        auctionItem.setItem_id(item.getId());
//        auctionItem.setStarting_price(1000.0);
//        auctionItem.setMin_bid_price(20.0);
//        auctionItem.setEnd_date(Date.valueOf("2020-10-10"));
//        auctionItem.insert();
//
//        assertEquals(validation.check(""+auctionItem.getId(), Arrays.asList(isInAuctionItem)), "");
//    }

}
