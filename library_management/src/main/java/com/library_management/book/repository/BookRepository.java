package com.library_management.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library_management.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{


}
