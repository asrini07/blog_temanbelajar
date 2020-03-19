package com.example.blogtemanbelajar.repository;

import com.example.blogtemanbelajar.model.Tags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagReporitory extends JpaRepository<Tags, Long>{

    
}