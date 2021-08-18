package com.arnab.ecommerceapp.template;

public class ResponseTemplate {
    private String customerName;
    private String productName;
    private String locationDetails;

    public ResponseTemplate() {
    }

    public ResponseTemplate(String customerName, String productName, String locationDetails) {
        this.customerName = customerName;
        this.productName = productName;
        this.locationDetails = locationDetails;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }
}
