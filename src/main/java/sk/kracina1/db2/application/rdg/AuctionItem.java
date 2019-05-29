package sk.kracina1.db2.application.rdg;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuctionItem extends BaseGateway {

    private Integer id;
    private Integer room_id;
    private Integer item_id;
    private Double starting_price;
    private Double price;
    private Double min_bid_price;
    private Date end_date;
    private Integer bidder_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Double getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(Double starting_price) {
        this.starting_price = starting_price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMin_bid_price() {
        return min_bid_price;
    }

    public void setMin_bid_price(Double bid_price) {
        this.min_bid_price = bid_price;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getHighest_bidder() {
        return bidder_id;
    }

    public void setHighest_bidder(Integer bidder_id) {
        this.bidder_id = bidder_id;
    }

    public void insert() throws SQLException {
        super.insert(
                "INSERT INTO auction_items (room_id, item_id, starting_price, price, min_bid_price, end_date, bidder_id) VALUES (?,?,?,?,?,?,?)");
    }

    @Override
    protected void insertFill(PreparedStatement s) throws SQLException {
        s.setInt(1, room_id);
        s.setInt(2, item_id);
        s.setDouble(3, starting_price);
        s.setDouble(4, price);
        s.setDouble(5, min_bid_price);
        s.setDate(6, end_date);
        s.setInt(7, bidder_id);
    }

    @Override
    protected void insertUpdateKeys(ResultSet r) throws SQLException {
        id = r.getInt(1);
    }

    public void update() throws SQLException {
        super.update("UPDATE auction_items SET price = ?, min_bid_price = ?, end_date = ?, bidder_id = ? WHERE id = ?");
    }

    @Override
    protected void updateFill(PreparedStatement s) throws SQLException {
        s.setDouble(1, price);
        s.setDouble(2, min_bid_price);
        s.setDate(3, end_date);
        s.setInt(4, bidder_id);
        s.setInt(5, id);
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id is not set");
        }
        super.delete("DELETE FROM auction_items WHERE id = ?");
    }

    @Override
    protected void deleteFill(PreparedStatement s) throws SQLException {
        s.setInt(1, id);
    }
}
