package com.example.blogtemanbelajar.controller;

import com.example.blogtemanbelajar.dto.ResponseDto;
import com.example.blogtemanbelajar.exeption.ResourceNotFoundException;
import com.example.blogtemanbelajar.model.Author;
import com.example.blogtemanbelajar.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public ResponseEntity<ResponseDto> getAllAuthor(Pageable pageable) {

        ResponseDto response = new ResponseDto();

        try {

            Page<Author> author = authorRepository.findAll(pageable);
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(author);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> createAuthor(@RequestBody Author authorData){

        ResponseDto response = new ResponseDto();

        // if( authorData.getFirstName().isEmpty() ) {

        //     response.setMessage("Field firstname, lastname, username and password must not be empty");
        //     return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        // }

        try {
            
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(authorRepository.save(authorData));

            return new ResponseEntity<>(response ,HttpStatus.OK);

        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage() + "opppppp");

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }

    }

    // @PostMapping("/save")
    // public ResponseEntity<ResponseDto> createAuthor(@RequestBody Author authorData) {

    //     ResponseDto response = new ResponseDto();

    //     if( authorData.getFirstName().isEmpty() || authorData.getLastName().isEmpty()|| authorData.getUsername().isEmpty() || authorData.getPassword().isEmpty() ) {

    //         response.setMessage("Field firstname, lastname, username and password must not be empty");
    //         return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

    //     }

    //     try {

    //         response.setStatus(true);
    //         response.setCode(200);
    //         response.setMessage("success");
    //         response.setData(authorRepository.save(authorData));

    //         return new ResponseEntity<>(response ,HttpStatus.OK);
            
    //     } catch (Exception e) {

    //         response.setStatus(false);
    //         response.setCode(500);
    //         response.setMessage(e.getMessage());

    //         return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        
    //     }
    // }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> detailAuthor(@PathVariable(value = "id") Long authorId) {

        ResponseDto response = new ResponseDto();

        try {

            Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
            
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(author);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteAuthor(@PathVariable(value = "id") Long authorId) {

        ResponseDto response = new ResponseDto();

        try {
            
            Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
            authorRepository.delete(author);

            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(author);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateAuthor(@PathVariable(value = "id") Long authorId, @RequestBody Author authorData) {

        ResponseDto response = new ResponseDto();

        if( authorData.getFirstname().isEmpty() || authorData.getLastname().isEmpty() || authorData.getUsername().isEmpty() || authorData.getPassword().isEmpty() ) {

            response.setMessage("Field firstname, lastname, username and password must not be empty");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }

        try {

            Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
            author.setFirstname(authorData.getFirstname());
            author.setLastname(authorData.getLastname());
            author.setUsername(authorData.getUsername());
            author.setPassword(authorData.getPassword());

            Author updateAuthor = authorRepository.save(author);
            
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(updateAuthor);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }
    }

    
    
}