package sk.kracina1.db2.application.rdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.kracina1.db2.application.DbContext;

public abstract class BaseFinder<T> {

    protected List<T> findAll(String query, int fromCity, int toCity, int ageId, int classId, String departureDate, String arrivalDate, String code) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            s.setString(1, departureDate);
            s.setString(2, arrivalDate);
            s.setString(3, departureDate);
            s.setInt(4, fromCity);
            s.setInt(5, classId);
            s.setInt(6, ageId);
            s.setInt(7, classId);
            s.setString(8, departureDate);
            s.setString(9, arrivalDate);
            s.setString(10, departureDate);
            s.setInt(11, classId);
            s.setInt(12, ageId);
            s.setInt(13, classId);
            s.setString(14, code);
            s.setInt(15, fromCity);
            s.setInt(16, toCity);
            s.setInt(17, classId);
            s.setInt(18, ageId);
            s.setInt(19, toCity);

            try (ResultSet r = s.executeQuery()) {

                List<T> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }

    protected List<T> findAll(String query, int value, int value2) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            s.setInt(1, value);
            s.setInt(2, value2);

            try (ResultSet r = s.executeQuery()) {

                List<T> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }

    protected List<T> findAll(String query, int value) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            s.setInt(1, value);

            try (ResultSet r = s.executeQuery()) {

                List<T> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }

    protected List<T> findAll(String query) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            try (ResultSet r = s.executeQuery()) {

                List<T> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }

    protected T findByInt(String query, int value) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            s.setInt(1, value);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    T c = load(r);

                    if (r.next()) {
                        throw new RuntimeException("Move than one row was returned");
                    }

                    return c;
                } else {
                    return null;
                }

            }
        }
    }

    protected T findByString(String query, String value) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            s.setString(1, value);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    T c = load(r);

                    if (r.next()) {
                        throw new RuntimeException("Move than one row was returned");
                    }

                    return c;
                } else {
                    return null;
                }

            }
        }
    }

    protected T findByString(String query, String value, String value2) throws SQLException {
        if (query == null) {
            throw new NullPointerException("query cannot be null");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(query)) {
            s.setString(1, value);
            s.setString(2, value2);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    T c = load(r);

                    if (r.next()) {
                        throw new RuntimeException("Move than one row was returned");
                    }

                    return c;
                } else {
                    return null;
                }

            }
        }
    }

    protected abstract T load(ResultSet r) throws SQLException;

}
