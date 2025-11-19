package Q5;

import java.util.Date;
import java.util.Scanner;

class RentalDataException extends Exception {
    public RentalDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws RentalDataException {
        if (id <= 0) throw new RentalDataException("ID must be > 0");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Company extends Entity {
    private String companyName, address, phoneNumber;
    
    public Company(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber) throws RentalDataException {
        super(id, createdDate, updatedDate);
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) throw new RentalDataException("Phone must be 10 digits");
        this.companyName = companyName; this.address = address; this.phoneNumber = phoneNumber;
    }
    
    public String getCompanyName() { return companyName; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
}

class Branch extends Company {
    private String branchName, locationCode;
    
    public Branch(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber);
        if (locationCode == null || locationCode.length() < 3) throw new RentalDataException("Location code must be >= 3 chars");
        this.branchName = branchName; this.locationCode = locationCode;
    }
    
    public String getBranchName() { return branchName; }
    public String getLocationCode() { return locationCode; }
}

class Vehicle extends Branch {
    private String vehicleType, registrationNumber;
    private double dailyRate;
    
    public Vehicle(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode);
        if (dailyRate <= 0) throw new RentalDataException("Daily rate must be > 0");
        this.vehicleType = vehicleType; this.registrationNumber = registrationNumber; this.dailyRate = dailyRate;
    }
    
    public String getVehicleType() { return vehicleType; }
    public String getRegistrationNumber() { return registrationNumber; }
    public double getDailyRate() { return dailyRate; }
}

class Customer extends Vehicle {
    private String customerName, licenseNumber, contactNumber;
    
    public Customer(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate, String customerName, String licenseNumber, String contactNumber) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate);
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) throw new RentalDataException("License cannot be empty");
        this.customerName = customerName; this.licenseNumber = licenseNumber; this.contactNumber = contactNumber;
    }
    
    public String getCustomerName() { return customerName; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getContactNumber() { return contactNumber; }
}

class Rental extends Customer {
    private String rentalDate, returnDate;
    private int rentalDays;
    
    public Rental(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate, String customerName, String licenseNumber, String contactNumber, String rentalDate, String returnDate, int rentalDays) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate, customerName, licenseNumber, contactNumber);
        if (rentalDays <= 0) throw new RentalDataException("Rental days must be > 0");
        this.rentalDate = rentalDate; this.returnDate = returnDate; this.rentalDays = rentalDays;
    }
    
    public String getRentalDate() { return rentalDate; }
    public String getReturnDate() { return returnDate; }
    public int getRentalDays() { return rentalDays; }
}

class Charge extends Rental {
    private double rentalCharge, penaltyCharge;
    
    public Charge(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate, String customerName, String licenseNumber, String contactNumber, String rentalDate, String returnDate, int rentalDays, double rentalCharge, double penaltyCharge) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate, customerName, licenseNumber, contactNumber, rentalDate, returnDate, rentalDays);
        if (rentalCharge < 0 || penaltyCharge < 0) throw new RentalDataException("Charges must be >= 0");
        this.rentalCharge = rentalCharge; this.penaltyCharge = penaltyCharge;
    }
    
    public double getRentalCharge() { return rentalCharge; }
    public double getPenaltyCharge() { return penaltyCharge; }
}

class Payment extends Charge {
    private String paymentMode, transactionId;
    
    public Payment(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate, String customerName, String licenseNumber, String contactNumber, String rentalDate, String returnDate, int rentalDays, double rentalCharge, double penaltyCharge, String paymentMode, String transactionId) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate, customerName, licenseNumber, contactNumber, rentalDate, returnDate, rentalDays, rentalCharge, penaltyCharge);
        if (paymentMode == null || paymentMode.trim().isEmpty()) throw new RentalDataException("Payment mode cannot be empty");
        if (transactionId == null || transactionId.trim().isEmpty()) throw new RentalDataException("Transaction ID cannot be empty");
        this.paymentMode = paymentMode; this.transactionId = transactionId;
    }
    
    public String getPaymentMode() { return paymentMode; }
    public String getTransactionId() { return transactionId; }
}

class Invoice extends Payment {
    private double totalCharge;
    
