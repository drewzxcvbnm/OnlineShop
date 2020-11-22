package com.online.shop.application.repositories;

import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query(value = "select sum(p2.PRICE) from \"order\" o\n" +
            "join PURCHASE P on o.ID = P.\"order_id\"\n" +
            "join PRODUCT P2 on P.PRODUCT_ID = P2.ID\n" +
            "where o.ID=?1", nativeQuery = true)
    long getOrderSumPrice(long orderId);

    @Query(value = "select p2.* from \"order\" o " +
            "join PURCHASE P on o.ID = P.\"order_id\" " +
            "join PRODUCT P2 on P.PRODUCT_ID = P2.ID " +
            "where o.ID=?1", nativeQuery = true)
    List<Product> getOrderProducts(long orderId);

}
