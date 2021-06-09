package com.service.book.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.book.service.enity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
