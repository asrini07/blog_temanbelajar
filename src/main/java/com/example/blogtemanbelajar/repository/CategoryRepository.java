package com.example.blogtemanbelajar.repository;

import java.util.List;

import com.example.blogtemanbelajar.model.Categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    List<Categories> findByName(String name);
}