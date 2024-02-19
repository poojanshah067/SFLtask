package com.task.weeklytaskspringboot.repositories;

import com.task.weeklytaskspringboot.entities.Author;
import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.entities.LibraryMember;
import com.task.weeklytaskspringboot.payloads.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
        Set<Book> findByAuthor(Author author);
        Set<Book> findByLibraryMember(LibraryMember libraryMember);

//        @Query(nativeQuery = true, value = "SELECT * FROM books as b LEFT JOIN authors as a ON b.author_id = a.id where b.author_id =?1 ORDER BY b.name")
        @Query(value = "SELECT b from Book as b LEFT JOIN Author as a on b.author.authorId = a.authorId where a.authorId = ?1 order by b.bookName")
        Set<Book> filterByAuthorAndSortByBookName(Long authorId);


}
