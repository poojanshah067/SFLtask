package com.task.weeklytaskspringboot.controllers;

import com.task.weeklytaskspringboot.payloads.ApiResponse;
import com.task.weeklytaskspringboot.payloads.AuthorDTO;
import com.task.weeklytaskspringboot.payloads.BookDTO;
import com.task.weeklytaskspringboot.service.AuthorService;
import com.task.weeklytaskspringboot.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO)
    {
        AuthorDTO createdAuthorDTO = this.authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(createdAuthorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long authorId)
    {
        AuthorDTO getAuthorId = this.authorService.getAuthorById(authorId);
        return new ResponseEntity<>(getAuthorId, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors()
    {
        List<AuthorDTO> getAuthorDTOList = this.authorService.getAllAuthors();
        return new ResponseEntity<>(getAuthorDTOList, HttpStatus.OK);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO, @PathVariable Long authorId)
    {
        AuthorDTO updatedAuthorDTO = this.authorService.updateAuthor(authorDTO, authorId);
        return new ResponseEntity<>(updatedAuthorDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable Long authorId)
    {
        this.authorService.deleteAuthorById(authorId);
        return new ResponseEntity<>(new ApiResponse("author deleted successfully",true),HttpStatus.OK);
    }
}
