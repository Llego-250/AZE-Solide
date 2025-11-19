package Q1;

import java.util.Date;

public class Department extends Hospital {
    private String departmentName;
    private String departmentCode;

    public Department(int id, Date createdDate, Date updatedDate, String hospitalName,
                     String address, String phoneNumber, String email, String departmentName,
                     String departmentCode) throws HospitalDataException {
        super(id, createdDate, updatedDate, hospitalName, address, phoneNumber, email);
        
        if (departmentCode == null || departmentCode.length() < 3 || !departmentCode.matches("^[a-zA-Z0-9]+$")) {
            throw new HospitalDataException("Department code must be alphanumeric and at least 3 characters");
        }
        
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }
}