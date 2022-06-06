package com.assignment.repository;

import com.assignment.domain.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetail, Long> {

    @Query(nativeQuery = true,
            value = "select d.* from delivery_detail as d" +
                    " WHERE d.delivery_status!='Order Delivered'" +
                    " and d.delivery_id not in " +
                    "(select d.delivery_id from delivery_detail as d  INNER JOIN ticket " +
                    "as t on d.delivery_id != t.detail_delivery_id" +
                    " where d.delivery_status!='Order Delivered' )")
    List<DeliveryDetail> findOrdersToDeliver();
}
