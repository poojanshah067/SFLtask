package com.task.weeklytaskspringboot.mappings;

import com.task.weeklytaskspringboot.entities.LibraryMember;
import com.task.weeklytaskspringboot.payloads.LibraryMemberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMemberMapper {
    LibraryMemberDTO toDTO(LibraryMember libraryMember);
    LibraryMember toEntity(LibraryMemberDTO libraryMemberDTO);
}
