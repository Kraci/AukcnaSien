package sk.kracina1.db2.application.ts;

//import sk.kracina1.db2.application.DbContext;
//import sk.kracina1.db2.application.rdg.*;
//import sk.kracina1.db2.application.ui.AirplaneSeatPrinter;
//import sk.kracina1.db2.application.ui.SearchFlightPrinter;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//public class AirportService {
//
//    private static final AirportService INSTANCE = new AirportService();
//
//    public static AirportService getInstance() {
//        return INSTANCE;
//    }
//
//    public void buyFlightTicket(int flightID, String travellerName, int travellerAge, int travellerIdentifier) throws SQLException, AirportException {
//        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        DbContext.getConnection().setAutoCommit(false);
//
//        try {
//
//            Flight flight = FlightFinder.getInstance().findById(flightID);
//
//            List<FlightTicket> flightTicketsForFlight = FlightTicketFinder.getInstance().findAllTicketsForFlight(flight.getId());
//            Integer boughtFlightTickets = flightTicketsForFlight.size();
//
//            List<AirplaneSeat> seatsForAirplane = AirplaneSeatFinder.getInstance().findAllSeatsForAirplane(flight.getAirplaneModelId());
//            Integer capacity = seatsForAirplane.size();
//
//            if (boughtFlightTickets >= capacity) {
//                throw new AirportException("Flight is full");
//            }
//
//            Integer flightTicketTravellerId = 0;
//            Traveller existingTraveller = TravellerFinder.getInstance().findByIdentifier(travellerIdentifier);
//
//            if (existingTraveller != null) {
//                flightTicketTravellerId = existingTraveller.getId();
//            } else {
//                Traveller traveller = new Traveller();
//                traveller.setName(travellerName);
//                traveller.setAge(travellerAge);
//                traveller.setIdentifier(travellerIdentifier);
//                traveller.insert();
//                flightTicketTravellerId = traveller.getId();
//            }
//
//            FlightTicket flightTicket = new FlightTicket();
//            flightTicket.setCancelled(false);
//            flightTicket.setFlightId(flight.getId());
//            flightTicket.setAirplaneSeatId(null);
//            flightTicket.setTravellerId(flightTicketTravellerId);
//            flightTicket.insert();
//
//            DbContext.getConnection().commit();
//
//            System.out.println("Flight ticket was successfully bought, id: " + flightTicket.getId());
//
//        } catch(Exception e) {
//            DbContext.getConnection().rollback();
//            throw e;
//        } finally {
//            DbContext.getConnection().setAutoCommit(true);
//        }
//    }
//
//    public void cancelFlightTicket(FlightTicket flightTicket) throws SQLException, AirportException {
//        int flightId = flightTicket.getFlightId();
//        Flight flight = FlightFinder.getInstance().findById(flightId);
//
//        Timestamp now = Timestamp.from(Instant.now());
//        Timestamp departure = flight.getDeparture();
//        Timestamp departureMinusDay = new Timestamp( departure.getTime() - (24 * 60 * 60 * 1000) );
//
//        if (now.compareTo(departureMinusDay) >= 0 && now.compareTo(departure) < 0) {
//            flightTicket.setCancelled(true);
//            flightTicket.update();
//        }
//
//        throw new AirportException("You can cancel flight ticket only 1 day before departure");
//    }
//
//    public void changeAirplaneModelForFlight(int flightId, int airplaneModelId) throws SQLException, AirportException {
//
//        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        DbContext.getConnection().setAutoCommit(false);
//
//        try {
//
//            List<FlightTicket> flightTicketsForFlight = FlightTicketFinder.getInstance().findAllTicketsForFlight(flightId);
//            List<AirplaneSeat> seatsForAirplane = AirplaneSeatFinder.getInstance().findAllSeatsForAirplane(airplaneModelId);
//
//            if (seatsForAirplane.size() < flightTicketsForFlight.size()) {
//                throw new AirportException("Airplane doesn't have enough capacity");
//            }
//
//            List<FlightTicket> firstClassTicketsForFlight = FlightTicketFinder.getInstance().findAllTicketsForFlightAndClass(flightId, ClassType.FIRST);
//            List<AirplaneSeat> firstClassSeatsForAirplane = AirplaneSeatFinder.getInstance().findAllSeatsForAirplaneAndClass(airplaneModelId, ClassType.FIRST);
//
//            if (firstClassSeatsForAirplane.size() < firstClassTicketsForFlight.size()) {
//                throw new AirportException("Airplane doesn't have enough first class seats");
//            }
//
//            List<FlightTicket> businessClassTicketsForFlight = FlightTicketFinder.getInstance().findAllTicketsForFlightAndClass(flightId, ClassType.BUSINESS);
//            List<AirplaneSeat> businessClassSeatsForAirplane = AirplaneSeatFinder.getInstance().findAllSeatsForAirplaneAndClass(airplaneModelId, ClassType.BUSINESS);
//
//            if (businessClassSeatsForAirplane.size() < businessClassTicketsForFlight.size()) {
//                throw new AirportException("Airplane doesn't have enough business class seats");
//            }
//
//            List<FlightTicket> economyClassTicketsForFlight = FlightTicketFinder.getInstance().findAllTicketsForFlightAndClass(flightId, ClassType.ECONOMY);
//            List<AirplaneSeat> economyClassSeatsForAirplane = AirplaneSeatFinder.getInstance().findAllSeatsForAirplaneAndClass(airplaneModelId, ClassType.ECONOMY);
//
//            if (economyClassSeatsForAirplane.size() < economyClassTicketsForFlight.size()) {
//                throw new AirportException("Airplane doesn't have enough economy class seats");
//            }
//
//            Flight flight = FlightFinder.getInstance().findById(flightId);
//            flight.setAirplaneModelId(airplaneModelId);
//            flight.update();
//
//            for (FlightTicket flightTicket : firstClassTicketsForFlight) {
//                AirplaneSeat seat = firstClassSeatsForAirplane.remove(firstClassSeatsForAirplane.size()-1);
//                flightTicket.setAirplaneSeatId(seat.getId());
//                flightTicket.update();
//            }
//
//            for (FlightTicket flightTicket : businessClassTicketsForFlight) {
//                AirplaneSeat seat = businessClassSeatsForAirplane.remove(businessClassSeatsForAirplane.size()-1);
//                flightTicket.setAirplaneSeatId(seat.getId());
//                flightTicket.update();
//            }
//
//            for (FlightTicket flightTicket : economyClassTicketsForFlight) {
//                AirplaneSeat seat = economyClassSeatsForAirplane.remove(economyClassSeatsForAirplane.size()-1);
//                flightTicket.setAirplaneSeatId(seat.getId());
//                flightTicket.update();
//            }
//
//            DbContext.getConnection().commit();
//
//            System.out.println("Airplane for flight was successfully changed");
//
//        } catch(Exception e) {
//            DbContext.getConnection().rollback();
//            throw e;
//        } finally {
//            DbContext.getConnection().setAutoCommit(true);
//        }
//    }
//
//    public void chooseSeatForTicket(int flightTicketId) {
//        while(true) {
//            try {
//
//                doChooseSeatForTicket(flightTicketId);
//                System.out.println("Seat was successfully added to flight ticket");
//                break;
//            } catch (Exception e) {
//                System.out.println("Seat was taken");
//            }
//        }
//    }
//
//    public void doChooseSeatForTicket(int flightTicketId) throws SQLException, IOException {
//
//        FlightTicket flightTicket = FlightTicketFinder.getInstance().findById(flightTicketId);
//
//        int flightID = flightTicket.getFlightId();
//        Flight flight = FlightFinder.getInstance().findById(flightID);
//        int airplaneModelID = flight.getAirplaneModelId();
//
//        List<AirplaneSeat> freeSeats = AirplaneSeatFinder.getInstance().findAllFreeSeatsForFlight(airplaneModelID, flightID);
//
//        for (AirplaneSeat s : freeSeats) {
//            AirplaneSeatPrinter.getInstance().printer(s);
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.print("Enter a seat id: ");
//        int airplaneSeatID = Integer.parseInt(br.readLine());
//
//        try {
//
//            // nepotrebne pouzit tranzakciu, ak si dvaja vyberu to iste miesto, vynimka odchyti unique integritne obmedzenie
////            FlightTicket ft = FlightTicketFinder.getInstance().findById(flightTicketId);
////            if (ft.getAirplaneSeatId() != 0) {
////                throw new AirportException("Seat was taken");
////            }
//            flightTicket.setAirplaneSeatId(airplaneSeatID);
//            flightTicket.update();
//
//        } catch(SQLException e) {
//            throw e;
//        }
//
//    }
//
//    public void buyTicketsForSearchedFlight(List<SearchFlight> searchFlights, int selectedIndex, String travellerName, int age, int identifier) throws SQLException, AirportException {
//        Integer[] flights = searchFlights.get(selectedIndex).getFlightIDs();
//
//        for (int i = 0; i < flights.length; i++) {
//            buyFlightTicket(flights[i], travellerName, age, identifier);
//            System.out.println(flights[i]);
//        }
//    }
//
//}
