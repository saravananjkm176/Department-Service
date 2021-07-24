package com.s3.department.service.main.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
    
    private String deptCode;
    
    private String name;
    
    private String city;
    
    private String village;
    
    private long pincode;
    
    private long lotalStaff;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

    public long getLotalStaff() {
        return lotalStaff;
    }

    public void setLotalStaff(long lotalStaff) {
        this.lotalStaff = lotalStaff;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
