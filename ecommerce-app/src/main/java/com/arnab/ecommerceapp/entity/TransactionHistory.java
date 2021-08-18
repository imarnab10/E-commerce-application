package com.arnab.ecommerceapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase_history")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    @JsonBackReference
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "fk_address_id")
    @JsonBackReference
    private Address address;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Calcutta")
    private Date purchasedDate;

    @OneToOne
    @JoinColumn(name = "fk_contact_id")
    @JsonBackReference
    private ContactDetails contactDetails;

    public TransactionHistory() {
    }

    public TransactionHistory(Long id, Product product, int quantity, Customer customer, Address address, Date purchasedDate, ContactDetails contactDetails) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.address = address;
        this.purchasedDate = purchasedDate;
        this.contactDetails = contactDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
