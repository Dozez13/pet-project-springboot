package com.example.task16.db.repository;

import com.example.task16.db.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Long countByUser_UserId(UUID userId);
    List<Order> findByUser_UserId(UUID uuid, Pageable pageable);
}
