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
        System.out.print("id:         ");
        System.out.println(auctionItem.getId());
        System.out.print("Room id:         ");
        System.out.println(auctionItem.getRoom_id());
        System.out.print("Item id:         ");
        System.out.println(auctionItem.getItem_id());
        System.out.print("Starting price:   ");
        System.out.println(auctionItem.getStarting_price());
        System.out.print("Min bid price:         ");
        System.out.println(auctionItem.getMin_bid_price());
        System.out.print("End date:         ");
        System.out.println(auctionItem.getEnd_date());
        System.out.println();
    }

    public void printForRoom(AuctionItem auctionItem, Item item){
        if (auctionItem == null) {
            throw new NullPointerException("Auction item cannot be null");
        }
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        System.out.print("id:     ");
        System.out.println(auctionItem.getId());
        System.out.print("name:   ");
        System.out.println(item.getName());
    }
}
