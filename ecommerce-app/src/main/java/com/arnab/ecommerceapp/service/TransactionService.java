package com.arnab.ecommerceapp.service;

import com.arnab.ecommerceapp.entity.*;
import com.arnab.ecommerceapp.exception.EntityNotAssociatedWithCustomer;
import com.arnab.ecommerceapp.exception.ItemNotFoundException;
import com.arnab.ecommerceapp.exception.NotEnoughProduct;
import com.arnab.ecommerceapp.exception.UniqueKeyException;
import com.arnab.ecommerceapp.repository.*;
import com.arnab.ecommerceapp.template.ContactDetailsTemplate;
import com.arnab.ecommerceapp.template.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    private ResponseEntity responseEntity;
    //All Transaction Services
    public TransactionHistory saveTransaction(Transaction transaction){
        Product p = productRepository.findById(transaction.getProductId()).orElseThrow(() -> new ItemNotFoundException("Product with ID:["+transaction.getProductId()+"] was not found"));
        Customer c = customerRepository.findById(transaction.getCustomerId()).orElseThrow(() -> new ItemNotFoundException("Customer with ID:["+ transaction.getCustomerId() +"] was not found"));
        Address ad = addressRepository.findById(transaction.getAddressId()).orElseThrow(() -> new ItemNotFoundException("Address with ID:["+transaction.getAddressId()+"] was not found"));
        ContactDetails cd = contactDetailsRepository.findById(transaction.getContactId()).orElseThrow(() -> new ItemNotFoundException("Contact Details with ID:["+transaction.getContactId()+"] was not found"));
        boolean flag1 = false , flag2 = false;
        for(Address address:c.getAddress()){
            if(address.getId() == transaction.getAddressId())
                flag1 = true;
        }
        for(ContactDetails contactDetails:c.getContactDetails()){
            if(contactDetails.getId() == transaction.getContactId())
                flag2 = true;
        }
        if(!flag1){
            throw new EntityNotAssociatedWithCustomer("Address with ID:["+transaction.getAddressId()+"] is not associated with Customer ID:["+c.getId()+"].");
        }
        if(!flag2){
            throw new EntityNotAssociatedWithCustomer("ContactDetails with ID:["+transaction.getContactId()+"] is not associated with Customer ID:["+c.getId()+"].");
        }
        int available = p.getQuantity();
        int purschased = transaction.getQuantity();
        TransactionHistory transactionHistory = new TransactionHistory();
        if (available >= purschased){
            transactionHistory.setId(transaction.getId());
            p.setQuantity(available-purschased);
            transactionHistory.setProduct(p);
            transactionHistory.setCustomer(c);
            transactionHistory.setAddress(ad);
            transactionHistory.setContactDetails(cd);
            transactionHistory.setPurchasedDate(new Date());
            transactionHistory.setQuantity(purschased);
            transactionRepository.save(transactionHistory);
        }
        else{
            throw new NotEnoughProduct("Product with ID:["+p.getId()+"] is only "+available+ "left");
        }

        return transactionHistory;
    }

    public List<TransactionHistory> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public List<Object[]> transactionByDate(Date date){
        return transactionRepository.transactionByDate(date);
    }

    public List<Object[]> getTransactionByDate(Date from ,Date to){
        return transactionRepository.getTransactionByDate(from, to);
    }

    public Long getSellPerDay(Date date) {
        return transactionRepository.getSellPerDay(date);
    }
    //All Customer Services
    public List<Object[]> getCustomer(String name){
        return customerRepository.getCustomerByName(name);
    }

    //Get Customer By Id
    public ResponseEntity<Customer> getCustomerById(Long id) {
        Customer customer = new Customer();
        int i = 0;
        while(true){


            i++;
            if(i == 999999999)break;
            System.out.println("inside the loop");
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           /* Customer customer = customerRepository.findById(id).orElseThrow(
                    () -> new ItemNotFoundException("Customer with ID:["+ id +"] was not found"));*/
        }
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    //All Product Services
    //Get Product By Id
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Product with ID:["+id+"] was not found"));
        return product;
    }

    /*public Page<Product> productSortedByPrice(){
        return (Page<Product>) productRepository.findAll(Sort.by("price"));
    }*/

    public List<Product> productSortedByPrice(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        Page<Product> pagedResult = productRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }

    //any particular product sold on date
    public Long productSoldOnDate(Long id, Date date){
        return productRepository.productSoldOnDate(id,date);
    }

    // contact details
    public List<ContactDetails> getAllContactDetails(){
        return contactDetailsRepository.findAll();
    }
    public ResponseEntity<ContactDetails> findContactDetailsById(Long id){
        ContactDetails contactDetails = contactDetailsRepository.getById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactDetails);
    }
    //save contact details
    public ContactDetails saveContactDetails(ContactDetailsTemplate cd){

        if(contactDetailsRepository.existsById(cd.getId())){
            throw new UniqueKeyException("Primary Key Already exists");
        }
        //Customer c = customerRepository.getById(cd.getCustomerId());
        try{
            Customer c = customerRepository.findById(cd.getCustomerId()).orElseThrow(() -> new ItemNotFoundException("Customer with ID:["+ cd.getCustomerId() +"] was not found"));
            ContactDetails contactDetails = new ContactDetails();
            contactDetails.setId(cd.getId());
            contactDetails.setEmail(cd.getEmail());
            contactDetails.setMobileNumber(cd.getMobileNumber());
            contactDetails.setCustomer(c);
            //c.getContactDetails().add(contactDetails);
            return contactDetailsRepository.save(contactDetails);
        }catch (DataIntegrityViolationException e){
            throw new UniqueKeyException("Email Id or Mobile No already exists");
        }
    }
}
