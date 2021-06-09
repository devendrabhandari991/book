package com.service.book.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.serivce.book.dto.AuthorDTO;
import com.serivce.book.dto.BookDTO;
import com.serivce.book.dto.CategoryDTO;
import com.serivce.book.dto.PublishingHouseDTO;
import com.service.book.IService.BookService;
import com.service.book.misc.Utility;
import com.service.book.service.enity.Author;
import com.service.book.service.enity.Books;
import com.service.book.service.enity.Category;
import com.service.book.service.enity.PublishingHouse;
import com.service.book.service.repository.AuthorRepository;
import com.service.book.service.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public List<BookDTO> getAllBooks() {
		try {
			List<Books> books = bookRepository.findByDeletedFalse();
			return covertEnityToDTO(books);
		} catch (Exception e) {
			log.error("Exception occurr while getting books info {}",e.getStackTrace()[0]);
			return new ArrayList<>();
		}
	}

	private List<BookDTO> covertEnityToDTO(List<Books> books) {
		List<BookDTO> bookDTOs = new ArrayList<>();
		for(Books book:books) {
			BookDTO bookDTO = new BookDTO();
			BeanUtils.copyProperties(book, bookDTO);
			
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(book.getCategoryId(), categoryDTO);
			
			PublishingHouseDTO publishingHouseDTO = new PublishingHouseDTO();
			BeanUtils.copyProperties(book.getPublishingHouseId(), publishingHouseDTO);
			
			List<AuthorDTO> authorDTOs = new ArrayList<>();
			if(!CollectionUtils.isEmpty(book.getAuthors())) {
				for(Author author : book.getAuthors()) {
					AuthorDTO authorDTO = new AuthorDTO();
					BeanUtils.copyProperties(author, authorDTO);
					authorDTOs.add(authorDTO);
				}
			}
			bookDTO.setAuthors(authorDTOs);
			bookDTO.setCategoryDTO(categoryDTO);
			bookDTO.setAuthors(authorDTOs);
			bookDTO.setPublishingHouseDTO(publishingHouseDTO);
			bookDTOs.add(bookDTO);
		}
		return bookDTOs;
	}

	@Override
	public BookDTO getBookById(Long bookId) {
		BookDTO bookDTO = new BookDTO();
		Optional<Books> book= bookRepository.findByIdAndDeletedFalse(bookId);
		if(book.isPresent()) {
			bookDTO = convertEnitityToDTO(book.get(), bookDTO,book.get().getAuthors());
		}
		return bookDTO;
	}

	private BookDTO convertEnitityToDTO(Books book, BookDTO bookDTO, List<Author> authors) {
		BeanUtils.copyProperties(book, bookDTO);
		
		CategoryDTO categoryDTO = new CategoryDTO();
		BeanUtils.copyProperties(book.getCategoryId(), categoryDTO);
		
		PublishingHouseDTO publishingHouseDTO = new PublishingHouseDTO();
		BeanUtils.copyProperties(book.getPublishingHouseId(), publishingHouseDTO);
		
		List<AuthorDTO> authorDTOs = new ArrayList<>();
		if(!CollectionUtils.isEmpty(book.getAuthors())) {
			for(Author author : authors) {
				AuthorDTO authorDTO = new AuthorDTO();
				BeanUtils.copyProperties(author, authorDTO);
				authorDTOs.add(authorDTO);
			}
		}
		bookDTO.setAuthors(authorDTOs);
		bookDTO.setCategoryDTO(categoryDTO);
		bookDTO.setAuthors(authorDTOs);
		bookDTO.setPublishingHouseDTO(publishingHouseDTO);
		return bookDTO;
	}

	@Override
	public boolean addBook(BookDTO bookDTO) {
		try {
			Books book = Utility.createBookEntity(bookDTO);
			if(bookDTO.getCategoryDTO() != null) {
				Category category = Utility.createCategoryEnitity(bookDTO.getCategoryDTO());
				book.setCategoryId(category);
			}
			if(bookDTO.getPublishingHouseDTO() != null) {
				PublishingHouse publishingHouse = Utility.createPublishingEntity(bookDTO.getPublishingHouseDTO());
				book.setPublishingHouseId(publishingHouse);
			}
			Books bookObj = bookRepository.save(book);
			if(!CollectionUtils.isEmpty(bookDTO.getAuthors())) {
				List<Author> authors = Utility.createAuthorEnitity(bookDTO.getAuthors(),bookObj);
				authorRepository.saveAll(authors);
			}
			bookDTO.setBookId(bookObj.getBookId());
			return true;
		} catch (Exception e) {
			log.error("Exception occurr while adding books info {}",e.getStackTrace()[0]);
		}
		return false;
	}

	@Override
	public boolean deleteBook(Long bookId) {
		try {
			Optional<Books> book = bookRepository.findById(bookId);
			if(book.isPresent()) {
				Books books = book.get();
				books.setDeleted(true);
				bookRepository.save(books);
				return true;
			}
		}catch(Exception e) {
			log.error("Exception occurr while deleting books info {}",e.getStackTrace()[0]);
		}
		return false;
	}

	@Override
	public boolean updateBook(BookDTO bookDTO) {
		try {
			if(Utility.isUpdateRequired(bookDTO)) {
				Optional<Books> books = bookRepository.findById(bookDTO.getBookId());
				if(books.isPresent()) {
					Books book = updatedBooksEnitity(books.get(),books.get().getAuthors(),bookDTO);
					bookRepository.save(book);
					return true;
				}
			}
		}catch(Exception e) {
			log.error("Exception occurr while updating books info {}",e.getStackTrace()[0]);
		}
		return false;
	}

	private Books updatedBooksEnitity(Books books, List<Author> authors, BookDTO bookDTO) {
		if(bookDTO.getCategoryDTO() != null && !StringUtils.isEmpty(bookDTO.getCategoryDTO().getCategoryName())) {
			books.getCategoryId().setCategoryName(bookDTO.getCategoryDTO().getCategoryName());
			books.getCategoryId().setUpdated(new Date());
		}
		if(bookDTO.getPublishingHouseDTO() != null && !StringUtils.isEmpty(bookDTO.getPublishingHouseDTO().getName())) {
			books.getPublishingHouseId().setName(bookDTO.getPublishingHouseDTO().getName());;
			books.getPublishingHouseId().setUpdated(new Date());
		}
		if(!CollectionUtils.isEmpty(bookDTO.getAuthors()) && !CollectionUtils.isEmpty(authors)) {
			for(Author author : books.getAuthors()) {
				for(AuthorDTO authorDTO : bookDTO.getAuthors()) {
					if(author.getAuthorId().equals(authorDTO.getAuthorId())) {
						if(!StringUtils.isEmpty(authorDTO.getName())) {
							author.setName(authorDTO.getName());
							author.setUpdated(new Date());
						}
					}
				}
			}
			books.setAuthors(authors);
		}
		return books;
	}
}
