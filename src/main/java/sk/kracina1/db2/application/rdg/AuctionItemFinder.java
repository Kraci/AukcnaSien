package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuctionItemFinder extends BaseFinder<AuctionItem> {

    private static final AuctionItemFinder INSTANCE = new AuctionItemFinder();

    public static AuctionItemFinder getInstance() { return INSTANCE; }

    private AuctionItemFinder() { }

    public AuctionItem findById(int id) throws SQLException {
        return findByInt("SELECT * FROM auction_items WHERE id = ?", id);
    }

    public List<AuctionItem> findByAuctionRoomId(int auctionRoomId) throws SQLException {
        return findAll("SELECT * FROM auction_items WHERE room_id = ?", auctionRoomId);
    }

    @Override
    protected AuctionItem load(ResultSet r) throws SQLException {
        AuctionItem auctionItem = new AuctionItem();

        auctionItem.setId(r.getInt("id"));
        auctionItem.setRoom_id(r.getInt("room_id"));
        auctionItem.setItem_id(r.getInt("item_id"));
        auctionItem.setStarting_price(r.getDouble("starting_price"));
        auctionItem.setMin_bid_price(r.getDouble("min_bid_price"));
        auctionItem.setEnd_date(r.getDate("end_date"));

        return auctionItem;
    }
}
