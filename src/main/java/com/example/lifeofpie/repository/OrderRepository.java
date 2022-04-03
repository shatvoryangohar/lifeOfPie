package com.example.lifeofpie.repository;

import com.example.lifeofpie.entity.Order;
import com.example.lifeofpie.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByDateTime(Date dateTime);
    }

