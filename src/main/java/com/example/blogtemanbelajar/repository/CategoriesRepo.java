package com.example.blogtemanbelajar.repository;

import com.example.blogtemanbelajar.model.Categories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends PagingAndSortingRepository<Categories, Long> {

    
}

