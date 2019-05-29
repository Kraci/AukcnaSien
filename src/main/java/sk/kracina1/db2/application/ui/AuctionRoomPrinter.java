package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.AuctionRoom;
import sk.kracina1.db2.application.rdg.Category;

public class AuctionRoomPrinter {
    private static final AuctionRoomPrinter INSTANCE = new AuctionRoomPrinter();

    public static AuctionRoomPrinter getInstance() { return INSTANCE; }

    private AuctionRoomPrinter() {}

    public void print(AuctionRoom auctionRoom, Category category) {
        if (auctionRoom == null) {
            throw new NullPointerException("Auction room cannot be null");
        }
        if (category == null) {
            throw new NullPointerException("Category cannot be null");
        }
        System.out.print("id:      ");
        System.out.println(auctionRoom.getId());
        System.out.print("name:    ");
        System.out.println(category.getName());
        System.out.println();
    }
}
