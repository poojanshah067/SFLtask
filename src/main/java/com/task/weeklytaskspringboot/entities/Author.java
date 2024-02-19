package com.task.weeklytaskspringboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long authorId;

    @Column(name = "name")
    private String authorName;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

}
