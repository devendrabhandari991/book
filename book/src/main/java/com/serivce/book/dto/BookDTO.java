package com.serivce.book.dto;

import java.util.Date;
import java.util.List;

public class BookDTO {

  private Long bookId;

  private Integer isbn;

  private CategoryDTO categoryDTO;

  private PublishingHouseDTO publishingHouseDTO;

  private List<AuthorDTO> authors;

  private String title;

  private Date publishedDate;

  private Date created;

  private Date updated;

  private Boolean deleted;

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Integer getIsbn() {
    return isbn;
  }

  public void setIsbn(Integer isbn) {
    this.isbn = isbn;
  }

  public CategoryDTO getCategoryDTO() {
    return categoryDTO;
  }

  public void setCategoryDTO(CategoryDTO categoryDTO) {
    this.categoryDTO = categoryDTO;
  }

  public PublishingHouseDTO getPublishingHouseDTO() {
    return publishingHouseDTO;
  }

  public void setPublishingHouseDTO(PublishingHouseDTO publishingHouseDTO) {
    this.publishingHouseDTO = publishingHouseDTO;
  }

  public List<AuthorDTO> getAuthors() {
    return authors;
  }

  public void setAuthors(List<AuthorDTO> authors) {
    this.authors = authors;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "BookDTO [bookId=" + bookId + ", isbn=" + isbn + ", categoryDTO=" + categoryDTO
        + ", publishingHouseDTO=" + publishingHouseDTO + ", authors=" + authors + ", title=" + title
        + ", publishedDate=" + publishedDate + ", created=" + created + ", updated=" + updated
        + ", deleted=" + deleted + "]";
  }

}
