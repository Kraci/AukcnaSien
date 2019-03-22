package sk.kracina1.db2.application.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

public class MainMenu extends Menu {

    @Override
    public void print() {
        System.out.println("***********************************");
        System.out.println("* 1. list all the users           *");
        System.out.println("* 2. show an user                 *");
        System.out.println("* 3. delete an user               *");
        System.out.println("* 4. add  item                    *");
        System.out.println("* 5. exit                         *");
        System.out.println("***********************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1":   listAllUsers(); break;
                case "2":   showAnUser(); break;
                case "3":   deleteAnUser(); break;
                case "4":   addItem();break;
                case "5":   exit(); break;

                default:    System.out.println("Unknown option"); break;
            }
        } catch(SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listAllUsers() throws SQLException {
        for (User user: UserFinder.getInstance().findAll()) {
            UserPrinter.getInstance().print(user);
        }
    }

    private void showAnUser() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter an user's id: ");
        int userId = Integer.parseInt(br.readLine());

        User user = UserFinder.getInstance().findById(userId);

        if (user == null) {
            System.out.println("No such user exists");
        } else {
            UserPrinter.getInstance().print(user);
        }
    }

    private void addAnAirport() throws IOException, SQLException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        Airport airport = new Airport();
//
//        System.out.print("Enter name: ");
//        airport.setName(br.readLine());
//        System.out.print("Enter city name: ");
//        City city = CityFinder.getInstance().findByName(br.readLine());
//        while (city == null) {
//            System.out.println("No such city exists");
//            city = CityFinder.getInstance().findByName(br.readLine());
//        }
//        airport.setCityId(city.getId());
//        airport.insert();
//
//        System.out.println("The airport has been sucessfully added");
//        System.out.print("The airport's id is: ");
//        System.out.println(airport.getId());
    }

    private void editAnAirport() throws IOException, SQLException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.print("Enter a airport's id: ");
//        int airportId = Integer.parseInt(br.readLine());
//
//        Airport airport = AirportFinder.getInstance().findById(airportId);
//
//        if (airport == null) {
//            System.out.println("No such airport exists");
//        } else {
//            AirportPrinter.getInstance().print(airport);
//
//            System.out.print("Enter name: ");
//            airport.setName(br.readLine());
//            System.out.print("Enter city name: ");
//            City city = CityFinder.getInstance().findByName(br.readLine());
//            while (city == null) {
//                System.out.println("No such city exists");
//                city = CityFinder.getInstance().findByName(br.readLine());
//            }
//            airport.setCityId(city.getId());
//
//            airport.update();
//
//            System.out.println("The airport has been successfully updated");
//        }
    }

    private void deleteAnUser() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter an user's id: ");
        int userId = Integer.parseInt(br.readLine());

        User user = UserFinder.getInstance().findById(userId);

        if (user == null) {
            System.out.println("No such user exists");
        } else {
            user.delete();
            System.out.println("The user has been successfully deleted");
        }
    }

    private void addItem() throws IOException, SQLException {
        Item item = new Item();

        // TODO auth user
        item.setUser_id(1);

        System.out.println("Add new Item.");
        String name = Validation.getInstance().validate("Name: ", Arrays.asList(
                new Required()
        ));
        item.setName(name);
        String count = Validation.getInstance().validate("Count: ", Arrays.asList(
                new Required(),
                new IsInteger()
        ));
        item.setCount(Integer.parseInt(count));

        String description = Validation.getInstance().validate("Description: ", Arrays.asList(
                new Required()
        ));
        System.out.println();
        item.setDescription(description);

        for (Category category : CategoryFinder.getInstance().findAll()) {
            CategoryPrinter.getInstance().print(category);
        }
        String category = Validation.getInstance().validate("Category id: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsInCategories()
        ));
        item.setCategory_id(CategoryFinder.getInstance().findById(Integer.parseInt(category)).getId());

        item.insert();
        System.out.println();
        ItemPrinter.getInstance().print(item);
    }

}