DROP TABLE IF EXISTS first_names CASCADE;
CREATE TABLE first_names
(
  first_name VARCHAR
);

INSERT INTO first_names (first_name)
VALUES	('James'), ('Willie'), ('Chad'), ('Zachary'), ('Mathew'),
  ('John'), ('Ralph'), ('Jacob'), ('Corey'), ('Tyrone'),
  ('Robert'), ('Lawrence'), ('Lee'), ('Herman'), ('Darren'),
  ('Michael'), ('Nicholas'), ('Melvin'), ('Maurice'), ('Lonnie'),
  ('William'), ('Roy'), ('Alfred'), ('Vernon'), ('Lance'),
  ('David'), ('Benjamin'), ('Kyle'), ('Roberto'), ('Cody');

DROP TABLE IF EXISTS last_names CASCADE;
CREATE TABLE last_names
(
  last_name VARCHAR
);

INSERT INTO last_names (last_name)
VALUES	('Smith'), ('Jones'), ('Taylor'), ('Williams'), ('Brown'),
  ('Davies'), ('Evans'), ('Wilson'), ('Thomas'), ('Roberts'),
  ('Johnson'), ('Lewis'), ('Walker'), ('Robinson'), ('Wood'),
  ('Thompson'), ('White'), ('Watson'), ('Jackson'), ('Wright'),
  ('Green'), ('Harris'), ('Cooper'), ('King'), ('Lee'),
  ('Martin'), ('Clarke'), ('James'), ('Morgan'), ('Hughes'),
  ('Edwards'), ('Hill'), ('Moore'), ('Clark'), ('Harrison'),
  ('Scott'), ('Young'), ('Morris'), ('Hall'), ('Ward'),
  ('Turner'), ('Carter'), ('Phillips'), ('Mitchell'), ('Patel'),
  ('Adams'), ('Campbell'), ('Anderson'), ('Allen'), ('Cook');

CREATE OR replace function random_first_name() returns VARCHAR LANGUAGE SQL AS
$$
SELECT first_name FROM first_names ORDER BY random() limit 1
$$;

CREATE OR replace function random_last_name() returns VARCHAR LANGUAGE SQL AS
$$
SELECT last_name FROM last_names ORDER BY random() limit 1
$$;

INSERT INTO users (username, mail, password, first_name, surname, age, money)
  SELECT
    NULL,
    NULL,
    NULL,
    random_first_name() as first_name,
    random_last_name() as surname,
    random() * 82 + 18 AS age,
    ROUND((random() * 100000)::NUMERIC, 2) AS money
  FROM generate_series(1, 3000) as seq(i);

UPDATE users SET
  username = lower(first_name) ||  lower(surname),
  mail = lower(first_name) ||  lower(surname) || '@gmail.com',
  password = first_name || surname || age;

INSERT INTO categories (name) VALUES ('Antiques'),('Art'),('Baby'),('Books'),('Business & Industrial'),
  ('Cameras & Photo'),('Cell Phones & Accessories'),('Clothing, Shoes & Accessories'),('Coins & Paper Money'),
  ('Collectibles'),('Computers/Tablets & Networking'),('Consumer Electronics'),('Crafts'),('Dolls & Bears'),
  ('DVDs & Movies'),('Entertainment Memorabilia'),('Gift Cards & Coupons'),('Health & Beauty'),
  ('Home & Garden'),('Jewelry & Watches'),('Music'),('Musical Instruments & Gear'),('Pet Supplies'),
  ('Pottery & Glass'),('Real Estate'),('Specialty Services'),('Sporting Goods'),('Sports Mem, Cards & Fan Shop'),
  ('Stamps'),('Tickets & Experiences'),('Toys & Hobbies'),('Travel'),('Video Games & Consoles'),('Everything Else');

drop function random_first_name();
drop function random_last_name();
drop table first_names;
drop table last_names;