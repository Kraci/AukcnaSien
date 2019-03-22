DROP TABLE if EXISTS users CASCADE;
CREATE TABLE users
(
    id serial PRIMARY KEY,
    username VARCHAR,
    mail VARCHAR,
    password VARCHAR,
    first_name VARCHAR,
    surname VARCHAR,
    age INTEGER
);

DROP TABLE if EXISTS categories CASCADE;
CREATE TABLE categories
(
    id serial PRIMARY KEY,
    name VARCHAR
);

DROP TABLE if EXISTS items CASCADE;
CREATE TABLE items
(
    id serial PRIMARY KEY,
    category_id INTEGER REFERENCES categories,
    user_id INTEGER REFERENCES users,
    count INTEGER,
    name VARCHAR,
    description VARCHAR
);

DROP TABLE if EXISTS auction_rooms CASCADE;
CREATE TABLE auction_rooms
(
    id serial PRIMARY KEY,
    category_id INTEGER REFERENCES categories
);

DROP TABLE if EXISTS auction_items CASCADE;
CREATE TABLE auction_items
(
    id serial PRIMARY KEY,
    room_id INTEGER REFERENCES auction_rooms,
    item_id INTEGER REFERENCES items,
    starting_price INTEGER,
    min_bid_price INTEGER,
    end_date DATE
);
-- DROP TABLE if EXISTS cities CASCADE;
-- CREATE TABLE cities
-- (
--     id serial PRIMARY KEY,
--     name VARCHAR
-- );
--
-- DROP TABLE if EXISTS airports CASCADE;
-- CREATE TABLE airports
-- (
--     id serial PRIMARY KEY,
--     name VARCHAR,
--     city_id INTEGER REFERENCES cities
-- );
--
-- DROP TABLE if EXISTS flight_tariff CASCADE;
-- CREATE TABLE flight_tariff
-- (
--     id serial PRIMARY KEY,
--     flight_id INTEGER REFERENCES flights ON DELETE CASCADE,
--     tariff_id INTEGER REFERENCES tariffs ON DELETE CASCADE
-- );
--
-- DROP TABLE if EXISTS flight_tickets CASCADE;
-- CREATE TABLE flight_tickets
-- (
--     id serial PRIMARY KEY,
--     cancelled BOOLEAN,
--     flight_id INTEGER REFERENCES flights ON DELETE CASCADE,
--     airplane_seat_id INTEGER REFERENCES airplane_seats,
--     traveller_id INTEGER REFERENCES travellers,
--     UNIQUE (flight_id, traveller_id),
--     UNIQUE (flight_id, airplane_seat_id)
-- );