package com.service.book.IService;

import java.util.List;

import com.serivce.book.dto.BookDTO;

public interface BookService {

  public List<BookDTO> getAllBooks();

  public BookDTO getBookById(Long bookId);

  public boolean addBook(BookDTO bookDTO);

  public boolean deleteBook(Long bookId);

  public boolean updateBook(BookDTO bookDTO);

}
