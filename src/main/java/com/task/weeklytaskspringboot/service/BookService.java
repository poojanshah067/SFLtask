package com.task.weeklytaskspringboot.service;

import com.task.weeklytaskspringboot.entities.Author;
import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.entities.LibraryMember;
import com.task.weeklytaskspringboot.exceptions.ResourceNotFoundException;
import com.task.weeklytaskspringboot.payloads.BookDTO;
import com.task.weeklytaskspringboot.repositories.AuthorRepository;
import com.task.weeklytaskspringboot.repositories.BookRepository;
import com.task.weeklytaskspringboot.repositories.LibraryMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    LibraryMemberRepository libraryMemberRepository;

    @Autowired
    ModelMapper modelMapper;

    //create Book
    public BookDTO createBook(BookDTO bookDTO, Long authorId)
    {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", authorId));

        Book book = this.modelMapper.map(bookDTO, Book.class);
        book.setAuthor(author);
        Book savedBook = this.bookRepository.save(book);
        return this.modelMapper.map(savedBook, BookDTO.class);
    }

    //issue book by member

//    public BookDTO issueBook(BookDTO bookDTO, Long libraryMemberId)
//    {
//        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("Member", "Id", libraryMemberId));
//        Book book = this.modelMapper.map(bookDTO, Book.class);
//        book.setLibraryMember(libraryMember);
//        Book savedBook = this.bookRepository.save(book);
//        return this.modelMapper.map(savedBook, BookDTO.class);
//    }

    //update Book
    public BookDTO updateBook(BookDTO bookDTO, Long bookId)
    {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setBookName(bookDTO.getBookName());
        book.setBookDescription(bookDTO.getBookDescription());

        Book updatedBook = this.bookRepository.save(book);
        return this.modelMapper.map(updatedBook,BookDTO.class);

    }

    //get Book
    public List<BookDTO> getAllBooks()
    {
        List<Book> allBooks = this.bookRepository.findAll();
        List<BookDTO> bookDTOList = allBooks.stream()
                .map((book) -> this.modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());

        return bookDTOList;
    }

    //get Book By Id
    public BookDTO getBookById(Long bookId)
    {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        return this.modelMapper.map(book,BookDTO.class);
    }

    //delete book by Id
    public void deleteBookById(Long bookId)
    {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        this.bookRepository.delete(book);
    }

    //get book by author
    public Set<BookDTO> getBooksByAuthor(Long authorId)
    {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", authorId));
        Set<Book> byAuthor = this.bookRepository.findByAuthor(author);

        Set<BookDTO> bookDTOSet = byAuthor.stream().map(book -> this.modelMapper.map(book, BookDTO.class)).collect(Collectors.toSet());

        return bookDTOSet;
    }

    // get books filter By Author And Sort By BookName
    public Set<BookDTO> getFilteredBooksByAuthorAndSortedByBookName(Long authorId)
    {
//        Book book = this.bookRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", authorId));
        Set<Book> books = this.bookRepository.filterByAuthorAndSortByBookName(authorId);
        Set<BookDTO> bookDTOSet = books.stream().map(book1 -> this.modelMapper.map(book1, BookDTO.class)).collect(Collectors.toSet());
        return bookDTOSet;
    }

}