    public Invoice(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate, String customerName, String licenseNumber, String contactNumber, String rentalDate, String returnDate, int rentalDays, double rentalCharge, double penaltyCharge, String paymentMode, String transactionId, double totalCharge) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate, customerName, licenseNumber, contactNumber, rentalDate, returnDate, rentalDays, rentalCharge, penaltyCharge, paymentMode, transactionId);
        if (totalCharge <= 0) throw new RentalDataException("Total charge must be > 0");
        this.totalCharge = totalCharge;
    }
    
    public double getTotalCharge() { return totalCharge; }
}

public final class RentalRecord extends Invoice {
    
    public RentalRecord(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String branchName, String locationCode, String vehicleType, String registrationNumber, double dailyRate, String customerName, String licenseNumber, String contactNumber, String rentalDate, String returnDate, int rentalDays, double rentalCharge, double penaltyCharge, String paymentMode, String transactionId, double totalCharge) throws RentalDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate, customerName, licenseNumber, contactNumber, rentalDate, returnDate, rentalDays, rentalCharge, penaltyCharge, paymentMode, transactionId, totalCharge);
    }
    
    public double calculateTotalCharge() {
        return getRentalCharge() + getPenaltyCharge();
    }
    
    public void displayDetails() {
        String output = "\n============ VEHICLE RENTAL RECORD ===========\n" +
                       "Company: " + getCompanyName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Branch: " + getBranchName() + " (" + getLocationCode() + ")\n" +
                       "Vehicle: " + getVehicleType() + " - " + getRegistrationNumber() + "\n" +
                       "Daily Rate: $" + getDailyRate() + "\n" +
                       "Customer: " + getCustomerName() + "\n" +
                       "License: " + getLicenseNumber() + "\n" +
                       "Contact: " + getContactNumber() + "\n" +
                       "Rental Period: " + getRentalDate() + " to " + getReturnDate() + "\n" +
                       "Rental Days: " + getRentalDays() + "\n" +
                       "Payment Mode: " + getPaymentMode() + "\n" +
                       "Transaction ID: " + getTransactionId() + "\n" +
                       "\n=== CHARGE BREAKDOWN ===\n" +
                       "Rental Charge: $" + getRentalCharge() + "\n" +
                       "Penalty Charge: $" + getPenaltyCharge() + "\n" +
                       "TOTAL CHARGE: $" + calculateTotalCharge();
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
                System.out.println("        VEHICLE RENTAL SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Company Information
                System.out.println("\n[COMPANY INFORMATION]");
                String companyName = getValidString(scanner, "Company Name: ");
                String address = getValidString(scanner, "Address: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                
                // Branch Information
                System.out.println("\n[BRANCH INFORMATION]");
                String branchName = getValidString(scanner, "Branch Name: ");
                String locationCode = getValidString(scanner, "Location Code: ");
                
                // Vehicle Information
                System.out.println("\n[VEHICLE INFORMATION]");
                String vehicleType = getValidString(scanner, "Vehicle Type: ");
                String registrationNumber = getValidString(scanner, "Registration Number: ");
                double dailyRate = getValidDouble(scanner, "Daily Rate ($): ");
                
                // Customer Information
                System.out.println("\n[CUSTOMER INFORMATION]");
                String customerName = getValidString(scanner, "Customer Name: ");
                String licenseNumber = getValidString(scanner, "License Number: ");
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                
                // Rental Information
                System.out.println("\n[RENTAL INFORMATION]");
                String rentalDate = getValidString(scanner, "Rental Date: ");
                String returnDate = getValidString(scanner, "Return Date: ");
                int rentalDays = getValidInt(scanner, "Rental Days: ");
                
                // Charge Information
                System.out.println("\n[CHARGE INFORMATION]");
                double rentalCharge = getValidDouble(scanner, "Rental Charge ($): ");
                double penaltyCharge = getValidDouble(scanner, "Penalty Charge ($): ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                String paymentMode = getValidString(scanner, "Payment Mode: ");
                String transactionId = getValidString(scanner, "Transaction ID: ");
                double totalCharge = rentalCharge + penaltyCharge;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing rental record...");
                System.out.println("-".repeat(50));
                
                RentalRecord record = new RentalRecord(id, currentDate, currentDate, companyName, address, phoneNumber, branchName, locationCode, vehicleType, registrationNumber, dailyRate, customerName, licenseNumber, contactNumber, rentalDate, returnDate, rentalDays, rentalCharge, penaltyCharge, paymentMode, transactionId, totalCharge);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Vehicle Rental System!");
                    break;
                }
                
            } catch (RentalDataException e) {
                System.out.println("\n❌ Rental Data Error: " + e.getMessage());
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