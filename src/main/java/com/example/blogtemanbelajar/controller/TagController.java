package com.example.blogtemanbelajar.controller;

import java.util.List;

import com.example.blogtemanbelajar.dto.ResponseDto;
import com.example.blogtemanbelajar.exeption.ResourceNotFoundException;
import com.example.blogtemanbelajar.model.Tags;
import com.example.blogtemanbelajar.repository.TagReporitory;

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
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagReporitory tagReporitory;

    @GetMapping("/")
    public ResponseEntity<ResponseDto> getAllTag(Pageable pageable){

        ResponseDto response = new ResponseDto();

        try {

            Page<Tags> tags = tagReporitory.findAll(pageable);
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(tags);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {
            
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
            
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> createTag(@RequestBody Tags tags){

        ResponseDto response = new ResponseDto();

        if( tags.getName().isEmpty() ) {

            response.setMessage("Name Tag must not be empty");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }

        try {

            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(tagReporitory.save(tags));

            return new ResponseEntity<>(response ,HttpStatus.OK);
            
        } catch (Exception e) {
            
            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> detailTags(@PathVariable(value = "id") Long tagId) {

        ResponseDto response = new ResponseDto();

        try {
            
            Tags tags = tagReporitory.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));
            
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(tags);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteTags(@PathVariable(value = "id") Long tagId) {

        ResponseDto response = new ResponseDto();

        try {

            Tags tags = tagReporitory.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));
            tagReporitory.delete(tags);

            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(tags);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
            
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable(value = "id") Long tagId, @RequestBody Tags tagData) {

        ResponseDto response = new ResponseDto();

        if( tagData.getName().isEmpty() ) {

            response.setMessage("Name Tag must not be empty");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

        }

        try {

            Tags tag = tagReporitory.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));
            tag.setName(tagData.getName());

            Tags updateTag = tagReporitory.save(tag);
            
            response.setStatus(true);
            response.setCode(200);
            response.setMessage("success");
            response.setData(updateTag);

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {

            response.setStatus(false);
            response.setCode(500);
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    
}