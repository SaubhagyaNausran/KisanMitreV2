package com.example.finalproject;

public class RentMachineModel {
    private String toolName;
    private String manufacturingYear;
    private String price;
    private String phone;
    private String ownerName;
    private String address;
    private String city;

    // Empty constructor required for Firebase
    public RentMachineModel() {
    }

    public RentMachineModel(String toolName, String manufacturingYear, String price, String phone, String ownerName, String address, String city) {
        this.toolName = toolName;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
        this.phone = phone;
        this.ownerName = ownerName;
        this.address = address;
        this.city = city;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
