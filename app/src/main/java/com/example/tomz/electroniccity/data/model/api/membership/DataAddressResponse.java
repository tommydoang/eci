package com.example.tomz.electroniccity.data.model.api.membership;

public class DataAddressResponse {

    private String statusResponse, idCustomerAddress, idCustomer, titleAddress, nameReceiver,
        phoneNumber1, phoneNumber2, address, isDefault, idDistrict, isPrimary, idWeddingRegistry;

    public DataAddressResponse(){

    }

    public DataAddressResponse(String statusResponse, String idCustomerAddress, String idCustomer,
                               String titleAddress, String nameReceiver, String phoneNumber1,
                               String phoneNumber2, String address, String isDefault, String idDistrict,
                               String isPrimary, String idWeddingRegistry) {
        this.statusResponse = statusResponse;
        this.idCustomerAddress = idCustomerAddress;
        this.idCustomer = idCustomer;
        this.titleAddress = titleAddress;
        this.nameReceiver = nameReceiver;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.address = address;
        this.isDefault = isDefault;
        this.idDistrict = idDistrict;
        this.isPrimary = isPrimary;
        this.idWeddingRegistry = idWeddingRegistry;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(String statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getIdCustomerAddress() {
        return idCustomerAddress;
    }

    public void setIdCustomerAddress(String idCustomerAddress) {
        this.idCustomerAddress = idCustomerAddress;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getTitleAddress() {
        return titleAddress;
    }

    public void setTitleAddress(String titleAddress) {
        this.titleAddress = titleAddress;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getIdWeddingRegistry() {
        return idWeddingRegistry;
    }

    public void setIdWeddingRegistry(String idWeddingRegistry) {
        this.idWeddingRegistry = idWeddingRegistry;
    }
}
