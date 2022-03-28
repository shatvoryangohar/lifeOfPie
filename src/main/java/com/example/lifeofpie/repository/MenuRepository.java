package com.example.lifeofpie.repository;

import com.example.lifeofpie.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Override
    List<Menu> findAllById(Iterable<Integer> integers);

    List<Menu> findAllByName(String name);


}
