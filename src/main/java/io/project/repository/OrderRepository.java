package io.project.repository;

import io.project.model.Order;
import io.project.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    Page<Order> findOrderByStatus(OrderStatus status, Pageable pageable);
}
