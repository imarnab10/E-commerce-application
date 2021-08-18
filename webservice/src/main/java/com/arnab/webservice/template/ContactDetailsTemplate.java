package com.arnab.webservice.template;

public class ContactDetailsTemplate {
    private Long id;
    private Long mobileNumber;
    private String email;
    private Long customerId;

    public ContactDetailsTemplate() {
    }

    public ContactDetailsTemplate( Long id,Long mobileNumber, String email, Long customerId) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
