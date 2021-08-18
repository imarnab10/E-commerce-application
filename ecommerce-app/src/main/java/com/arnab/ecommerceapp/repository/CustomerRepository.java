package com.arnab.ecommerceapp.repository;

import com.arnab.ecommerceapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT NEW com.arnab.ecommerceapp.template.ResponseTemplate " +
            "(c.customerName,p.productName,ad.details)" +
            " from Customer c JOIN c.transactionHistory t JOIN c.address ad JOIN t.product p " +
            "WHERE c.customerName LIKE %:name%")
    List<Object[]> getCustomerByName(String name);


    //Customer findById(Long id);

}
