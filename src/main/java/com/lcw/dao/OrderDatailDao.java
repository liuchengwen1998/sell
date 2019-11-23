package com.lcw.dao;

import com.lcw.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDatailDao extends JpaRepository<OrderDetail,String > {

    List<OrderDetail> findByOrderId(String orderId);
}
