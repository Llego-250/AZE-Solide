package Q6;

import java.util.Date;
import java.util.Scanner;

class BankingDataException extends Exception {
    public BankingDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws BankingDataException {
        if (id <= 0) throw new BankingDataException("ID must be > 0");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Bank extends Entity {
    private String bankName, branchCode, address;
    
    public Bank(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address) throws BankingDataException {
        super(id, createdDate, updatedDate);
        if (branchCode == null || branchCode.length() < 3) throw new BankingDataException("Branch code must be >= 3 chars");
        this.bankName = bankName; this.branchCode = branchCode; this.address = address;
    }
    
    public String getBankName() { return bankName; }
    public String getBranchCode() { return branchCode; }
    public String getAddress() { return address; }
}

class Account extends Bank {
    private String accountNumber, accountType;
    private double balance;
    
    public Account(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address);
        if (balance < 0) throw new BankingDataException("Balance must be >= 0");
        this.accountNumber = accountNumber; this.accountType = accountType; this.balance = balance;
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
}

class Customer extends Account {
    private String customerName, email, phoneNumber;
    
    public Customer(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance);
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new BankingDataException("Invalid email");
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) throw new BankingDataException("Phone must be 10 digits");
        this.customerName = customerName; this.email = email; this.phoneNumber = phoneNumber;
    }
    
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
}

class Transaction extends Customer {
    private String transactionId, transactionType;
    private double amount;
    
    public Transaction(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber, String transactionId, String transactionType, double amount) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber);
        if (amount <= 0) throw new BankingDataException("Amount must be > 0");
        this.transactionId = transactionId; this.transactionType = transactionType; this.amount = amount;
    }
    
    public String getTransactionId() { return transactionId; }
    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
}

class Deposit extends Transaction {
    private double depositAmount;
    private String depositDate;
    
    public Deposit(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber, String transactionId, String transactionType, double amount, double depositAmount, String depositDate) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber, transactionId, transactionType, amount);
        if (depositAmount <= 0) throw new BankingDataException("Deposit amount must be > 0");
        this.depositAmount = depositAmount; this.depositDate = depositDate;
    }
    
    public double getDepositAmount() { return depositAmount; }
    public String getDepositDate() { return depositDate; }
}

class Withdrawal extends Deposit {
    private double withdrawalAmount;
    private String withdrawalDate;
    
    public Withdrawal(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber, String transactionId, String transactionType, double amount, double depositAmount, String depositDate, double withdrawalAmount, String withdrawalDate) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber, transactionId, transactionType, amount, depositAmount, depositDate);
        if (withdrawalAmount <= 0) throw new BankingDataException("Withdrawal amount must be > 0");
        this.withdrawalAmount = withdrawalAmount; this.withdrawalDate = withdrawalDate;
    }
    
    public double getWithdrawalAmount() { return withdrawalAmount; }
    public String getWithdrawalDate() { return withdrawalDate; }
}

class Loan extends Withdrawal {
    private double loanAmount, interestRate;
    private int duration;
    
    public Loan(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber, String transactionId, String transactionType, double amount, double depositAmount, String depositDate, double withdrawalAmount, String withdrawalDate, double loanAmount, double interestRate, int duration) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber, transactionId, transactionType, amount, depositAmount, depositDate, withdrawalAmount, withdrawalDate);
        if (loanAmount <= 0 || interestRate <= 0 || duration <= 0) throw new BankingDataException("Loan details must be > 0");
        this.loanAmount = loanAmount; this.interestRate = interestRate; this.duration = duration;
    }
    
    public double getLoanAmount() { return loanAmount; }
    public double getInterestRate() { return interestRate; }
    public int getDuration() { return duration; }
}

class Payment extends Loan {
    private double paymentAmount;
    private String paymentDate;
    
    public Payment(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber, String transactionId, String transactionType, double amount, double depositAmount, String depositDate, double withdrawalAmount, String withdrawalDate, double loanAmount, double interestRate, int duration, double paymentAmount, String paymentDate) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber, transactionId, transactionType, amount, depositAmount, depositDate, withdrawalAmount, withdrawalDate, loanAmount, interestRate, duration);
        if (paymentAmount <= 0) throw new BankingDataException("Payment amount must be > 0");
        this.paymentAmount = paymentAmount; this.paymentDate = paymentDate;
    }
    
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentDate() { return paymentDate; }
}

public final class AccountRecord extends Payment {
    
