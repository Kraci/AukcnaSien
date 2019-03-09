-- TRUNCATE TABLE age_intervals RESTART IDENTITY CASCADE;

---- pomocne tabulky na generovanie dat

-- first names

-- DROP TABLE IF EXISTS first_names CASCADE;
-- CREATE TABLE first_names
-- (
--   first_name VARCHAR
-- );
--
-- INSERT INTO first_names (first_name)
-- VALUES	('James'), ('Willie'), ('Chad'), ('Zachary'), ('Mathew'),
--   ('John'), ('Ralph'), ('Jacob'), ('Corey'), ('Tyrone'),
--   ('Robert'), ('Lawrence'), ('Lee'), ('Herman'), ('Darren'),
--   ('Michael'), ('Nicholas'), ('Melvin'), ('Maurice'), ('Lonnie'),
--   ('William'), ('Roy'), ('Alfred'), ('Vernon'), ('Lance'),
--   ('David'), ('Benjamin'), ('Kyle'), ('Roberto'), ('Cody');

-- last names

-- DROP TABLE IF EXISTS last_names CASCADE;
-- CREATE TABLE last_names
-- (
--   last_name VARCHAR
-- );
--
-- INSERT INTO last_names (last_name)
-- VALUES	('Smith'), ('Jones'), ('Taylor'), ('Williams'), ('Brown'),
--   ('Davies'), ('Evans'), ('Wilson'), ('Thomas'), ('Roberts'),
--   ('Johnson'), ('Lewis'), ('Walker'), ('Robinson'), ('Wood'),
--   ('Thompson'), ('White'), ('Watson'), ('Jackson'), ('Wright'),
--   ('Green'), ('Harris'), ('Cooper'), ('King'), ('Lee'),
--   ('Martin'), ('Clarke'), ('James'), ('Morgan'), ('Hughes'),
--   ('Edwards'), ('Hill'), ('Moore'), ('Clark'), ('Harrison'),
--   ('Scott'), ('Young'), ('Morris'), ('Hall'), ('Ward'),
--   ('Turner'), ('Carter'), ('Phillips'), ('Mitchell'), ('Patel'),
--   ('Adams'), ('Campbell'), ('Anderson'), ('Allen'), ('Cook');

-- street names

-- DROP TABLE IF EXISTS street_names CASCADE;
-- CREATE TABLE street_names
-- (
--   street_name VARCHAR
-- );
--
-- INSERT INTO street_names (street_name)
-- VALUES	('Main Street'), ('Church Street'), ('Main Street North'), ('Main Street South'), ('Elm Street'),
--   ('High Street'), ('Washington Street'), ('Main Street West'), ('Main Street East'), ('Park Avenue'),
--   ('Walnut Street'), ('2nd Street'), ('Chestnut Street'), ('Broad Street'), ('Maple Avenue'),
--   ('Maple Street'), ('Center Street'), ('Oak Street'), ('Pine Street'), ('River Road');

-- city names

-- DROP TABLE IF EXISTS city_names CASCADE;
-- CREATE TABLE city_names
-- (
--   id serial PRIMARY KEY,
--   city_name VARCHAR
-- );
--
-- INSERT INTO city_names (city_name)
-- VALUES	('New York'), ('Los Angeles'), ('Chicago'), ('Houston'), ('Philadelphia'),
--   ('Phoenix'), ('San Antonio'), ('San Diego'), ('Dallas'), ('San Jose'),
--   ('Austin'), ('Jacksonville'), ('San Francisco'), ('Indianapolis'), ('Columbus'),
--   ('Fort Worth'), ('Charlotte'), ('Detroit'), ('El Paso'), ('Seattle'),
--   ('Bratislava'), ('Paris'), ('London'), ('Manchester'), ('Vienna'), ('Budapest'), ('Prague'), ('Berlin'),
--   ('Athens'), ('Bern'), ('Lisbon'), ('Moscow'), ('Ottawa'), ('Rome'), ('Stockholm'), ('Tokyo'), ('Zagreb');

---- hlavne tabulky

-- CREATE OR replace function random_city_id() returns INTEGER LANGUAGE SQL AS
-- $$
-- SELECT id FROM city_names WHERE id IN (SELECT round(random() * (SELECT COUNT(*) FROM city_names))::INTEGER AS id
--                                        FROM generate_series(1, 20)) ORDER BY random() LIMIT 1
-- $$;
--
-- CREATE OR replace function random_city_id_different(other_id integer) returns INTEGER LANGUAGE SQL AS
-- $$
-- SELECT id FROM cities tablesample system_rows(10) WHERE id != other_id ORDER BY random() limit 1
-- $$;

-- CREATE OR replace function random_city_name() returns VARCHAR LANGUAGE SQL AS
-- $$
-- SELECT city_name FROM city_names tablesample system_rows(10) ORDER BY random() limit 1
-- $$;

-- ///////////////////
-- INSERT INTO flights (kilometers, duration, departure, arrival, airplane_model_id, company_id, airport_from_id, airport_to_id)
-- SELECT trunc(random() * 1000 + 400) AS kilometres,
--     TIME '01:00' + random() * INTERVAL '10:00' AS duration,
--     TIMESTAMP '2018-01-01 10:00:00' + random() * INTERVAL '730 days' AS departure,
--     TIMESTAMP '2018-01-01 10:00:00',
--     random_airplane_model_id(),
--     random_company_id(),
--     random_airport_id(),
--     random_airport_id()
-- FROM generate_series(1, 3000) as seq(i);
--
-- UPDATE flights SET airport_to_id = random_airport_id_different(airport_from_id) WHERE airport_from_id = airport_to_id;
-- UPDATE flights SET arrival = departure + random() * INTERVAL '2 days';
-- ///////////////////

---- nakoniec zmazeme pomocne veci
--
-- drop table first_names, last_names, street_names, city_names cascade;
--
-- drop function random_first_name();
-- drop function random_last_name();
-- drop function random_city_name();
-- drop function random_city_id();
-- drop function random_city_id_different(integer);
-- drop function random_airplane_model_id();
-- drop function random_company_id();
-- drop function random_airport_id();
-- drop function random_airport_id_different(integer);
-- drop function random_class_type_id();
-- drop function random_age_interval_id();
-- drop function random_tariff_id(integer, integer);
-- drop function random_traveller_id();