package com.task.weeklytaskspringboot.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class LibraryMemberDTO {

    private Long libraryMemberId;

    @NotEmpty(message = "Library member name should not be empty!!")
    private String libraryMemberName;

    private Set<BookDTO> memberBooks = new HashSet<>();

}
