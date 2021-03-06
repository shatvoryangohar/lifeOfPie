package com.example.lifeofpie.repository;

import com.example.lifeofpie.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Integer> {


    List<Table> findAllByMaxPerson(Integer maxPerson);
}
