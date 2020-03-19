package com.example.blogtemanbelajar.service;

import java.util.ArrayList;
import java.util.List;

import com.example.blogtemanbelajar.model.Categories;
import com.example.blogtemanbelajar.repository.CategoriesRepo;
import com.example.blogtemanbelajar.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CategoryService {

    @Autowired
    //CategoryRepository categoryRepository;
    CategoriesRepo categoryRepo;

    public List<Categories> getAllCategories(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Categories> pagedResult = categoryRepo.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Categories>();
        }
    }
    
}