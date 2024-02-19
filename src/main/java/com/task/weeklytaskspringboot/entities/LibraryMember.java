package com.task.weeklytaskspringboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library_members")
@Getter
@Setter
@NoArgsConstructor
public class LibraryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long libraryMemberId;

    @Column(name = "name")
    private String libraryMemberName;

    @OneToMany(mappedBy = "libraryMember")
    private Set<Book> memberBooks = new HashSet<>();

}
