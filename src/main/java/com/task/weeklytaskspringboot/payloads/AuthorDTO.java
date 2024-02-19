package com.task.weeklytaskspringboot.payloads;

import com.task.weeklytaskspringboot.entities.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {
    private Long authorId;

    @NotEmpty(message = "Author name should not be empty!!")
    private String authorName;

    private Set<BookDTO> books = new HashSet<>();

}
