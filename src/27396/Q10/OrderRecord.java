package Q10;

import java.util.Date;
import java.util.Scanner;

class ShoppingDataException extends Exception {
    public ShoppingDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws ShoppingDataException {
        if (id <= 0) throw new ShoppingDataException("ID must be > 0");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Store extends Entity {
    private String storeName, address, email;
    
    public Store(int id, Date createdDate, Date updatedDate, String storeName, String address, String email) throws ShoppingDataException {
        super(id, createdDate, updatedDate);
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new ShoppingDataException("Invalid email");
        this.storeName = storeName; this.address = address; this.email = email;
    }
    
    public String getStoreName() { return storeName; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
}

class Category extends Store {
    private String categoryName, categoryCode;
    
    public Category(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email);
        if (categoryCode == null || categoryCode.length() < 3) throw new ShoppingDataException("Category code must be >= 3 chars");
        this.categoryName = categoryName; this.categoryCode = categoryCode;
    }
    
    public String getCategoryName() { return categoryName; }
    public String getCategoryCode() { return categoryCode; }
}

class Product extends Category {
    private String productName, productCode;
    private double price;
    
    public Product(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode);
        if (price <= 0) throw new ShoppingDataException("Price must be > 0");
        this.productName = productName; this.productCode = productCode; this.price = price;
    }
    
    public String getProductName() { return productName; }
    public String getProductCode() { return productCode; }
    public double getPrice() { return price; }
}

class Customer extends Product {
    private String customerName, contactNumber, customerAddress;
    
    public Customer(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price, String customerName, String contactNumber, String customerAddress) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price);
        if (customerName == null || customerName.trim().isEmpty()) throw new ShoppingDataException("Customer name cannot be empty");
        if (contactNumber == null || contactNumber.trim().isEmpty()) throw new ShoppingDataException("Contact number cannot be empty");
        if (customerAddress == null || customerAddress.trim().isEmpty()) throw new ShoppingDataException("Customer address cannot be empty");
        this.customerName = customerName; this.contactNumber = contactNumber; this.customerAddress = customerAddress;
    }
    
    public String getCustomerName() { return customerName; }
    public String getContactNumber() { return contactNumber; }
    public String getCustomerAddress() { return customerAddress; }
}

class Order extends Customer {
    private String orderDate, orderId;
    
    public Order(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price, String customerName, String contactNumber, String customerAddress, String orderDate, String orderId) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price, customerName, contactNumber, customerAddress);
        if (orderDate == null || orderDate.trim().isEmpty()) throw new ShoppingDataException("Order date cannot be empty");
        if (orderId == null || orderId.trim().isEmpty()) throw new ShoppingDataException("Order ID cannot be empty");
        this.orderDate = orderDate; this.orderId = orderId;
    }
    
    public String getOrderDate() { return orderDate; }
    public String getOrderId() { return orderId; }
}

class Payment extends Order {
    private String paymentMethod, paymentStatus;
    
    public Payment(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price, String customerName, String contactNumber, String customerAddress, String orderDate, String orderId, String paymentMethod, String paymentStatus) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price, customerName, contactNumber, customerAddress, orderDate, orderId);
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) throw new ShoppingDataException("Payment method cannot be empty");
        if (paymentStatus == null || paymentStatus.trim().isEmpty()) throw new ShoppingDataException("Payment status cannot be empty");
        this.paymentMethod = paymentMethod; this.paymentStatus = paymentStatus;
    }
    
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
}

class Shipping extends Payment {
    private String shippingAddress;
    private double shippingCost;
    
    public Shipping(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price, String customerName, String contactNumber, String customerAddress, String orderDate, String orderId, String paymentMethod, String paymentStatus, String shippingAddress, double shippingCost) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price, customerName, contactNumber, customerAddress, orderDate, orderId, paymentMethod, paymentStatus);
        if (shippingCost < 0) throw new ShoppingDataException("Shipping cost must be >= 0");
        this.shippingAddress = shippingAddress; this.shippingCost = shippingCost;
    }
    
    public String getShippingAddress() { return shippingAddress; }
    public double getShippingCost() { return shippingCost; }
}

class Invoice extends Shipping {
    private double totalAmount;
    
    public Invoice(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price, String customerName, String contactNumber, String customerAddress, String orderDate, String orderId, String paymentMethod, String paymentStatus, String shippingAddress, double shippingCost, double totalAmount) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price, customerName, contactNumber, customerAddress, orderDate, orderId, paymentMethod, paymentStatus, shippingAddress, shippingCost);
        if (totalAmount <= 0) throw new ShoppingDataException("Total amount must be > 0");
        this.totalAmount = totalAmount;
    }
    
    public double getTotalAmount() { return totalAmount; }
}

