package Q9;

import java.util.Date;
import java.util.Scanner;

class AirlineDataException extends Exception {
    public AirlineDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws AirlineDataException {
        if (id <= 0) throw new AirlineDataException("ID must be > 0");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Airline extends Entity {
    private String airlineName, address, contactEmail;
    
    public Airline(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail) throws AirlineDataException {
        super(id, createdDate, updatedDate);
        if (contactEmail == null || !contactEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new AirlineDataException("Invalid email");
        this.airlineName = airlineName; this.address = address; this.contactEmail = contactEmail;
    }
    
    public String getAirlineName() { return airlineName; }
    public String getAddress() { return address; }
    public String getContactEmail() { return contactEmail; }
}

class Flight extends Airline {
    private String flightNumber, destination, departureTime;
    
    public Flight(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail);
        if (flightNumber == null || flightNumber.trim().isEmpty()) throw new AirlineDataException("Flight number cannot be empty");
        if (destination == null || destination.trim().isEmpty()) throw new AirlineDataException("Destination cannot be empty");
        if (departureTime == null || departureTime.trim().isEmpty()) throw new AirlineDataException("Departure time cannot be empty");
        this.flightNumber = flightNumber; this.destination = destination; this.departureTime = departureTime;
    }
    
    public String getFlightNumber() { return flightNumber; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
}

class Passenger extends Flight {
    private String passengerName, passportNumber, nationality;
    
    public Passenger(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime);
        if (passportNumber == null || passportNumber.trim().isEmpty()) throw new AirlineDataException("Passport number cannot be empty");
        this.passengerName = passengerName; this.passportNumber = passportNumber; this.nationality = nationality;
    }
    
    public String getPassengerName() { return passengerName; }
    public String getPassportNumber() { return passportNumber; }
    public String getNationality() { return nationality; }
}

class Seat extends Passenger {
    private String seatNumber, seatType;
    
    public Seat(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality, String seatNumber, String seatType) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality);
        if (seatType == null || (!seatType.equals("Economy") && !seatType.equals("Business"))) throw new AirlineDataException("Seat type must be 'Economy' or 'Business'");
        this.seatNumber = seatNumber; this.seatType = seatType;
    }
    
    public String getSeatNumber() { return seatNumber; }
    public String getSeatType() { return seatType; }
}

class Ticket extends Seat {
    private String ticketNumber;
    private double price;
    
    public Ticket(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality, String seatNumber, String seatType, String ticketNumber, double price) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality, seatNumber, seatType);
        if (price <= 0) throw new AirlineDataException("Price must be > 0");
        this.ticketNumber = ticketNumber; this.price = price;
    }
    
    public String getTicketNumber() { return ticketNumber; }
    public double getPrice() { return price; }
}

class Baggage extends Ticket {
    private double baggageWeight, baggageFee;
    
    public Baggage(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality, String seatNumber, String seatType, String ticketNumber, double price, double baggageWeight, double baggageFee) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality, seatNumber, seatType, ticketNumber, price);
        if (baggageWeight < 0 || baggageFee < 0) throw new AirlineDataException("Baggage details must be >= 0");
        this.baggageWeight = baggageWeight; this.baggageFee = baggageFee;
    }
    
    public double getBaggageWeight() { return baggageWeight; }
    public double getBaggageFee() { return baggageFee; }
}

class Payment extends Baggage {
    private String paymentDate, paymentMode;
    
    public Payment(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality, String seatNumber, String seatType, String ticketNumber, double price, double baggageWeight, double baggageFee, String paymentDate, String paymentMode) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality, seatNumber, seatType, ticketNumber, price, baggageWeight, baggageFee);
        if (paymentDate == null || paymentDate.trim().isEmpty()) throw new AirlineDataException("Payment date cannot be empty");
        if (paymentMode == null || paymentMode.trim().isEmpty()) throw new AirlineDataException("Payment mode cannot be empty");
        this.paymentDate = paymentDate; this.paymentMode = paymentMode;
    }
    
    public String getPaymentDate() { return paymentDate; }
    public String getPaymentMode() { return paymentMode; }
}

class Invoice extends Payment {
    private double totalFare;
    
    public Invoice(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality, String seatNumber, String seatType, String ticketNumber, double price, double baggageWeight, double baggageFee, String paymentDate, String paymentMode, double totalFare) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality, seatNumber, seatType, ticketNumber, price, baggageWeight, baggageFee, paymentDate, paymentMode);
        if (totalFare <= 0) throw new AirlineDataException("Total fare must be > 0");
        this.totalFare = totalFare;
    }
    
    public double getTotalFare() { return totalFare; }
}

public final class TicketRecord extends Invoice {
    
    public TicketRecord(int id, Date createdDate, Date updatedDate, String airlineName, String address, String contactEmail, String flightNumber, String destination, String departureTime, String passengerName, String passportNumber, String nationality, String seatNumber, String seatType, String ticketNumber, double price, double baggageWeight, double baggageFee, String paymentDate, String paymentMode, double totalFare) throws AirlineDataException {
        super(id, createdDate, updatedDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality, seatNumber, seatType, ticketNumber, price, baggageWeight, baggageFee, paymentDate, paymentMode, totalFare);
    }
    
