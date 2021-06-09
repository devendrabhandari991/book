package com.service.book.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.book.service.enity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