    public AccountRecord(int id, Date createdDate, Date updatedDate, String bankName, String branchCode, String address, String accountNumber, String accountType, double balance, String customerName, String email, String phoneNumber, String transactionId, String transactionType, double amount, double depositAmount, String depositDate, double withdrawalAmount, String withdrawalDate, double loanAmount, double interestRate, int duration, double paymentAmount, String paymentDate) throws BankingDataException {
        super(id, createdDate, updatedDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber, transactionId, transactionType, amount, depositAmount, depositDate, withdrawalAmount, withdrawalDate, loanAmount, interestRate, duration, paymentAmount, paymentDate);
    }
    
    public double calculateInterest() {
        return (getLoanAmount() * getInterestRate() * getDuration()) / 100;
    }
    
    public void displayDetails() {
        String output = "\n============ BANKING ACCOUNT RECORD ===========\n" +
                       "Bank: " + getBankName() + "\n" +
                       "Branch Code: " + getBranchCode() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Account: " + getAccountNumber() + " (" + getAccountType() + ")\n" +
                       "Balance: $" + getBalance() + "\n" +
                       "Customer: " + getCustomerName() + "\n" +
                       "Email: " + getEmail() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Transaction ID: " + getTransactionId() + "\n" +
                       "Transaction Type: " + getTransactionType() + "\n" +
                       "Amount: $" + getAmount() + "\n" +
                       "Deposit: $" + getDepositAmount() + " on " + getDepositDate() + "\n" +
                       "Withdrawal: $" + getWithdrawalAmount() + " on " + getWithdrawalDate() + "\n" +
                       "\n=== LOAN DETAILS ===\n" +
                       "Loan Amount: $" + getLoanAmount() + "\n" +
                       "Interest Rate: " + getInterestRate() + "%\n" +
                       "Duration: " + getDuration() + " years\n" +
                       "Calculated Interest: $" + calculateInterest() + "\n" +
                       "Payment: $" + getPaymentAmount() + " on " + getPaymentDate();
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
                System.out.println("        BANKING SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Bank Information
                System.out.println("\n[BANK INFORMATION]");
                String bankName = getValidString(scanner, "Bank Name: ");
                String branchCode = getValidString(scanner, "Branch Code: ");
                String address = getValidString(scanner, "Address: ");
                
                // Account Information
                System.out.println("\n[ACCOUNT INFORMATION]");
                String accountNumber = getValidString(scanner, "Account Number: ");
                String accountType = getValidString(scanner, "Account Type: ");
                double balance = getValidDouble(scanner, "Balance ($): ");
                
                // Customer Information
                System.out.println("\n[CUSTOMER INFORMATION]");
                String customerName = getValidString(scanner, "Customer Name: ");
                String email = getValidEmail(scanner, "Email: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                
                // Transaction Information
                System.out.println("\n[TRANSACTION INFORMATION]");
                String transactionId = getValidString(scanner, "Transaction ID: ");
                String transactionType = getValidString(scanner, "Transaction Type: ");
                double amount = getValidDouble(scanner, "Amount ($): ");
                
                // Deposit Information
                System.out.println("\n[DEPOSIT INFORMATION]");
                double depositAmount = getValidDouble(scanner, "Deposit Amount ($): ");
                String depositDate = getValidString(scanner, "Deposit Date: ");
                
                // Withdrawal Information
                System.out.println("\n[WITHDRAWAL INFORMATION]");
                double withdrawalAmount = getValidDouble(scanner, "Withdrawal Amount ($): ");
                String withdrawalDate = getValidString(scanner, "Withdrawal Date: ");
                
                // Loan Information
                System.out.println("\n[LOAN INFORMATION]");
                double loanAmount = getValidDouble(scanner, "Loan Amount ($): ");
                double interestRate = getValidDouble(scanner, "Interest Rate (%): ");
                int duration = getValidInt(scanner, "Duration (years): ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                double paymentAmount = getValidDouble(scanner, "Payment Amount ($): ");
                String paymentDate = getValidString(scanner, "Payment Date: ");
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing account record...");
                System.out.println("-".repeat(50));
                
                AccountRecord record = new AccountRecord(id, currentDate, currentDate, bankName, branchCode, address, accountNumber, accountType, balance, customerName, email, phoneNumber, transactionId, transactionType, amount, depositAmount, depositDate, withdrawalAmount, withdrawalDate, loanAmount, interestRate, duration, paymentAmount, paymentDate);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Banking System!");
                    break;
                }
                
            } catch (BankingDataException e) {
                System.out.println("\n❌ Banking Data Error: " + e.getMessage());
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