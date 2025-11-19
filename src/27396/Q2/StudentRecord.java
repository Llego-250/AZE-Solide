package Q2;

import java.util.Date;
import java.util.Scanner;

// Condensed implementation with all classes in one file for brevity
class Department extends School {
    private String departmentName, departmentCode;
    
    public Department(int id, Date createdDate, Date updatedDate, String schoolName, String address, 
                     String phoneNumber, String email, String departmentName, String departmentCode) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email);
        if (departmentCode == null || departmentCode.length() < 3 || !departmentCode.matches("^[a-zA-Z0-9]+$")) {
            throw new SchoolDataException("Department code must be alphanumeric and >= 3 chars");
        }
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }
    
    public String getDepartmentName() { return departmentName; }
    public String getDepartmentCode() { return departmentCode; }
}

class Teacher extends Department {
    private String teacherName, subject, teacherEmail, phone;
    
    public Teacher(int id, Date createdDate, Date updatedDate, String schoolName, String address,
                  String phoneNumber, String email, String departmentName, String departmentCode,
                  String teacherName, String subject, String teacherEmail, String phone) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode);
        if (subject == null || subject.trim().isEmpty()) throw new SchoolDataException("Subject cannot be empty");
        if (teacherEmail == null || !teacherEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new SchoolDataException("Invalid teacher email");
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) throw new SchoolDataException("Phone must be 10 digits");
        
        this.teacherName = teacherName;
        this.subject = subject;
        this.teacherEmail = teacherEmail;
        this.phone = phone;
    }
    
    public String getTeacherName() { return teacherName; }
    public String getSubject() { return subject; }
    public String getTeacherEmail() { return teacherEmail; }
    public String getPhone() { return phone; }
}

class Student extends Teacher {
    private String studentName, grade, contactNumber;
    private int rollNumber;
    
    public Student(int id, Date createdDate, Date updatedDate, String schoolName, String address,
                  String phoneNumber, String email, String departmentName, String departmentCode,
                  String teacherName, String subject, String teacherEmail, String phone,
                  String studentName, int rollNumber, String grade, String contactNumber) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, phone);
        if (rollNumber <= 0) throw new SchoolDataException("Roll number must be > 0");
        if (grade == null || grade.trim().isEmpty()) throw new SchoolDataException("Grade cannot be empty");
        
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.contactNumber = contactNumber;
    }
    
    public String getStudentName() { return studentName; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public String getContactNumber() { return contactNumber; }
}

class Course extends Student {
    private String courseName, courseCode;
    private int creditHours;
    
    public Course(int id, Date createdDate, Date updatedDate, String schoolName, String address,
                 String phoneNumber, String email, String departmentName, String departmentCode,
                 String teacherName, String subject, String teacherEmail, String phone,
                 String studentName, int rollNumber, String grade, String contactNumber,
                 String courseName, String courseCode, int creditHours) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, phone, studentName, rollNumber, grade, contactNumber);
        if (creditHours <= 0) throw new SchoolDataException("Credit hours must be > 0");
        
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHours = creditHours;
    }
    
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public int getCreditHours() { return creditHours; }
}

class Exam extends Course {
    private String examName, examDate;
    private int maxMarks;
    
    public Exam(int id, Date createdDate, Date updatedDate, String schoolName, String address,
               String phoneNumber, String email, String departmentName, String departmentCode,
               String teacherName, String subject, String teacherEmail, String phone,
               String studentName, int rollNumber, String grade, String contactNumber,
               String courseName, String courseCode, int creditHours, String examName,
               int maxMarks, String examDate) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, phone, studentName, rollNumber, grade, contactNumber, courseName, courseCode, creditHours);
        if (maxMarks <= 0) throw new SchoolDataException("Max marks must be > 0");
        if (examDate == null || examDate.trim().isEmpty()) throw new SchoolDataException("Exam date cannot be empty");
        
        this.examName = examName;
        this.maxMarks = maxMarks;
        this.examDate = examDate;
    }
    
    public String getExamName() { return examName; }
    public int getMaxMarks() { return maxMarks; }
    public String getExamDate() { return examDate; }
}

