package Q4;

import java.util.Date;
import java.util.Scanner;

class HotelDataException extends Exception {
    public HotelDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws HotelDataException {
        if (id <= 0) throw new HotelDataException("ID must be > 0");
        if (createdDate == null || updatedDate == null) throw new HotelDataException("Dates cannot be null");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Hotel extends Entity {
    private String hotelName, address, phoneNumber, email;
    
    public Hotel(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email) throws HotelDataException {
        super(id, createdDate, updatedDate);
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) throw new HotelDataException("Phone must be 10 digits");
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new HotelDataException("Invalid email");
        this.hotelName = hotelName; this.address = address; this.phoneNumber = phoneNumber; this.email = email;
    }
    
    public String getHotelName() { return hotelName; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
}

class Room extends Hotel {
    private String roomNumber, roomType;
    private double pricePerNight;
    
    public Room(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email);
        if (pricePerNight <= 0) throw new HotelDataException("Price must be > 0");
        this.roomNumber = roomNumber; this.roomType = roomType; this.pricePerNight = pricePerNight;
    }
    
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
}

class Customer extends Room {
    private String customerName, customerEmail, contactNumber;
    
    public Customer(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight);
        if (customerEmail == null || !customerEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new HotelDataException("Invalid customer email");
        if (contactNumber == null || contactNumber.length() != 10 || !contactNumber.matches("\\d+")) throw new HotelDataException("Contact must be 10 digits");
        this.customerName = customerName; this.customerEmail = customerEmail; this.contactNumber = contactNumber;
    }
    
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getContactNumber() { return contactNumber; }
}

class Booking extends Customer {
    private String bookingDate, checkInDate, checkOutDate;
    
    public Booking(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber, String bookingDate, String checkInDate, String checkOutDate) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber);
        if (bookingDate == null || bookingDate.trim().isEmpty()) throw new HotelDataException("Booking date cannot be empty");
        if (checkInDate == null || checkInDate.trim().isEmpty()) throw new HotelDataException("Check-in date cannot be empty");
        if (checkOutDate == null || checkOutDate.trim().isEmpty()) throw new HotelDataException("Check-out date cannot be empty");
        this.bookingDate = bookingDate; this.checkInDate = checkInDate; this.checkOutDate = checkOutDate;
    }
    
    public String getBookingDate() { return bookingDate; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
}

class Service extends Booking {
    private String serviceName;
    private double serviceCost;
    
    public Service(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber, String bookingDate, String checkInDate, String checkOutDate, String serviceName, double serviceCost) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber, bookingDate, checkInDate, checkOutDate);
        if (serviceCost <= 0) throw new HotelDataException("Service cost must be > 0");
        this.serviceName = serviceName; this.serviceCost = serviceCost;
    }
    
    public String getServiceName() { return serviceName; }
    public double getServiceCost() { return serviceCost; }
}

class Payment extends Service {
    private String paymentMethod, paymentDate;
    
    public Payment(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber, String bookingDate, String checkInDate, String checkOutDate, String serviceName, double serviceCost, String paymentMethod, String paymentDate) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber, bookingDate, checkInDate, checkOutDate, serviceName, serviceCost);
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) throw new HotelDataException("Payment method cannot be null");
        if (paymentDate == null || paymentDate.trim().isEmpty()) throw new HotelDataException("Payment date cannot be null");
        this.paymentMethod = paymentMethod; this.paymentDate = paymentDate;
    }
    
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentDate() { return paymentDate; }
}

class Bill extends Payment {
    private double roomCharge, serviceCharge, totalBill;
    
    public Bill(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber, String bookingDate, String checkInDate, String checkOutDate, String serviceName, double serviceCost, String paymentMethod, String paymentDate, double roomCharge, double serviceCharge, double totalBill) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber, bookingDate, checkInDate, checkOutDate, serviceName, serviceCost, paymentMethod, paymentDate);
        if (roomCharge <= 0 || serviceCharge <= 0 || totalBill <= 0) throw new HotelDataException("All charges must be > 0");
        this.roomCharge = roomCharge; this.serviceCharge = serviceCharge; this.totalBill = totalBill;
    }
    
    public double getRoomCharge() { return roomCharge; }
    public double getServiceCharge() { return serviceCharge; }
    public double getTotalBill() { return totalBill; }
}

class Feedback extends Bill {
    private int rating;
    private String comments;
    
    public Feedback(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber, String bookingDate, String checkInDate, String checkOutDate, String serviceName, double serviceCost, String paymentMethod, String paymentDate, double roomCharge, double serviceCharge, double totalBill, int rating, String comments) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber, bookingDate, checkInDate, checkOutDate, serviceName, serviceCost, paymentMethod, paymentDate, roomCharge, serviceCharge, totalBill);
        if (rating < 1 || rating > 5) throw new HotelDataException("Rating must be between 1-5");
        this.rating = rating; this.comments = comments;
    }
    
    public int getRating() { return rating; }
    public String getComments() { return comments; }
}

public final class ReservationRecord extends Feedback {
    
