package com.serivce.book.dto;

public class PublishingHouseDTO {

  private Long publishingId;

  private String name;

  public Long getPublishingId() {
    return publishingId;
  }

  public void setPublishingId(Long publishingId) {
    this.publishingId = publishingId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "PublishingHouseDTO [publishingId=" + publishingId + ", name=" + name + "]";
  }

}
