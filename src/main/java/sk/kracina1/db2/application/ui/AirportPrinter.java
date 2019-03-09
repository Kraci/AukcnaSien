package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.Airport;

public class AirportPrinter {

    private static final AirportPrinter INSTANCE = new AirportPrinter();

    public static AirportPrinter getInstance() { return INSTANCE; }

    private AirportPrinter() {}

    public void print(Airport airport) {
        if (airport == null) {
            throw new NullPointerException("Airport cannot be null");
        }

        System.out.print("id:         ");
        System.out.println(airport.getId());
        System.out.print("name:       ");
        System.out.println(airport.getName());
        System.out.println();
        System.out.println();
    }

}
