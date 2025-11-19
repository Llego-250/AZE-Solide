package Q3;

import java.util.Date;
import java.util.Scanner;

class PayrollDataException extends Exception {
    public PayrollDataException(String message) { super(message); }
}

class Entity {
    private int id;
    private Date createdDate, updatedDate;
    
    public Entity(int id, Date createdDate, Date updatedDate) throws PayrollDataException {
        if (id <= 0) throw new PayrollDataException("ID must be > 0");
        if (createdDate == null || updatedDate == null) throw new PayrollDataException("Dates cannot be null");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    
    public int getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }
}

class Company extends Entity {
    private String companyName, address, phoneNumber, email;
    
    public Company(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email) throws PayrollDataException {
        super(id, createdDate, updatedDate);
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) throw new PayrollDataException("Phone must be 10 digits");
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new PayrollDataException("Invalid email");
        this.companyName = companyName; this.address = address; this.phoneNumber = phoneNumber; this.email = email;
    }
    
    public String getCompanyName() { return companyName; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
}

class Department extends Company {
    private String departmentName, departmentCode;
    
    public Department(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email);
        if (departmentCode == null || departmentCode.length() < 3 || !departmentCode.matches("^[a-zA-Z0-9]+$")) throw new PayrollDataException("Code must be alphanumeric, >= 3 chars");
        this.departmentName = departmentName; this.departmentCode = departmentCode;
    }
    
    public String getDepartmentName() { return departmentName; }
    public String getDepartmentCode() { return departmentCode; }
}

class Manager extends Department {
    private String managerName, managerEmail, phone;
    
    public Manager(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode);
        if (managerName == null || managerName.trim().isEmpty()) throw new PayrollDataException("Manager name cannot be empty");
        if (managerEmail == null || !managerEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new PayrollDataException("Invalid manager email");
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) throw new PayrollDataException("Phone must be 10 digits");
        this.managerName = managerName; this.managerEmail = managerEmail; this.phone = phone;
    }
    
    public String getManagerName() { return managerName; }
    public String getManagerEmail() { return managerEmail; }
    public String getPhone() { return phone; }
}

class Employee extends Manager {
    private String employeeName, designation, contactNumber;
    private int employeeId;
    
    public Employee(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone, String employeeName, int employeeId, String designation, String contactNumber) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, phone);
        if (employeeId <= 0) throw new PayrollDataException("Employee ID must be > 0");
        if (contactNumber == null || contactNumber.length() != 10 || !contactNumber.matches("\\d+")) throw new PayrollDataException("Contact must be 10 digits");
        this.employeeName = employeeName; this.employeeId = employeeId; this.designation = designation; this.contactNumber = contactNumber;
    }
    
    public String getEmployeeName() { return employeeName; }
    public int getEmployeeId() { return employeeId; }
    public String getDesignation() { return designation; }
    public String getContactNumber() { return contactNumber; }
}

class Attendance extends Employee {
    private int totalDays, presentDays, leaveDays;
    
    public Attendance(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone, String employeeName, int employeeId, String designation, String contactNumber, int totalDays, int presentDays, int leaveDays) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, phone, employeeName, employeeId, designation, contactNumber);
        if (totalDays < 0 || presentDays < 0 || leaveDays < 0) throw new PayrollDataException("Days must be >= 0");
        if (presentDays > totalDays) throw new PayrollDataException("Present days cannot exceed total days");
        this.totalDays = totalDays; this.presentDays = presentDays; this.leaveDays = leaveDays;
    }
    
    public int getTotalDays() { return totalDays; }
    public int getPresentDays() { return presentDays; }
    public int getLeaveDays() { return leaveDays; }
}

class Allowance extends Attendance {
    private double housingAllowance, transportAllowance;
    
    public Allowance(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone, String employeeName, int employeeId, String designation, String contactNumber, int totalDays, int presentDays, int leaveDays, double housingAllowance, double transportAllowance) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, phone, employeeName, employeeId, designation, contactNumber, totalDays, presentDays, leaveDays);
        if (housingAllowance < 0 || transportAllowance < 0) throw new PayrollDataException("Allowances must be >= 0");
        this.housingAllowance = housingAllowance; this.transportAllowance = transportAllowance;
    }
    
    public double getHousingAllowance() { return housingAllowance; }
    public double getTransportAllowance() { return transportAllowance; }
}

class Deduction extends Allowance {
    private double taxDeduction, loanDeduction;
    
    public Deduction(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone, String employeeName, int employeeId, String designation, String contactNumber, int totalDays, int presentDays, int leaveDays, double housingAllowance, double transportAllowance, double taxDeduction, double loanDeduction) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, phone, employeeName, employeeId, designation, contactNumber, totalDays, presentDays, leaveDays, housingAllowance, transportAllowance);
        if (taxDeduction < 0 || loanDeduction < 0) throw new PayrollDataException("Deductions must be >= 0");
        this.taxDeduction = taxDeduction; this.loanDeduction = loanDeduction;
    }
    
    public double getTaxDeduction() { return taxDeduction; }
    public double getLoanDeduction() { return loanDeduction; }
}

class Salary extends Deduction {
    private double basicSalary, grossSalary, netSalary;
    
