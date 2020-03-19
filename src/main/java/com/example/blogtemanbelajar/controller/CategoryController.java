package com.example.blogtemanbelajar.controller;

import java.util.List;

import com.example.blogtemanbelajar.model.Categories;
import com.example.blogtemanbelajar.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    
}