package Q7;

import java.util.Date;
import java.util.Scanner;

class RealEstateDataException extends Exception {
    public RealEstateDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws RealEstateDataException {
        if (id <= 0) throw new RealEstateDataException("ID must be > 0");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Agency extends Entity {
    private String agencyName, location, phoneNumber;
    
    public Agency(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber) throws RealEstateDataException {
        super(id, createdDate, updatedDate);
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) throw new RealEstateDataException("Phone must be 10 digits");
        this.agencyName = agencyName; this.location = location; this.phoneNumber = phoneNumber;
    }
    
    public String getAgencyName() { return agencyName; }
    public String getLocation() { return location; }
    public String getPhoneNumber() { return phoneNumber; }
}

class Agent extends Agency {
    private String agentName, email, licenseNumber;
    
    public Agent(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber);
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new RealEstateDataException("Invalid email");
        this.agentName = agentName; this.email = email; this.licenseNumber = licenseNumber;
    }
    
    public String getAgentName() { return agentName; }
    public String getEmail() { return email; }
    public String getLicenseNumber() { return licenseNumber; }
}

class Property extends Agent {
    private String propertyCode, propertyType;
    private double price;
    
    public Property(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber);
        if (price <= 0) throw new RealEstateDataException("Price must be > 0");
        this.propertyCode = propertyCode; this.propertyType = propertyType; this.price = price;
    }
    
    public String getPropertyCode() { return propertyCode; }
    public String getPropertyType() { return propertyType; }
    public double getPrice() { return price; }
}

class Seller extends Property {
    private String sellerName, contactNumber;
    
    public Seller(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price, String sellerName, String contactNumber) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price);
        if (sellerName == null || sellerName.trim().isEmpty()) throw new RealEstateDataException("Seller name cannot be empty");
        this.sellerName = sellerName; this.contactNumber = contactNumber;
    }
    
    public String getSellerName() { return sellerName; }
    public String getContactNumber() { return contactNumber; }
}

class Buyer extends Seller {
    private String buyerName, buyerEmail;
    
    public Buyer(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price, String sellerName, String contactNumber, String buyerName, String buyerEmail) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price, sellerName, contactNumber);
        if (buyerEmail == null || !buyerEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new RealEstateDataException("Invalid buyer email");
        this.buyerName = buyerName; this.buyerEmail = buyerEmail;
    }
    
    public String getBuyerName() { return buyerName; }
    public String getBuyerEmail() { return buyerEmail; }
}

class Agreement extends Buyer {
    private String agreementDate, terms;
    
    public Agreement(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price, String sellerName, String contactNumber, String buyerName, String buyerEmail, String agreementDate, String terms) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price, sellerName, contactNumber, buyerName, buyerEmail);
        if (agreementDate == null || agreementDate.trim().isEmpty()) throw new RealEstateDataException("Agreement date cannot be empty");
        if (terms == null || terms.trim().isEmpty()) throw new RealEstateDataException("Terms cannot be empty");
        this.agreementDate = agreementDate; this.terms = terms;
    }
    
    public String getAgreementDate() { return agreementDate; }
    public String getTerms() { return terms; }
}

class Payment extends Agreement {
    private double paymentAmount;
    private String paymentDate;
    
    public Payment(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price, String sellerName, String contactNumber, String buyerName, String buyerEmail, String agreementDate, String terms, double paymentAmount, String paymentDate) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price, sellerName, contactNumber, buyerName, buyerEmail, agreementDate, terms);
        if (paymentAmount <= 0) throw new RealEstateDataException("Payment amount must be > 0");
        this.paymentAmount = paymentAmount; this.paymentDate = paymentDate;
    }
    
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentDate() { return paymentDate; }
}

class Commission extends Payment {
    private double commissionRate, commissionAmount;
    
    public Commission(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price, String sellerName, String contactNumber, String buyerName, String buyerEmail, String agreementDate, String terms, double paymentAmount, String paymentDate, double commissionRate, double commissionAmount) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price, sellerName, contactNumber, buyerName, buyerEmail, agreementDate, terms, paymentAmount, paymentDate);
        if (commissionRate < 0) throw new RealEstateDataException("Commission rate must be >= 0");
        this.commissionRate = commissionRate; this.commissionAmount = commissionAmount;
    }
    
    public double getCommissionRate() { return commissionRate; }
    public double getCommissionAmount() { return commissionAmount; }
}

