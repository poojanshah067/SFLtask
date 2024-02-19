package com.task.weeklytaskspringboot.controllers;

import com.task.weeklytaskspringboot.payloads.ApiResponse;
import com.task.weeklytaskspringboot.payloads.LibraryMemberDTO;
import com.task.weeklytaskspringboot.service.LibraryMemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraryMembers")
public class LibraryMemberController {

    @Autowired
    LibraryMemberService libraryMemberService;

    @PostMapping("/")
    public ResponseEntity<LibraryMemberDTO> createLibraryMember(@Valid @RequestBody LibraryMemberDTO libraryMemberDTO)
    {
        LibraryMemberDTO createLibraryMemberDTO = this.libraryMemberService.createLibraryMember(libraryMemberDTO);
        return new ResponseEntity<>(createLibraryMemberDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{libraryMemberId}")
    public ResponseEntity<LibraryMemberDTO> updateLibraryMember(@Valid @RequestBody LibraryMemberDTO libraryMemberDTO,@PathVariable Long libraryMemberId)
    {
        LibraryMemberDTO updatedLibraryMember = this.libraryMemberService.updateLibraryMember(libraryMemberDTO, libraryMemberId);
        return new ResponseEntity<>(updatedLibraryMember,HttpStatus.OK);
    }

    @GetMapping("/{libraryMemberId}")
    public ResponseEntity<LibraryMemberDTO> getLibraryMemberById(@PathVariable Long libraryMemberId)
    {
        LibraryMemberDTO getLibraryMemberDTO = this.libraryMemberService.getLibraryMemberById(libraryMemberId);
        return new ResponseEntity<>(getLibraryMemberDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<LibraryMemberDTO>> getAllLibraryMembers()
    {
        List<LibraryMemberDTO> getLibrarymemberDTOList = this.libraryMemberService.getAllLibraryMembers();
        return new ResponseEntity<>(getLibrarymemberDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{libraryMemberId}")
    public ResponseEntity<ApiResponse> deleteLibraryMember(@PathVariable Long libraryMemberId)
    {
        this.libraryMemberService.deleteLibraryMemberById(libraryMemberId);
        return new ResponseEntity<>(new ApiResponse("libraryMember deleted successfully",true),HttpStatus.OK);
    }



}
