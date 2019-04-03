package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuctionRoomFinder extends BaseFinder<AuctionRoom> {

    private static final AuctionRoomFinder INSTANCE = new AuctionRoomFinder();

    public static AuctionRoomFinder getInstance() { return INSTANCE; }

    private AuctionRoomFinder() { }

    public AuctionRoom findByCategory(int categoryId) throws SQLException {
        return findByInt("SELECT * FROM auction_rooms WHERE category_id = ?", categoryId);
    }

    @Override
    protected AuctionRoom load(ResultSet r) throws SQLException {
        AuctionRoom auctionRoom = new AuctionRoom();
        auctionRoom.setId(r.getInt("id"));
        auctionRoom.setCategory_id(r.getInt("category_id"));
        return auctionRoom;
    }
}