public final class OrderRecord extends Invoice {
    
    public OrderRecord(int id, Date createdDate, Date updatedDate, String storeName, String address, String email, String categoryName, String categoryCode, String productName, String productCode, double price, String customerName, String contactNumber, String customerAddress, String orderDate, String orderId, String paymentMethod, String paymentStatus, String shippingAddress, double shippingCost, double totalAmount) throws ShoppingDataException {
        super(id, createdDate, updatedDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price, customerName, contactNumber, customerAddress, orderDate, orderId, paymentMethod, paymentStatus, shippingAddress, shippingCost, totalAmount);
    }
    
    public double calculateTotalAmount() {
        return getPrice() + getShippingCost();
    }
    
    public void displayDetails() {
        String output = "\n============ ONLINE SHOPPING ORDER RECORD ===========\n" +
                       "Store: " + getStoreName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Email: " + getEmail() + "\n" +
                       "Category: " + getCategoryName() + " (" + getCategoryCode() + ")\n" +
                       "Product: " + getProductName() + " - " + getProductCode() + "\n" +
                       "Price: $" + getPrice() + "\n" +
                       "Customer: " + getCustomerName() + "\n" +
                       "Contact: " + getContactNumber() + "\n" +
                       "Customer Address: " + getCustomerAddress() + "\n" +
                       "Order ID: " + getOrderId() + "\n" +
                       "Order Date: " + getOrderDate() + "\n" +
                       "Payment Method: " + getPaymentMethod() + "\n" +
                       "Payment Status: " + getPaymentStatus() + "\n" +
                       "Shipping Address: " + getShippingAddress() + "\n" +
                       "\n=== ORDER BREAKDOWN ===\n" +
                       "Product Price: $" + getPrice() + "\n" +
                       "Shipping Cost: $" + getShippingCost() + "\n" +
                       "Total Amount: $" + getTotalAmount() + "\n" +
                       "CALCULATED TOTAL: $" + calculateTotalAmount();
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
                System.out.println("        ONLINE SHOPPING SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Store Information
                System.out.println("\n[STORE INFORMATION]");
                String storeName = getValidString(scanner, "Store Name: ");
                String address = getValidString(scanner, "Store Address: ");
                String email = getValidEmail(scanner, "Store Email: ");
                
                // Category Information
                System.out.println("\n[CATEGORY INFORMATION]");
                String categoryName = getValidString(scanner, "Category Name: ");
                String categoryCode = getValidString(scanner, "Category Code: ");
                
                // Product Information
                System.out.println("\n[PRODUCT INFORMATION]");
                String productName = getValidString(scanner, "Product Name: ");
                String productCode = getValidString(scanner, "Product Code: ");
                double price = getValidDouble(scanner, "Price ($): ");
                
                // Customer Information
                System.out.println("\n[CUSTOMER INFORMATION]");
                String customerName = getValidString(scanner, "Customer Name: ");
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                String customerAddress = getValidString(scanner, "Customer Address: ");
                
                // Order Information
                System.out.println("\n[ORDER INFORMATION]");
                String orderDate = getValidString(scanner, "Order Date: ");
                String orderId = getValidString(scanner, "Order ID: ");
                
                // Payment Information
                System.out.println("\n[PAYMENT INFORMATION]");
                String paymentMethod = getValidString(scanner, "Payment Method: ");
                String paymentStatus = getValidString(scanner, "Payment Status: ");
                
                // Shipping Information
                System.out.println("\n[SHIPPING INFORMATION]");
                String shippingAddress = getValidString(scanner, "Shipping Address: ");
                double shippingCost = getValidDouble(scanner, "Shipping Cost ($): ");
                double totalAmount = price + shippingCost;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing order record...");
                System.out.println("-".repeat(50));
                
                OrderRecord record = new OrderRecord(id, currentDate, currentDate, storeName, address, email, categoryName, categoryCode, productName, productCode, price, customerName, contactNumber, customerAddress, orderDate, orderId, paymentMethod, paymentStatus, shippingAddress, shippingCost, totalAmount);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Online Shopping System!");
                    break;
                }
                
            } catch (ShoppingDataException e) {
                System.out.println("\n❌ Shopping Data Error: " + e.getMessage());
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