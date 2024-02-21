package com.task.weeklytaskspringboot.service;

import com.task.weeklytaskspringboot.entities.Author;
import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.entities.LibraryMember;
import com.task.weeklytaskspringboot.exceptions.ResourceNotFoundException;
import com.task.weeklytaskspringboot.mappings.BookMapper;
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
    BookMapper bookMapper;

    @Autowired
    ModelMapper modelMapper;

    //create Book
    public BookDTO createBook(BookDTO bookDTO, Long authorId)
    {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", authorId));

        Book book = bookMapper.toEntity(bookDTO);
        book.setAuthor(author);
        Book savedBook = this.bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    //issue book by member
    public BookDTO issueBookByLibraryMember(Long bookId, Long libraryMemberId)
    {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("Member", "Id", libraryMemberId));
        Book book = this.bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","id",bookId));
        book.setLibraryMember(libraryMember);
        Book savedBook = this.bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    //update Book
    public BookDTO updateBook(BookDTO bookDTO, Long bookId)
    {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setBookName(bookDTO.getBookName());
        book.setBookDescription(bookDTO.getBookDescription());

        Book updatedBook = this.bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    //get Book
    public List<BookDTO> getAllBooks()
    {
        List<Book> allBooks = this.bookRepository.findAll();
        List<BookDTO> bookDTOList = allBooks.stream()
                .map((book) -> bookMapper.toDTO(book))
                .collect(Collectors.toList());

        return bookDTOList;
    }

    //get Book By Id
    public BookDTO getBookById(Long bookId)
    {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        return bookMapper.toDTO(book);
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

        Set<BookDTO> bookDTOSet = byAuthor.stream().map(book -> bookMapper.toDTO(book)).collect(Collectors.toSet());

        return bookDTOSet;
    }

    // get books filter By Author And Sort By BookName
    public Set<BookDTO> getFilteredBooksByAuthorAndSortedByBookName(Long authorId)
    {
//        Book book = this.bookRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", authorId));
        Set<Book> books = this.bookRepository.filterByAuthorAndSortByBookName(authorId);
        Set<BookDTO> bookDTOSet = books.stream().map(book1 -> bookMapper.toDTO(book1)).collect(Collectors.toSet());
        return bookDTOSet;
    }

    public Long countBookByLibraryMemberId(Long libraryMemberId)
    {
        return this.bookRepository.countBookByLibraryMemberId(libraryMemberId);
    }

}
