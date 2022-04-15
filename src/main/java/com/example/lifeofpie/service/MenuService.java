package com.example.lifeofpie.service;

import com.example.lifeofpie.entity.Menu;
import com.example.lifeofpie.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;


    public Menu save(Menu menu){
       return menuRepository.save(menu);
    }


    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
