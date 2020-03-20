package com.example.blogtemanbelajar.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.blogtemanbelajar.dto.ResponseDto;
import com.example.blogtemanbelajar.exeption.ResourceNotFoundException;
import com.example.blogtemanbelajar.model.Categories;
import com.example.blogtemanbelajar.repository.CategoriesRepo;
import com.example.blogtemanbelajar.repository.CategoryRepository;
import com.example.blogtemanbelajar.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<ResponseDto> getAllCategories() {
        
        ResponseDto response = new ResponseDto();

        try {

            List<Categories> categories = categoryRepository.findAll();
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(categories);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Categories>> getAllCategoriesPagination(
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "id") String sortBy
    ) {
        
        ResponseDto response = new ResponseDto();

        try {

            List<Categories> categories = categoryService.getAllCategories(pageNo, pageSize, sortBy);
            
            return new ResponseEntity<List<Categories>>(categories, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<List<Categories>>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createCategory(@RequestBody Categories categories) {
       
        ResponseDto response = new ResponseDto();

        Categories category = new Categories();

        if( categories.getName().isEmpty() ) {

            response.setMessage("Name Category must not be empty");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }

        try {

            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(categoryRepository.save(categories));

            return new ResponseEntity<>(response ,HttpStatus.OK);
            
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> detailCategory(@PathVariable(value = "id") Long categoryId) {

        ResponseDto response = new ResponseDto();

        try {

            Categories categories = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
            
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(categories);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable(value = "id") Long categoryId) {

        ResponseDto response = new ResponseDto();

        try {

            Categories category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
            categoryRepository.delete(category);

            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(category);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable(value = "id") Long categoryId, @RequestBody Categories categoriesData) {

            ResponseDto response = new ResponseDto();

            if( categoriesData.getName().isEmpty() ) {

                response.setMessage("Name Category must not be empty");
                return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

            }
                
            try {

                Categories category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
                category.setName(categoriesData.getName());

                Categories updateCategories = categoryRepository.save(category);
                
                response.setStatus(true);
                response.setCode(200);
                response.setMessage("success");
                response.setData(updateCategories);

                return new ResponseEntity<>(response, HttpStatus.OK);
                
            } catch (Exception e) {

                response.setStatus(false);
                response.setCode(500);
                response.setMessage(e.getMessage());

                return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

            }
    }
}