package com.serivce.book.dto;

import java.util.Date;

public class AuthorDTO {

  private Long authorId;

  private String name;

  private Date created;

  private Date updated;

  private Boolean deleted;

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    return "AuthorDTO [authorId=" + authorId + ", name=" + name + ", created=" + created
        + ", updated=" + updated + ", deleted=" + deleted + "]";
  }
}
