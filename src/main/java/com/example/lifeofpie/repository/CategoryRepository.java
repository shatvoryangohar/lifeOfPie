package com.example.lifeofpie.repository;

import com.example.lifeofpie.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByName(String name);
}