    public double generateInvoice() {
        return getPrice() + getBaggageFee();
    }
    
    public void displayDetails() {
        String output = "\n============ AIRLINE TICKET RECORD ===========\n" +
                       "Airline: " + getAirlineName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Contact Email: " + getContactEmail() + "\n" +
                       "Flight: " + getFlightNumber() + " to " + getDestination() + "\n" +
                       "Departure Time: " + getDepartureTime() + "\n" +
                       "Passenger: " + getPassengerName() + "\n" +
                       "Passport: " + getPassportNumber() + "\n" +
                       "Nationality: " + getNationality() + "\n" +
                       "Seat: " + getSeatNumber() + " (" + getSeatType() + ")\n" +
                       "Ticket Number: " + getTicketNumber() + "\n" +
                       "Baggage Weight: " + getBaggageWeight() + " kg\n" +
                       "Payment Date: " + getPaymentDate() + "\n" +
                       "Payment Mode: " + getPaymentMode() + "\n" +
                       "\n=== FARE BREAKDOWN ===\n" +
                       "Ticket Price: $" + getPrice() + "\n" +
                       "Baggage Fee: $" + getBaggageFee() + "\n" +
                       "Total Fare: $" + getTotalFare() + "\n" +
                       "CALCULATED FARE: $" + generateInvoice();
        System.out.println("27396" + output);
    }
    
    private static int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value < 0) {
                    System.out.println("❌ Error: Value cannot be negative. Please enter a valid number.");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("❌ Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value < 0) {
                    System.out.println("❌ Error: Amount cannot be negative. Please enter a valid amount.");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("❌ Error: Please enter a valid amount.");
                scanner.nextLine();
            }
        }
    }

    private static String getValidString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("❌ Error: This field cannot be empty. Please enter a valid input.");
                continue;
            }
            return input;
        }
    }

    private static String getValidEmail(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("❌ Error: Email cannot be empty.");
                continue;
            }
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("❌ Error: Please enter a valid email address.");
                continue;
            }
            return email;
        }
    }

    private static String getValidSeatType(Scanner scanner) {
        while (true) {
            System.out.print("Seat Type (Economy/Business): ");
            String seatType = scanner.nextLine().trim();
            if (seatType.equalsIgnoreCase("Economy") || seatType.equalsIgnoreCase("Business")) {
                return seatType;
            }
            System.out.println("❌ Error: Please enter Economy or Business.");
        }
    }

    private static String getValidYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y") || 
                input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
                return input;
            }
            System.out.println("❌ Error: Please enter yes/y or no/n.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("        AIRLINE TICKETING SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Airline Information
                System.out.println("\n[AIRLINE INFORMATION]");
                String airlineName = getValidString(scanner, "Airline Name: ");
                String address = getValidString(scanner, "Address: ");
                String contactEmail = getValidEmail(scanner, "Contact Email: ");
                
                // Flight Information
                System.out.println("\n[FLIGHT INFORMATION]");
                String flightNumber = getValidString(scanner, "Flight Number: ");
                String destination = getValidString(scanner, "Destination: ");
                String departureTime = getValidString(scanner, "Departure Time: ");
                
                // Passenger Information
                System.out.println("\n[PASSENGER INFORMATION]");
                String passengerName = getValidString(scanner, "Passenger Name: ");
                String passportNumber = getValidString(scanner, "Passport Number: ");
                String nationality = getValidString(scanner, "Nationality: ");
                
                // Seat Information
                System.out.println("\n[SEAT INFORMATION]");
                String seatNumber = getValidString(scanner, "Seat Number: ");
                String seatType = getValidSeatType(scanner);
                
                // Ticket Information
                System.out.println("\n[TICKET INFORMATION]");
                String ticketNumber = getValidString(scanner, "Ticket Number: ");
                double price = getValidDouble(scanner, "Price ($): ");
                
                // Baggage Information
                System.out.println("\n[BAGGAGE INFORMATION]");
                double baggageWeight = getValidDouble(scanner, "Baggage Weight (kg): ");
                double baggageFee = getValidDouble(scanner, "Baggage Fee ($): ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                String paymentDate = getValidString(scanner, "Payment Date: ");
                String paymentMode = getValidString(scanner, "Payment Mode: ");
                double totalFare = price + baggageFee;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing ticket record...");
                System.out.println("-".repeat(50));
                
                TicketRecord record = new TicketRecord(id, currentDate, currentDate, airlineName, address, contactEmail, flightNumber, destination, departureTime, passengerName, passportNumber, nationality, seatNumber, seatType, ticketNumber, price, baggageWeight, baggageFee, paymentDate, paymentMode, totalFare);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Airline Ticketing System!");
                    break;
                }
                
            } catch (AirlineDataException e) {
                System.out.println("\n❌ Airline Data Error: " + e.getMessage());
                String retry = getValidYesNo(scanner, "\nDo you want to try again? (yes/no): ");
                if (retry.equalsIgnoreCase("no") || retry.equalsIgnoreCase("n")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\n❌ Unexpected Error: " + e.getMessage());
                String retry = getValidYesNo(scanner, "\nDo you want to try again? (yes/no): ");
                if (retry.equalsIgnoreCase("no") || retry.equalsIgnoreCase("n")) {
                    break;
                }
            }
        }
        
        scanner.close();
    }
}