    public Salary(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone, String employeeName, int employeeId, String designation, String contactNumber, int totalDays, int presentDays, int leaveDays, double housingAllowance, double transportAllowance, double taxDeduction, double loanDeduction, double basicSalary, double grossSalary, double netSalary) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, phone, employeeName, employeeId, designation, contactNumber, totalDays, presentDays, leaveDays, housingAllowance, transportAllowance, taxDeduction, loanDeduction);
        if (basicSalary <= 0 || grossSalary <= 0 || netSalary <= 0) throw new PayrollDataException("Salaries must be > 0");
        this.basicSalary = basicSalary; this.grossSalary = grossSalary; this.netSalary = netSalary;
    }
    
    public double getBasicSalary() { return basicSalary; }
    public double getGrossSalary() { return grossSalary; }
    public double getNetSalary() { return netSalary; }
}

public final class PayrollRecord extends Salary {
    
    public PayrollRecord(int id, Date createdDate, Date updatedDate, String companyName, String address, String phoneNumber, String email, String departmentName, String departmentCode, String managerName, String managerEmail, String phone, String employeeName, int employeeId, String designation, String contactNumber, int totalDays, int presentDays, int leaveDays, double housingAllowance, double transportAllowance, double taxDeduction, double loanDeduction, double basicSalary, double grossSalary, double netSalary) throws PayrollDataException {
        super(id, createdDate, updatedDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, phone, employeeName, employeeId, designation, contactNumber, totalDays, presentDays, leaveDays, housingAllowance, transportAllowance, taxDeduction, loanDeduction, basicSalary, grossSalary, netSalary);
    }
    
    public double calculateNetSalary() {
        return getBasicSalary() + getHousingAllowance() + getTransportAllowance() - getTaxDeduction() - getLoanDeduction();
    }
    
    public void displayDetails() {
        String output = "\n============ PAYROLL RECORD ===========\n" +
                       "Company: " + getCompanyName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Email: " + getEmail() + "\n" +
                       "Department: " + getDepartmentName() + " (" + getDepartmentCode() + ")\n" +
                       "Manager: " + getManagerName() + "\n" +
                       "Manager Email: " + getManagerEmail() + "\n" +
                       "Employee: " + getEmployeeName() + ", ID: " + getEmployeeId() + ", Designation: " + getDesignation() + "\n" +
                       "Contact: " + getContactNumber() + "\n" +
                       "Attendance: " + getPresentDays() + "/" + getTotalDays() + " days (Leave: " + getLeaveDays() + ")\n" +
                       "\n=== SALARY BREAKDOWN ===\n" +
                       "Basic Salary: $" + getBasicSalary() + "\n" +
                       "Housing Allowance: $" + getHousingAllowance() + "\n" +
                       "Transport Allowance: $" + getTransportAllowance() + "\n" +
                       "Tax Deduction: $" + getTaxDeduction() + "\n" +
                       "Loan Deduction: $" + getLoanDeduction() + "\n" +
                       "NET SALARY: $" + calculateNetSalary();
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
                System.out.println("        EMPLOYEE PAYROLL SYSTEM");
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
                String email = getValidEmail(scanner, "Email: ");
                
                // Department Information
                System.out.println("\n[DEPARTMENT INFORMATION]");
                String departmentName = getValidString(scanner, "Department Name: ");
                String departmentCode = getValidString(scanner, "Department Code: ");
                
                // Manager Information
                System.out.println("\n[MANAGER INFORMATION]");
                String managerName = getValidString(scanner, "Manager Name: ");
                String managerEmail = getValidEmail(scanner, "Manager Email: ");
                String managerPhone = getValidPhone(scanner, "Manager Phone: ");
                
                // Employee Information
                System.out.println("\n[EMPLOYEE INFORMATION]");
                String employeeName = getValidString(scanner, "Employee Name: ");
                int employeeId = getValidInt(scanner, "Employee ID: ");
                String designation = getValidString(scanner, "Designation: ");
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                
                // Attendance Information
                System.out.println("\n[ATTENDANCE INFORMATION]");
                int totalDays = getValidInt(scanner, "Total Days: ");
                int presentDays = getValidInt(scanner, "Present Days: ");
                int leaveDays = getValidInt(scanner, "Leave Days: ");
                
                // Allowance Information
                System.out.println("\n[ALLOWANCE INFORMATION]");
                double housingAllowance = getValidDouble(scanner, "Housing Allowance ($): ");
                double transportAllowance = getValidDouble(scanner, "Transport Allowance ($): ");
                
                // Deduction Information
                System.out.println("\n[DEDUCTION INFORMATION]");
                double taxDeduction = getValidDouble(scanner, "Tax Deduction ($): ");
                double loanDeduction = getValidDouble(scanner, "Loan Deduction ($): ");
                
                // Salary Information
                System.out.println("\n[SALARY INFORMATION]");
                double basicSalary = getValidDouble(scanner, "Basic Salary ($): ");
                double grossSalary = basicSalary + housingAllowance + transportAllowance;
                double netSalary = grossSalary - taxDeduction - loanDeduction;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing payroll record...");
                System.out.println("-".repeat(50));
                
                PayrollRecord record = new PayrollRecord(id, currentDate, currentDate, companyName, address, phoneNumber, email, departmentName, departmentCode, managerName, managerEmail, managerPhone, employeeName, employeeId, designation, contactNumber, totalDays, presentDays, leaveDays, housingAllowance, transportAllowance, taxDeduction, loanDeduction, basicSalary, grossSalary, netSalary);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Employee Payroll System!");
                    break;
                }
                
            } catch (PayrollDataException e) {
                System.out.println("\n❌ Payroll Data Error: " + e.getMessage());
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