public final class RealEstateRecord extends Commission {
    
    public RealEstateRecord(int id, Date createdDate, Date updatedDate, String agencyName, String location, String phoneNumber, String agentName, String email, String licenseNumber, String propertyCode, String propertyType, double price, String sellerName, String contactNumber, String buyerName, String buyerEmail, String agreementDate, String terms, double paymentAmount, String paymentDate, double commissionRate, double commissionAmount) throws RealEstateDataException {
        super(id, createdDate, updatedDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price, sellerName, contactNumber, buyerName, buyerEmail, agreementDate, terms, paymentAmount, paymentDate, commissionRate, commissionAmount);
    }
    
    public double calculateCommission() {
        return (getPrice() * getCommissionRate()) / 100;
    }
    
    public void displayDetails() {
        String output = "\n============ REAL ESTATE RECORD ===========\n" +
                       "Agency: " + getAgencyName() + "\n" +
                       "Location: " + getLocation() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Agent: " + getAgentName() + "\n" +
                       "Agent Email: " + getEmail() + "\n" +
                       "License: " + getLicenseNumber() + "\n" +
                       "Property: " + getPropertyCode() + " (" + getPropertyType() + ")\n" +
                       "Price: $" + getPrice() + "\n" +
                       "Seller: " + getSellerName() + "\n" +
                       "Seller Contact: " + getContactNumber() + "\n" +
                       "Buyer: " + getBuyerName() + "\n" +
                       "Buyer Email: " + getBuyerEmail() + "\n" +
                       "Agreement Date: " + getAgreementDate() + "\n" +
                       "Terms: " + getTerms() + "\n" +
                       "Payment: $" + getPaymentAmount() + " on " + getPaymentDate() + "\n" +
                       "\n=== COMMISSION BREAKDOWN ===\n" +
                       "Commission Rate: " + getCommissionRate() + "%\n" +
                       "Commission Amount: $" + getCommissionAmount() + "\n" +
                       "CALCULATED COMMISSION: $" + calculateCommission();
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
                System.out.println("        REAL ESTATE MANAGEMENT SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Agency Information
                System.out.println("\n[AGENCY INFORMATION]");
                String agencyName = getValidString(scanner, "Agency Name: ");
                String location = getValidString(scanner, "Location: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                
                // Agent Information
                System.out.println("\n[AGENT INFORMATION]");
                String agentName = getValidString(scanner, "Agent Name: ");
                String email = getValidEmail(scanner, "Agent Email: ");
                String licenseNumber = getValidString(scanner, "License Number: ");
                
                // Property Information
                System.out.println("\n[PROPERTY INFORMATION]");
                String propertyCode = getValidString(scanner, "Property Code: ");
                String propertyType = getValidString(scanner, "Property Type: ");
                double price = getValidDouble(scanner, "Price ($): ");
                
                // Seller Information
                System.out.println("\n[SELLER INFORMATION]");
                String sellerName = getValidString(scanner, "Seller Name: ");
                String contactNumber = getValidPhone(scanner, "Seller Contact (10 digits): ");
                
                // Buyer Information
                System.out.println("\n[BUYER INFORMATION]");
                String buyerName = getValidString(scanner, "Buyer Name: ");
                String buyerEmail = getValidEmail(scanner, "Buyer Email: ");
                
                // Agreement Information
                System.out.println("\n[AGREEMENT INFORMATION]");
                String agreementDate = getValidString(scanner, "Agreement Date: ");
                String terms = getValidString(scanner, "Terms: ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                double paymentAmount = getValidDouble(scanner, "Payment Amount ($): ");
                String paymentDate = getValidString(scanner, "Payment Date: ");
                
                // Commission Information
                System.out.println("\n[COMMISSION INFORMATION]");
                double commissionRate = getValidDouble(scanner, "Commission Rate (%): ");
                double commissionAmount = (price * commissionRate) / 100;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing real estate record...");
                System.out.println("-".repeat(50));
                
                RealEstateRecord record = new RealEstateRecord(id, currentDate, currentDate, agencyName, location, phoneNumber, agentName, email, licenseNumber, propertyCode, propertyType, price, sellerName, contactNumber, buyerName, buyerEmail, agreementDate, terms, paymentAmount, paymentDate, commissionRate, commissionAmount);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Real Estate Management System!");
                    break;
                }
                
            } catch (RealEstateDataException e) {
                System.out.println("\n❌ Real Estate Data Error: " + e.getMessage());
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