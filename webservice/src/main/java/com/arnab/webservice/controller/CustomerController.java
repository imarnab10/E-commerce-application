package com.arnab.webservice.controller;

import com.arnab.webservice.service.CustomerService;
import com.arnab.webservice.template.ContactDetailsTemplate;
import com.arnab.webservice.template.TransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer-name/{name}")
    public ResponseEntity<Object[]> getCustomerByName(@PathVariable String name){
        return customerService.getCustomerByName(name);
    }

    @GetMapping("/customer/{id}")
    public Object getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/product/{id}")
    public Object getProductById(@PathVariable Long id) {
        return customerService.getProductById(id);
    }

    @GetMapping("/sort-product")
    public ResponseEntity<Object[]> productSortedByPrice() {
        return customerService.productSortedByPrice();
    }

    //all transaction services
    @PostMapping("/save_transaction")
    public Object postTransaction(@RequestBody TransactionTemplate transactionTemplate){
        return customerService.postTransaction(transactionTemplate);
    }
    @GetMapping("/transaction/{date}")
    public ResponseEntity<Object[]> getTransactionForOneDay(@PathVariable String date){
        return customerService.getTransactionForOneDay(date);
    }

    @GetMapping("/transaction/{from}/{to}")
    public ResponseEntity<Object[]> getTransactionByDate(@PathVariable String from, @PathVariable String to){
        return customerService.getTransactionByDate(from,to);
    }

    @GetMapping("/sell/{date}")
    public ResponseEntity<Long> getSellByDate(@PathVariable String date){
        return customerService.getSellByDate(date);
    }

    @GetMapping("/transaction")
    public ResponseEntity<Object[]> getAllTransaction(){
        return customerService.getAllTransaction();
    }

    @GetMapping("/username/{user}")
    public Object getCustomerByUsername(@PathVariable String user){
        return customerService.getCustomerByUsername(user);
    }

    //contact details api
    @PostMapping("/save_cd")
    public Object postContactDetails(@RequestBody ContactDetailsTemplate contactDetailsTemplate){
        return customerService.postContactDetails(contactDetailsTemplate);
    }
}
