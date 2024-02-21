package com.task.weeklytaskspringboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library_members")
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

    public Long getLibraryMemberId() {
        return libraryMemberId;
    }

    public void setLibraryMemberId(Long libraryMemberId) {
        this.libraryMemberId = libraryMemberId;
    }

    public String getLibraryMemberName() {
        return libraryMemberName;
    }

    public void setLibraryMemberName(String libraryMemberName) {
        this.libraryMemberName = libraryMemberName;
    }

    public Set<Book> getMemberBooks() {
        return memberBooks;
    }

    public void setMemberBooks(Set<Book> memberBooks) {
        this.memberBooks = memberBooks;
    }
}
