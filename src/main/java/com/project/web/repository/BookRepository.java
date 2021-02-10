package com.project.web.repository;

import com.project.web.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByUserId(Long userId);
    BookEntity getById(Long bookId);
}
