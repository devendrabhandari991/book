package com.service.book.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.book.service.enity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

	List<Books> findByDeletedFalse();

	@Query("select b from Books b where b.bookId = ?1 and deleted = false ")
	Optional<Books> findByIdAndDeletedFalse(Long bookId);

}
