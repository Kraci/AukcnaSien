package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.AuctionItem;
import sk.kracina1.db2.application.rdg.Item;

public class AuctionItemPrinter {
    private static final AuctionItemPrinter INSTANCE = new AuctionItemPrinter();

    public static AuctionItemPrinter getInstance() { return INSTANCE; }

    private AuctionItemPrinter() {}

    public void print(AuctionItem auctionItem) {
        if (auctionItem == null) {
            throw new NullPointerException("Category cannot be null");
        }

        System.out.println(String.format("%-18s %d", "Auction item id:", auctionItem.getId()));
        System.out.println(String.format("%-18s %d", "Room id:", auctionItem.getRoom_id()));
        System.out.println(String.format("%-18s %d", "Item id:", auctionItem.getItem_id()));
        System.out.println(String.format("%-18s %f", "Starting price:", auctionItem.getStarting_price()));
        System.out.println(String.format("%-18s %f", "Min bid price:", auctionItem.getMin_bid_price()));
        System.out.println(String.format("%-18s %s", "End date:", auctionItem.getEnd_date()));
    }

    public void printForRoom(AuctionItem auctionItem, Item item){
        if (auctionItem == null) {
            throw new NullPointerException("Auction item cannot be null");
        }
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        System.out.println(String.format("%-10s %d", "id:", auctionItem.getId()));
        System.out.println(String.format("%-10s %s", "name:", item.getName()));
    }

    public void printAll(AuctionItem auctionItem, Item item){
        if (auctionItem == null) {
            throw new NullPointerException("Auction item cannot be null");
        }
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }

        System.out.println(String.format("%-18s %s", "Name:", item.getName()));
        System.out.println(String.format("%-18s %s", "Descriptio:", item.getDescription()));
        print(auctionItem);

    }
}
