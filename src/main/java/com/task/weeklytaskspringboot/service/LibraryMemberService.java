package com.task.weeklytaskspringboot.service;

import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.entities.LibraryMember;
import com.task.weeklytaskspringboot.exceptions.ResourceNotFoundException;
import com.task.weeklytaskspringboot.mappings.LibraryMemberMapper;
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
    BookRepository bookRepository;

    @Autowired
    LibraryMemberMapper libraryMemberMapper;

    @Autowired
    ModelMapper modelMapper;

    //create Librarymember
    public LibraryMemberDTO createLibraryMember(LibraryMemberDTO libraryMemberDTO) {
        LibraryMember libraryMember = libraryMemberMapper.toEntity(libraryMemberDTO);
        LibraryMember savedLibraryMember = this.libraryMemberRepository.save(libraryMember);
        return libraryMemberMapper.toDTO(savedLibraryMember);
    }

    //update LibraryMember
    public LibraryMemberDTO updateLibraryMember(LibraryMemberDTO libraryMemberDTO, Long libraryMemberId) {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("LibraryMember", "id", libraryMemberId));

        libraryMember.setLibraryMemberName(libraryMemberDTO.getLibraryMemberName());

        LibraryMember updatedLibraryMember = this.libraryMemberRepository.save(libraryMember);
        return libraryMemberMapper.toDTO(updatedLibraryMember);

    }

    //get LibraryMember
    public List<LibraryMemberDTO> getAllLibraryMembers() {
        List<LibraryMember> allLibraryMembers = this.libraryMemberRepository.findAll();
        List<LibraryMemberDTO> libraryMemberDTOList = allLibraryMembers.stream()
                .map((libraryMember) -> libraryMemberMapper.toDTO(libraryMember))
                .collect(Collectors.toList());

        return libraryMemberDTOList;
    }

    //get Librarymember By Id
    public LibraryMemberDTO getLibraryMemberById(Long libraryMemberId) {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("LibraryMember", "id", libraryMemberId));
        return libraryMemberMapper.toDTO(libraryMember);
    }

    // delete libraryMember by Id
    public void deleteLibraryMemberById(Long libraryMemberId) {
        LibraryMember libraryMember = this.libraryMemberRepository.findById(libraryMemberId).orElseThrow(() -> new ResourceNotFoundException("LibraryMember", "id", libraryMemberId));
        if (libraryMember != null) {

            for (Book book : libraryMember.getMemberBooks()) {
                book.setLibraryMember(null);
                bookRepository.save(book);
            }
            this.libraryMemberRepository.delete(libraryMember);
        }
    }
}
