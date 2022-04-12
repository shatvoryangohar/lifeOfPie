package com.example.lifeofpie.dto;

import com.example.lifeofpie.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuRequest {
    private int id;
    private String name;
    private String description;
    private double price;
    private String picUrl;
    private int categoryId;
}