class Result extends Exam {
    private int obtainedMarks;
    private String remarks;
    
    public Result(int id, Date createdDate, Date updatedDate, String schoolName, String address,
                 String phoneNumber, String email, String departmentName, String departmentCode,
                 String teacherName, String subject, String teacherEmail, String phone,
                 String studentName, int rollNumber, String grade, String contactNumber,
                 String courseName, String courseCode, int creditHours, String examName,
                 int maxMarks, String examDate, int obtainedMarks, String remarks) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, phone, studentName, rollNumber, grade, contactNumber, courseName, courseCode, creditHours, examName, maxMarks, examDate);
        if (obtainedMarks < 0) throw new SchoolDataException("Obtained marks must be >= 0");
        if (remarks == null || remarks.trim().isEmpty()) throw new SchoolDataException("Remarks cannot be empty");
        
        this.obtainedMarks = obtainedMarks;
        this.remarks = remarks;
    }
    
    public int getObtainedMarks() { return obtainedMarks; }
    public String getRemarks() { return remarks; }
}

class Fee extends Result {
    private double tuitionFee, examFee, totalFee;
    
    public Fee(int id, Date createdDate, Date updatedDate, String schoolName, String address,
              String phoneNumber, String email, String departmentName, String departmentCode,
              String teacherName, String subject, String teacherEmail, String phone,
              String studentName, int rollNumber, String grade, String contactNumber,
              String courseName, String courseCode, int creditHours, String examName,
              int maxMarks, String examDate, int obtainedMarks, String remarks,
              double tuitionFee, double examFee, double totalFee) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, phone, studentName, rollNumber, grade, contactNumber, courseName, courseCode, creditHours, examName, maxMarks, examDate, obtainedMarks, remarks);
        if (tuitionFee <= 0 || examFee <= 0 || totalFee <= 0) throw new SchoolDataException("All fees must be > 0");
        
        this.tuitionFee = tuitionFee;
        this.examFee = examFee;
        this.totalFee = totalFee;
    }
    
    public double getTuitionFee() { return tuitionFee; }
    public double getExamFee() { return examFee; }
    public double getTotalFee() { return totalFee; }
}

public final class StudentRecord extends Fee {
    private double averageMarks;
    
    public StudentRecord(int id, Date createdDate, Date updatedDate, String schoolName, String address,
                        String phoneNumber, String email, String departmentName, String departmentCode,
                        String teacherName, String subject, String teacherEmail, String phone,
                        String studentName, int rollNumber, String grade, String contactNumber,
                        String courseName, String courseCode, int creditHours, String examName,
                        int maxMarks, String examDate, int obtainedMarks, String remarks,
                        double tuitionFee, double examFee, double totalFee) throws SchoolDataException {
        super(id, createdDate, updatedDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, phone, studentName, rollNumber, grade, contactNumber, courseName, courseCode, creditHours, examName, maxMarks, examDate, obtainedMarks, remarks, tuitionFee, examFee, totalFee);
        this.averageMarks = calculateAverageMarks();
    }
    
    public double calculateAverageMarks() {
        return (double) getObtainedMarks() / getMaxMarks() * 100;
    }
    
    public double getAverageMarks() {
        return averageMarks;
    }
    
