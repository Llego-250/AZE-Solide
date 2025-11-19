package Q8;

import java.util.Date;
import java.util.Scanner;

class LibraryDataException extends Exception {
    public LibraryDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws LibraryDataException {
        if (id <= 0) throw new LibraryDataException("ID must be > 0");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Library extends Entity {
    private String libraryName, location, phoneNumber;
    
    public Library(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber) throws LibraryDataException {
        super(id, createdDate, updatedDate);
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) throw new LibraryDataException("Phone must be 10 digits");
        this.libraryName = libraryName; this.location = location; this.phoneNumber = phoneNumber;
    }
    
    public String getLibraryName() { return libraryName; }
    public String getLocation() { return location; }
    public String getPhoneNumber() { return phoneNumber; }
}

class Section extends Library {
    private String sectionName, sectionCode;
    
    public Section(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber);
        if (sectionCode == null || sectionCode.length() < 3) throw new LibraryDataException("Section code must be >= 3 chars");
        this.sectionName = sectionName; this.sectionCode = sectionCode;
    }
    
    public String getSectionName() { return sectionName; }
    public String getSectionCode() { return sectionCode; }
}

class Book extends Section {
    private String title, author, ISBN;
    
    public Book(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode);
        if (ISBN == null || ISBN.length() < 10) throw new LibraryDataException("ISBN must be >= 10 chars");
        this.title = title; this.author = author; this.ISBN = ISBN;
    }
    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
}

class Member extends Book {
    private String memberName, contactNumber;
    private int memberId;
    
    public Member(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN, String memberName, int memberId, String contactNumber) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN);
        if (memberId <= 0) throw new LibraryDataException("Member ID must be > 0");
        this.memberName = memberName; this.memberId = memberId; this.contactNumber = contactNumber;
    }
    
    public String getMemberName() { return memberName; }
    public int getMemberId() { return memberId; }
    public String getContactNumber() { return contactNumber; }
}

class Borrow extends Member {
    private String borrowDate, returnDate;
    
    public Borrow(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN, String memberName, int memberId, String contactNumber, String borrowDate, String returnDate) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN, memberName, memberId, contactNumber);
        if (borrowDate == null || borrowDate.trim().isEmpty()) throw new LibraryDataException("Borrow date cannot be empty");
        if (returnDate == null || returnDate.trim().isEmpty()) throw new LibraryDataException("Return date cannot be empty");
        this.borrowDate = borrowDate; this.returnDate = returnDate;
    }
    
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }
}

class Fine extends Borrow {
    private double fineAmount;
    private int daysLate;
    
    public Fine(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN, String memberName, int memberId, String contactNumber, String borrowDate, String returnDate, double fineAmount, int daysLate) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN, memberName, memberId, contactNumber, borrowDate, returnDate);
        if (fineAmount < 0) throw new LibraryDataException("Fine amount must be >= 0");
        this.fineAmount = fineAmount; this.daysLate = daysLate;
    }
    
    public double getFineAmount() { return fineAmount; }
    public int getDaysLate() { return daysLate; }
}

class Payment extends Fine {
    private String paymentDate, paymentMode;
    
    public Payment(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN, String memberName, int memberId, String contactNumber, String borrowDate, String returnDate, double fineAmount, int daysLate, String paymentDate, String paymentMode) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN, memberName, memberId, contactNumber, borrowDate, returnDate, fineAmount, daysLate);
        if (paymentDate == null || paymentDate.trim().isEmpty()) throw new LibraryDataException("Payment date cannot be empty");
        if (paymentMode == null || paymentMode.trim().isEmpty()) throw new LibraryDataException("Payment mode cannot be empty");
        this.paymentDate = paymentDate; this.paymentMode = paymentMode;
    }
    
    public String getPaymentDate() { return paymentDate; }
    public String getPaymentMode() { return paymentMode; }
}

class Record extends Payment {
    private double totalFine;
    
    public Record(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN, String memberName, int memberId, String contactNumber, String borrowDate, String returnDate, double fineAmount, int daysLate, String paymentDate, String paymentMode, double totalFine) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN, memberName, memberId, contactNumber, borrowDate, returnDate, fineAmount, daysLate, paymentDate, paymentMode);
        if (totalFine <= 0) throw new LibraryDataException("Total fine must be > 0");
        this.totalFine = totalFine;
    }
    
    public double getTotalFine() { return totalFine; }
}

