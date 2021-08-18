package com.arnab.ecommerceapp.repository;

import com.arnab.ecommerceapp.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory,Long> {

    @Query("SELECT t " +
            "from TransactionHistory t " +
            "WHERE function('trunc', t.purchasedDate) =:date")
    List<Object[]> transactionByDate(Date date);

    @Query("SELECT NEW com.arnab.ecommerceapp.template.ResponseTemplate " +
            "(c.customerName,p.productName,ad.details)" +
            " from TransactionHistory t JOIN t.customer c JOIN t.address ad JOIN t.product p " +
            "WHERE function('trunc', t.purchasedDate) BETWEEN :from AND :to")
    List<Object[]> getTransactionByDate(Date from, Date to);

    @Query("SELECT SUM(t.quantity*t.product.price) from TransactionHistory t " +
            "WHERE function('trunc', t.purchasedDate) =:date")
    Long getSellPerDay(Date date);
}