    public ReservationRecord(int id, Date createdDate, Date updatedDate, String hotelName, String address, String phoneNumber, String email, String roomNumber, String roomType, double pricePerNight, String customerName, String customerEmail, String contactNumber, String bookingDate, String checkInDate, String checkOutDate, String serviceName, double serviceCost, String paymentMethod, String paymentDate, double roomCharge, double serviceCharge, double totalBill, int rating, String comments) throws HotelDataException {
        super(id, createdDate, updatedDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber, bookingDate, checkInDate, checkOutDate, serviceName, serviceCost, paymentMethod, paymentDate, roomCharge, serviceCharge, totalBill, rating, comments);
    }
    
    public double generateBill() {
        return getRoomCharge() + getServiceCharge();
    }
    
    public void displayDetails() {
        String output = "\n============ HOTEL RESERVATION RECORD ===========\n" +
                       "Hotel: " + getHotelName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Email: " + getEmail() + "\n" +
                       "Room: " + getRoomNumber() + " (" + getRoomType() + ") - $" + getPricePerNight() + "/night\n" +
                       "Customer: " + getCustomerName() + "\n" +
                       "Customer Email: " + getCustomerEmail() + "\n" +
                       "Contact: " + getContactNumber() + "\n" +
                       "Booking Date: " + getBookingDate() + "\n" +
                       "Check-in: " + getCheckInDate() + "\n" +
                       "Check-out: " + getCheckOutDate() + "\n" +
                       "Service: " + getServiceName() + " - $" + getServiceCost() + "\n" +
                       "Payment Method: " + getPaymentMethod() + "\n" +
                       "Payment Date: " + getPaymentDate() + "\n" +
                       "\n=== BILL BREAKDOWN ===\n" +
                       "Room Charge: $" + getRoomCharge() + "\n" +
                       "Service Charge: $" + getServiceCharge() + "\n" +
                       "TOTAL BILL: $" + generateBill() + "\n" +
                       "\n=== FEEDBACK ===\n" +
                       "Rating: " + getRating() + "/5\n" +
                       "Comments: " + getComments();
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

    private static String getValidPhone(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("❌ Error: Phone number cannot be empty.");
                continue;
            }
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                System.out.println("❌ Error: Phone number must be exactly 10 digits.");
                continue;
            }
            return phone;
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

    private static int getValidRating(Scanner scanner) {
        while (true) {
            System.out.print("Rating (1-5): ");
            try {
                int rating = scanner.nextInt();
                scanner.nextLine();
                if (rating < 1 || rating > 5) {
                    System.out.println("❌ Error: Rating must be between 1 and 5.");
                    continue;
                }
                return rating;
            } catch (Exception e) {
                System.out.println("❌ Error: Please enter a valid rating.");
                scanner.nextLine();
            }
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
                System.out.println("        HOTEL RESERVATION SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Hotel Information
                System.out.println("\n[HOTEL INFORMATION]");
                String hotelName = getValidString(scanner, "Hotel Name: ");
                String address = getValidString(scanner, "Address: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                String email = getValidEmail(scanner, "Email: ");
                
                // Room Information
                System.out.println("\n[ROOM INFORMATION]");
                String roomNumber = getValidString(scanner, "Room Number: ");
                String roomType = getValidString(scanner, "Room Type: ");
                double pricePerNight = getValidDouble(scanner, "Price Per Night ($): ");
                
                // Customer Information
                System.out.println("\n[CUSTOMER INFORMATION]");
                String customerName = getValidString(scanner, "Customer Name: ");
                String customerEmail = getValidEmail(scanner, "Customer Email: ");
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                
                // Booking Information
                System.out.println("\n[BOOKING INFORMATION]");
                String bookingDate = getValidString(scanner, "Booking Date: ");
                String checkInDate = getValidString(scanner, "Check-in Date: ");
                String checkOutDate = getValidString(scanner, "Check-out Date: ");
                
                // Service Information
                System.out.println("\n[SERVICE INFORMATION]");
                String serviceName = getValidString(scanner, "Service Name: ");
                double serviceCost = getValidDouble(scanner, "Service Cost ($): ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                String paymentMethod = getValidString(scanner, "Payment Method: ");
                String paymentDate = getValidString(scanner, "Payment Date: ");
                
                // Bill Information
                System.out.println("\n[BILL INFORMATION]");
                double roomCharge = getValidDouble(scanner, "Room Charge ($): ");
                double serviceCharge = getValidDouble(scanner, "Service Charge ($): ");
                double totalBill = roomCharge + serviceCharge;
                
                // Feedback Information
                System.out.println("\n[FEEDBACK INFORMATION]");
                int rating = getValidRating(scanner);
                String comments = getValidString(scanner, "Comments: ");
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing reservation record...");
                System.out.println("-".repeat(50));
                
                ReservationRecord record = new ReservationRecord(id, currentDate, currentDate, hotelName, address, phoneNumber, email, roomNumber, roomType, pricePerNight, customerName, customerEmail, contactNumber, bookingDate, checkInDate, checkOutDate, serviceName, serviceCost, paymentMethod, paymentDate, roomCharge, serviceCharge, totalBill, rating, comments);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;
                }
                
            } catch (HotelDataException e) {
                System.out.println("\n❌ Hotel Data Error: " + e.getMessage());
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