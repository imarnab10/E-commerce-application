package com.arnab.ecommerceapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TransactionHistory> transactionHistory = new HashSet<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Address> address = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ContactDetails> contactDetails = new HashSet<>();

    @OneToOne(mappedBy = "customer")
    @JsonManagedReference
    private User user;

    public Customer() {
    }

    public Customer(Long id, String customerName, Set<TransactionHistory> transactionHistory, Set<Address> address, Set<ContactDetails> contactDetails) {
        this.id = id;
        this.customerName = customerName;
        this.transactionHistory = transactionHistory;
        this.address = address;
        this.contactDetails = contactDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<TransactionHistory> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(Set<TransactionHistory> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Set<ContactDetails> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetails> contactDetails) {
        this.contactDetails = contactDetails;
    }
}
