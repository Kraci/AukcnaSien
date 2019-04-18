DROP TABLE if EXISTS users CASCADE;
CREATE TABLE users
(
    id serial PRIMARY KEY,
    username VARCHAR,
    mail VARCHAR,
    password VARCHAR,
    first_name VARCHAR,
    surname VARCHAR,
    age INTEGER,
    money DOUBLE PRECISION
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