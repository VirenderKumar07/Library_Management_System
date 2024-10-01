package com.springboottutorials.librarymgmtsytm.LMS.repository;

import com.springboottutorials.librarymgmtsytm.LMS.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
