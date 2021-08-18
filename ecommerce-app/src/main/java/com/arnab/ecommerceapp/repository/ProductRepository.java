package com.arnab.ecommerceapp.repository;

import com.arnab.ecommerceapp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.arnab.ecommerceapp.template.ResponseTemplate2 " +
            "(p.productName,p.price,p.quantity)" +
            " from Product p ORDER BY p.price")
    List<Object[]> productSortedByPrice();

    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable pageable);

    @Query("SELECT SUM(t.quantity)" +
            " from TransactionHistory t JOIN t.product p " +
            "WHERE function('trunc', t.purchasedDate) =:date AND p.id =:id")
    Long productSoldOnDate(Long id, Date date);
}
