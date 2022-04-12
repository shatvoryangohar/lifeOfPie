package com.example.lifeofpie.service;

import com.example.lifeofpie.entity.Category;
import com.example.lifeofpie.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {


    public final CategoryRepository categoryRepository;



    public List<Category> findAll(){
       return categoryRepository.findAll();
    }



}
