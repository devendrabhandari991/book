package com.service.book.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.serivce.book.dto.AuthorDTO;
import com.serivce.book.dto.BookDTO;
import com.serivce.book.dto.CategoryDTO;
import com.serivce.book.dto.PublishingHouseDTO;
import com.service.book.service.enity.Author;
import com.service.book.service.enity.Books;
import com.service.book.service.enity.Category;
import com.service.book.service.enity.PublishingHouse;

public class Utility {

	public static boolean validateRequest(BookDTO bookDTO) {
		if(StringUtils.isEmpty(bookDTO.getTitle()) || StringUtils.isEmpty(bookDTO.getIsbn())) {
			return false;
		}
		return true;
	}

	public static Books createBookEntity(BookDTO bookDTO) {
		Books book = new Books();
		BeanUtils.copyProperties(bookDTO,book);
		book.setBookId(null);
		book.setCreated(new Date());
		book.setUpdated(new Date());
		return book;
	}

	public static Category createCategoryEnitity(CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO,category);
		category.setCategoryId(null);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		return category;
	}

	public static PublishingHouse createPublishingEntity(PublishingHouseDTO publishingHouseDTO) {
		PublishingHouse publishingHouse = new PublishingHouse();
		BeanUtils.copyProperties(publishingHouseDTO,publishingHouse);
		publishingHouse.setPublishingId(null);
		publishingHouse.setCreated(new Date());
		publishingHouse.setUpdated(new Date());
		return publishingHouse;
	}

	public static List<Author> createAuthorEnitity(List<AuthorDTO> authors,Books book) {
		List<Author> authorList = new ArrayList<>();
		for(AuthorDTO authorDTO : authors) {
			Author author = new Author();
			BeanUtils.copyProperties(authorDTO,author);
			author.setAuthorId(null);
			author.setCreated(new Date());
			author.setUpdated(new Date());
			author.setBooks(book);
			authorList.add(author);
		}
		return authorList;
	}

	public static boolean isUpdateRequired(BookDTO bookDTO) {
		if(bookDTO.getCategoryDTO() != null && !StringUtils.isEmpty(bookDTO.getCategoryDTO().getCategoryName())) {
			return true;
		}
		if(bookDTO.getPublishingHouseDTO() != null && !StringUtils.isEmpty(bookDTO.getPublishingHouseDTO().getName())) {
			return true;
		}
		if(!CollectionUtils.isEmpty(bookDTO.getAuthors())) {
			boolean isUpdateRequired = false;
			for(AuthorDTO authorDTO : bookDTO.getAuthors()) {
				if(!StringUtils.isEmpty(authorDTO.getName())) {
					isUpdateRequired = true;
				}
			}
			return isUpdateRequired;
		}
		return false;
	}

}
