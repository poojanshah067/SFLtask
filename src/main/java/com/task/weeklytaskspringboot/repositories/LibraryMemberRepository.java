package com.task.weeklytaskspringboot.repositories;

import com.task.weeklytaskspringboot.entities.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember,Long> {
}