public final class LibraryRecord extends Record {
    
    public LibraryRecord(int id, Date createdDate, Date updatedDate, String libraryName, String location, String phoneNumber, String sectionName, String sectionCode, String title, String author, String ISBN, String memberName, int memberId, String contactNumber, String borrowDate, String returnDate, double fineAmount, int daysLate, String paymentDate, String paymentMode, double totalFine) throws LibraryDataException {
        super(id, createdDate, updatedDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN, memberName, memberId, contactNumber, borrowDate, returnDate, fineAmount, daysLate, paymentDate, paymentMode, totalFine);
    }
    
    public double calculateFine() {
        return getFineAmount() * getDaysLate();
    }
    
    public void displayDetails() {
        String output = "\n============ LIBRARY RECORD ===========\n" +
                       "Library: " + getLibraryName() + "\n" +
                       "Location: " + getLocation() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Section: " + getSectionName() + " (" + getSectionCode() + ")\n" +
                       "Book: " + getTitle() + "\n" +
                       "Author: " + getAuthor() + "\n" +
                       "ISBN: " + getISBN() + "\n" +
                       "Member: " + getMemberName() + ", ID: " + getMemberId() + "\n" +
                       "Contact: " + getContactNumber() + "\n" +
                       "Borrow Date: " + getBorrowDate() + "\n" +
                       "Return Date: " + getReturnDate() + "\n" +
                       "Days Late: " + getDaysLate() + "\n" +
                       "Payment Date: " + getPaymentDate() + "\n" +
                       "Payment Mode: " + getPaymentMode() + "\n" +
                       "\n=== FINE BREAKDOWN ===\n" +
                       "Fine per Day: $" + getFineAmount() + "\n" +
                       "Total Fine: $" + getTotalFine() + "\n" +
                       "CALCULATED FINE: $" + calculateFine();
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
                System.out.println("        LIBRARY MANAGEMENT SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Library Information
                System.out.println("\n[LIBRARY INFORMATION]");
                String libraryName = getValidString(scanner, "Library Name: ");
                String location = getValidString(scanner, "Location: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                
                // Section Information
                System.out.println("\n[SECTION INFORMATION]");
                String sectionName = getValidString(scanner, "Section Name: ");
                String sectionCode = getValidString(scanner, "Section Code: ");
                
                // Book Information
                System.out.println("\n[BOOK INFORMATION]");
                String title = getValidString(scanner, "Book Title: ");
                String author = getValidString(scanner, "Author: ");
                String ISBN = getValidString(scanner, "ISBN: ");
                
                // Member Information
                System.out.println("\n[MEMBER INFORMATION]");
                String memberName = getValidString(scanner, "Member Name: ");
                int memberId = getValidInt(scanner, "Member ID: ");
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                
                // Borrow Information
                System.out.println("\n[BORROW INFORMATION]");
                String borrowDate = getValidString(scanner, "Borrow Date: ");
                String returnDate = getValidString(scanner, "Return Date: ");
                
                // Fine Information
                System.out.println("\n[FINE INFORMATION]");
                double fineAmount = getValidDouble(scanner, "Fine Amount per Day ($): ");
                int daysLate = getValidInt(scanner, "Days Late: ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                String paymentDate = getValidString(scanner, "Payment Date: ");
                String paymentMode = getValidString(scanner, "Payment Mode: ");
                double totalFine = fineAmount * daysLate;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing library record...");
                System.out.println("-".repeat(50));
                
                LibraryRecord record = new LibraryRecord(id, currentDate, currentDate, libraryName, location, phoneNumber, sectionName, sectionCode, title, author, ISBN, memberName, memberId, contactNumber, borrowDate, returnDate, fineAmount, daysLate, paymentDate, paymentMode, totalFine);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Library Management System!");
                    break;
                }
                
            } catch (LibraryDataException e) {
                System.out.println("\n❌ Library Data Error: " + e.getMessage());
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