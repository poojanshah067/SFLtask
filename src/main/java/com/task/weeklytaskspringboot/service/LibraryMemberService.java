package com.task.weeklytaskspringboot.service;

import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.entities.LibraryMember;
import com.task.weeklytaskspringboot.exceptions.ResourceNotFoundException;
import com.task.weeklytaskspringboot.payloads.BookDTO;
import com.task.weeklytaskspringboot.payloads.LibraryMemberDTO;
import com.task.weeklytaskspringboot.repositories.BookRepository;
import com.task.weeklytaskspringboot.repositories.LibraryMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryMemberService {
    @Autowired
    LibraryMemberRepository libraryMemberRepository;

    @Autowired
    ModelMapper modelMapper;

    //create Librarymember
    public LibraryMemberDTO createLibraryMember(LibraryMemberDTO libraryMemberDTO)
    {
        LibraryMember libraryMember = this.modelMapper.map(libraryMemberDTO, LibraryMember.class);
        LibraryMember savedLibraryMember = this.libraryMemberRepository.save(libraryMember);
        return this.modelMapper.map(savedLibraryMember, LibraryMemberDTO.class);
    }

    //update LibraryMember
    public LibraryMemberDTO updateLibraryMember(LibraryMemberDTO libraryMemberDTO, Long libraryMemberId)
    {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("LibraryMember", "id", libraryMemberId));

        libraryMember.setLibraryMemberName(libraryMemberDTO.getLibraryMemberName());

        LibraryMember updatedLibraryMember = this.libraryMemberRepository.save(libraryMember);
        return this.modelMapper.map(updatedLibraryMember,LibraryMemberDTO.class);

    }

    //get LibraryMember
    public List<LibraryMemberDTO> getAllLibraryMembers()
    {
        List<LibraryMember> allLibraryMembers = this.libraryMemberRepository.findAll();
        List<LibraryMemberDTO> libraryMemberDTOList = allLibraryMembers.stream()
                .map((libraryMember) -> this.modelMapper.map(libraryMember, LibraryMemberDTO.class))
                .collect(Collectors.toList());

        return libraryMemberDTOList;
    }

    //get Librarymember By Id
    public LibraryMemberDTO getLibraryMemberById(Long libraryMemberId)
    {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("LibraryMember", "id", libraryMemberId));
        return this.modelMapper.map(libraryMember,LibraryMemberDTO.class);
    }

    // delete libraryMember by Id
    public void deleteLibraryMemberById(Long libraryMemberId)
    {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("LibraryMember", "id", libraryMemberId));
        this.libraryMemberRepository.delete(libraryMember);
    }
}
