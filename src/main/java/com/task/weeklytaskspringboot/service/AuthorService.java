package com.task.weeklytaskspringboot.service;

import com.task.weeklytaskspringboot.entities.Author;
import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.exceptions.ResourceNotFoundException;
import com.task.weeklytaskspringboot.mappings.AuthorMapper;
import com.task.weeklytaskspringboot.payloads.AuthorDTO;
import com.task.weeklytaskspringboot.repositories.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AuthorMapper authorMapper;

    //create author
    public AuthorDTO createAuthor(AuthorDTO authorDTO)
    {
        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = this.authorRepository.save(author);
        return authorMapper.toDTO(savedAuthor);
    }

    //update Author
    public AuthorDTO updateAuthor(AuthorDTO authorDTO, Long authorId)
    {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        author.setAuthorName(authorDTO.getAuthorName());

        Author updatedAuthor = this.authorRepository.save(author);
        return authorMapper.toDTO(updatedAuthor);

    }

    //get Author
    public List<AuthorDTO> getAllAuthors()
    {
        List<Author> allAuthors = this.authorRepository.findAll();
        List<AuthorDTO> authorDTOList = allAuthors.stream()
                                                    .map((author) -> authorMapper.toDTO(author))
                                                    .collect(Collectors.toList());

        return authorDTOList;
    }

    //get Author By Id
    public AuthorDTO getAuthorById(Long authorId)
    {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
        return authorMapper.toDTO(author);
    }

    //delete author by Id
    public void deleteAuthorById(Long authorId)
    {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
        this.authorRepository.delete(author);
    }
}