    public void displayDetails() {
        String output = "\n============ STUDENT RECORD ===========\n" +
                       "School: " + getSchoolName() + "\n" +
                       "Address: " + getAddress() + "\n" +
                       "Phone: " + getPhoneNumber() + "\n" +
                       "Email: " + getEmail() + "\n" +
                       "Department: " + getDepartmentName() + " (" + getDepartmentCode() + ")\n" +
                       "Teacher: " + getTeacherName() + " - " + getSubject() + "\n" +
                       "Teacher Email: " + getTeacherEmail() + "\n" +
                       "Student: " + getStudentName() + ", Roll: " + getRollNumber() + ", Grade: " + getGrade() + "\n" +
                       "Contact: " + getContactNumber() + "\n" +
                       "Course: " + getCourseName() + " (" + getCourseCode() + ") - " + getCreditHours() + " credits\n" +
                       "Exam: " + getExamName() + " - " + getExamDate() + "\n" +
                       "Marks: " + getObtainedMarks() + "/" + getMaxMarks() + "\n" +
                       "Average: " + String.format("%.2f", calculateAverageMarks()) + "%\n" +
                       "Remarks: " + getRemarks() + "\n" +
                       "\n=== FEE BREAKDOWN ===\n" +
                       "Tuition Fee: $" + getTuitionFee() + "\n" +
                       "Exam Fee: $" + getExamFee() + "\n" +
                       "TOTAL FEE: $" + getTotalFee();
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
                System.out.println("        SCHOOL MANAGEMENT SYSTEM");
                System.out.println("=".repeat(50));
                
                // Record ID
                System.out.println("\n[RECORD INFORMATION]");
                int id = getValidInt(scanner, "Enter Record ID: ");
                Date currentDate = new Date();
                
                // School Information
                System.out.println("\n[SCHOOL INFORMATION]");
                String schoolName = getValidString(scanner, "School Name: ");
                String address = getValidString(scanner, "Address: ");
                String phoneNumber = getValidPhone(scanner, "Phone Number (10 digits): ");
                String email = getValidEmail(scanner, "Email: ");
                
                // Department Information
                System.out.println("\n[DEPARTMENT INFORMATION]");
                String departmentName = getValidString(scanner, "Department Name: ");
                String departmentCode = getValidString(scanner, "Department Code: ");
                
                // Teacher Information
                System.out.println("\n[TEACHER INFORMATION]");
                String teacherName = getValidString(scanner, "Teacher Name: ");
                String subject = getValidString(scanner, "Subject: ");
                String teacherEmail = getValidEmail(scanner, "Teacher Email: ");
                String teacherPhone = getValidPhone(scanner, "Teacher Phone: ");
                
                // Student Information
                System.out.println("\n[STUDENT INFORMATION]");
                String studentName = getValidString(scanner, "Student Name: ");
                int rollNumber = getValidInt(scanner, "Roll Number: ");
                String grade = getValidString(scanner, "Grade: ");
                String contactNumber = getValidPhone(scanner, "Contact Number (10 digits): ");
                
                // Course Information
                System.out.println("\n[COURSE INFORMATION]");
                String courseName = getValidString(scanner, "Course Name: ");
                String courseCode = getValidString(scanner, "Course Code: ");
                int creditHours = getValidInt(scanner, "Credit Hours: ");
                
                // Exam Information
                System.out.println("\n[EXAM INFORMATION]");
                String examName = getValidString(scanner, "Exam Name: ");
                int maxMarks = getValidInt(scanner, "Max Marks: ");
                String examDate = getValidString(scanner, "Exam Date: ");
                int obtainedMarks = getValidInt(scanner, "Obtained Marks: ");
                String remarks = getValidString(scanner, "Remarks: ");
                
                // Fee Information
                System.out.println("\n[FEE INFORMATION]");
                double tuitionFee = getValidDouble(scanner, "Tuition Fee ($): ");
                double examFee = getValidDouble(scanner, "Exam Fee ($): ");
                double totalFee = tuitionFee + examFee;
                
                System.out.println("\n" + "-".repeat(50));
                System.out.println("Processing student record...");
                System.out.println("-".repeat(50));
                
                StudentRecord record = new StudentRecord(id, currentDate, currentDate, schoolName, address, phoneNumber, email, departmentName, departmentCode, teacherName, subject, teacherEmail, teacherPhone, studentName, rollNumber, grade, contactNumber, courseName, courseCode, creditHours, examName, maxMarks, examDate, obtainedMarks, remarks, tuitionFee, examFee, totalFee);
                
                record.displayDetails();
                
                System.out.println("\n" + "=".repeat(50));
                String continueChoice = getValidYesNo(scanner, "Do you want to enter another record? (yes/no): ");
                
                if (continueChoice.equalsIgnoreCase("no") || continueChoice.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using School Management System!");
                    break;
                }
                
            } catch (SchoolDataException e) {
                System.out.println("\n❌ School Data Error: " + e.getMessage());
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