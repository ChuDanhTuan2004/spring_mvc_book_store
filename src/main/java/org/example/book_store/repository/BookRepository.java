package org.example.book_store.repository;

import org.example.book_store.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByNameContaining (String name, Pageable pageable);
    Page<Book> findAll(Pageable pageable);
}
