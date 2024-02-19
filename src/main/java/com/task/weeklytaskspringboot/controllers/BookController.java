package com.task.weeklytaskspringboot.controllers;


import com.task.weeklytaskspringboot.payloads.ApiResponse;
import com.task.weeklytaskspringboot.payloads.BookDTO;
import com.task.weeklytaskspringboot.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/author/{authorId}/books")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long authorId)
    {
        BookDTO createdBookDTO = this.bookService.createBook(bookDTO,authorId);
        return new ResponseEntity<>(createdBookDTO, HttpStatus.CREATED);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId)
    {
        BookDTO getBookDTO = this.bookService.getBookById(bookId);
        return new ResponseEntity<>(getBookDTO, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks()
    {
        List<BookDTO> getBookDTOList = this.bookService.getAllBooks();
        return new ResponseEntity<>(getBookDTOList, HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}/books")
    public ResponseEntity<Set<BookDTO>> getBooksByAuthor(@PathVariable Long authorId)
    {
        Set<BookDTO> booksByAuthor = this.bookService.getBooksByAuthor(authorId);
        return new ResponseEntity<>(booksByAuthor,HttpStatus.OK);
    }


    @GetMapping("/authors/{authorId}/books/filtered")
    public ResponseEntity<Set<BookDTO>> getFilteredBooks(@PathVariable Long authorId)
    {
        Set<BookDTO> filteredBooks = this.bookService.getFilteredBooksByAuthorAndSortedByBookName(authorId);
        return new ResponseEntity<>(filteredBooks,HttpStatus.OK);

    }


    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long bookId)
    {
        BookDTO updatedBookDTO = this.bookService.updateBook(bookDTO, bookId);
        return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId)
    {
        this.bookService.deleteBookById(bookId);
        return new ResponseEntity<>(new ApiResponse("book deleted successfully",true),HttpStatus.OK);
    }

}
