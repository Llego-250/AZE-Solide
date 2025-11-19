package Q1;

import java.util.Date;
import java.util.Scanner;

public final class HospitalRecord extends Bill {
    
    public HospitalRecord(int id, Date createdDate, Date updatedDate, String hospitalName,
                         String address, String phoneNumber, String email, String departmentName,
                         String departmentCode, String doctorName, String specialization,
                         String doctorEmail, String phone, String nurseName, String shift,
                         int yearsOfExperience, String patientName, int age, String gender,
                         String contactNumber, Date admissionDate, String roomNumber,
                         double roomCharges, String diagnosis, String treatmentGiven,
                         double treatmentCost, double doctorFee, double medicineCost,
                         double totalBill) throws HospitalDataException {
        super(id, createdDate, updatedDate, hospitalName, address, phoneNumber, email,
              departmentName, departmentCode, doctorName, specialization, doctorEmail,
              phone, nurseName, shift, yearsOfExperience, patientName, age, gender,
              contactNumber, admissionDate, roomNumber, roomCharges, diagnosis,
              treatmentGiven, treatmentCost, doctorFee, medicineCost, totalBill);
    }

    public double generateBill() {
        return getRoomCharges() + getTreatmentCost() + getDoctorFee() + getMedicineCost();
    }

    public void displayDetails() {
        String output = "\n============ HOSPITAL RECORD ==========\n" +
                       "Hospital: " + getHospitalName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Email: " + getEmail() + "\n" +
                       "Department: " + getDepartmentName() + " (" + getDepartmentCode() + ")\n" +
                       "Doctor: " + getDoctorName() + " - " + getSpecialization() + "\n" +
                       "Nurse: " + getNurseName() + " (" + getShift() + " shift)\n" +
                       "Patient: " + getPatientName() + ", Age: " + getAge() + ", Gender: " + getGender() + "\n" +
                       "Room: " + getRoomNumber() + "\n" +
                       "Diagnosis: " + getDiagnosis() + "\n" +
                       "Treatment: " + getTreatmentGiven() + "\n" +
                       "\n=== BILL BREAKDOWN ===\n" +
                       "Room Charges: $" + getRoomCharges() + "\n" +
                       "Treatment Cost: $" + getTreatmentCost() + "\n" +
                       "Doctor Fee: $" + getDoctorFee() + "\n" +
                       "Medicine Cost: $" + getMedicineCost() + "\n" +
                       "TOTAL BILL: $" + generateBill();
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

    private static String getValidGender(Scanner scanner) {
        while (true) {
            System.out.print("Gender (Male/Female/Other): ");
            String gender = scanner.nextLine().trim();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other")) {
                return gender;
            }
            System.out.println("❌ Error: Please enter Male, Female, or Other.");
        }
    }

    private static String getValidShift(Scanner scanner) {
        while (true) {
            System.out.print("Shift (Day/Night): ");
            String shift = scanner.nextLine().trim();
            if (shift.equalsIgnoreCase("Day") || shift.equalsIgnoreCase("Night")) {
                return shift;
            }
            System.out.println("❌ Error: Please enter Day or Night.");
        }
    }

    private static int getValidAge(Scanner scanner) {
        while (true) {
            System.out.print("Age: ");
            try {
                int age = scanner.nextInt();
                scanner.nextLine();
                if (age <= 0 || age > 150) {
                    System.out.println("❌ Error: Age must be between 1 and 150.");
                    continue;
                }
                return age;
            } catch (Exception e) {
                System.out.println("❌ Error: Please enter a valid age.");
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
                System.out.println("        HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // Hospital Information
                System.out.println("\n[HOSPITAL INFORMATION]");
                String hospitalName = getValidString(scanner, "Hospital Name: ");
                String address = getValidString(scanner, "Address: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                String email = getValidEmail(scanner, "Email: ");
                
                // Department Information
                System.out.println("\n[DEPARTMENT INFORMATION]");
                String departmentName = getValidString(scanner, "Department Name: ");
                String departmentCode = getValidString(scanner, "Department Code: ");
                
                // Doctor Information
                System.out.println("\n[DOCTOR INFORMATION]");
                String doctorName = getValidString(scanner, "Doctor Name: ");
                String specialization = getValidString(scanner, "Specialization: ");
                String doctorEmail = getValidEmail(scanner, "Doctor Email: ");
                String doctorPhone = getValidPhone(scanner, "Doctor Phone: ");
                
                // Nurse Information
                System.out.println("\n[NURSE INFORMATION]");
                String nurseName = getValidString(scanner, "Nurse Name: ");
                String shift = getValidShift(scanner);
                int yearsOfExperience = getValidInt(scanner, "Years of Experience: ");
                
                // Patient Information
                System.out.println("\n[PATIENT INFORMATION]");
                String patientName = getValidString(scanner, "Patient Name: ");
                int age = getValidAge(scanner);
                String gender = getValidGender(scanner);
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                
                // Room Information
                System.out.println("\n[ROOM & ACCOMMODATION]");
                String roomNumber = getValidString(scanner, "Room Number: ");
                double roomCharges = getValidDouble(scanner, "Room Charges ($): ");
                
                // Medical Information
                System.out.println("\n[MEDICAL INFORMATION]");
                String diagnosis = getValidString(scanner, "Diagnosis: ");
                String treatmentGiven = getValidString(scanner, "Treatment Given: ");
                
                // Billing Information
                System.out.println("\n[BILLING INFORMATION]");
                double treatmentCost = getValidDouble(scanner, "Treatment Cost ($): ");
                double doctorFee = getValidDouble(scanner, "Doctor Fee ($): ");
                double medicineCost = getValidDouble(scanner, "Medicine Cost ($): ");
                
                double totalBill = roomCharges + treatmentCost + doctorFee + medicineCost;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing hospital record...");
                System.out.println("-".repeat(50));
                
                HospitalRecord record = new HospitalRecord(id, currentDate, currentDate, hospitalName,
                    address, phoneNumber, email, departmentName, departmentCode, doctorName,
                    specialization, doctorEmail, doctorPhone, nurseName, shift, yearsOfExperience,
                    patientName, age, gender, contactNumber, currentDate, roomNumber, roomCharges,
                    diagnosis, treatmentGiven, treatmentCost, doctorFee, medicineCost, totalBill);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using Hospital Management System!");
                    break;
                }
                
            } catch (HospitalDataException e) {
                System.out.println("\n❌ Hospital Data Error: " + e.getMessage());
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