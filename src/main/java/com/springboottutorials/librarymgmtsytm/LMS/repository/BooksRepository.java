package com.springboottutorials.librarymgmtsytm.LMS.repository;

import com.springboottutorials.librarymgmtsytm.LMS.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Long> {
}
