package com.arnab.ecommerceapp.controller;

import com.arnab.ecommerceapp.entity.*;
import com.arnab.ecommerceapp.model.JwtRequest;
import com.arnab.ecommerceapp.model.JwtResponse;
import com.arnab.ecommerceapp.service.UserDetailsServiceImpl;
import com.arnab.ecommerceapp.template.ContactDetailsTemplate;
import com.arnab.ecommerceapp.template.Transaction;
import com.arnab.ecommerceapp.service.TransactionService;
import com.arnab.ecommerceapp.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    //*************All Customer Apis*************//
    @GetMapping("/get/{name}")
    public List<Object[]> getCustomer(@PathVariable String name){
        return transactionService.getCustomer(name);
    }


    //get customer object by id
    @GetMapping("/cus/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return transactionService.getCustomerById(id);
    }

    //*************All Product Apis*************//

    //get product object by id
    @GetMapping("/pro/{id}")
    public Product getProductById(@PathVariable Long id){
        return transactionService.getProductById(id);
    }
    //product sorted by price
    /*@GetMapping("/psort")
    public Page<Product> productSortedByPrice(){
        return transactionService.productSortedByPrice();
    }*/

    @GetMapping("/product/{id}/{date}")
    public Long productSoldOnDate(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return transactionService.productSoldOnDate(id,date);
    }
    @GetMapping("/psort")
    public ResponseEntity<List<Product>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "price") String sortBy)
    {
        List<Product> list = transactionService.productSortedByPrice(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    //*************All Transaction Apis*************//

    //Get all transaction for a particular Date
    @GetMapping("/transaction/{date}")
    public List<Object[]> transactionByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return transactionService.transactionByDate(date);
    }
    //total sell on a particular day
    @GetMapping("/sell/{date}")
    public Long getSellPerDay(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return transactionService.getSellPerDay(date);
    }

    @GetMapping("/transaction")
    public List<TransactionHistory> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

    @PostMapping("/transaction")
    public TransactionHistory saveTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }

    //get transaction from date to date
    @GetMapping("/transaction/{from}/{to}")
    public List<Object[]> getTransactionByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date to){
        return transactionService.getTransactionByDate(from,to);
    }

    /*@GetMapping("/username/{user}")
    public User findByUsername(@PathVariable String user){
        return transactionService.findByUsername(user);
    }*/
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }

    //contact details
    @GetMapping("/cd/all")
    List<ContactDetails> getAllContactDetails(){
        return transactionService.getAllContactDetails();
    }
    @GetMapping("/cd/{id}")
    ResponseEntity<ContactDetails> getAllContactDetails(@PathVariable Long id){
        return transactionService.findContactDetailsById(id);
    }
    @PostMapping("/cd_save")
    ContactDetails saveContactDetails(@RequestBody ContactDetailsTemplate contactDetailsTemplate){
        return transactionService.saveContactDetails(contactDetailsTemplate);
    }
}
