package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AirportFinder extends BaseFinder<Airport> {

    private static final AirportFinder INSTANCE = new AirportFinder();

    public static AirportFinder getInstance() { return INSTANCE; }

    private AirportFinder() { }

    public Airport findById(int id) throws SQLException {
        return findByInt("SELECT * FROM airports WHERE id = ?", id);
    }

    public List<Airport> findAll() throws SQLException {
        return findAll("SELECT * FROM airports");
    }

    @Override
    protected Airport load(ResultSet r) throws SQLException {
        Airport airport = new Airport();

        airport.setId(r.getInt("id"));
        airport.setName(r.getString("name"));

        return airport;
    }

}
