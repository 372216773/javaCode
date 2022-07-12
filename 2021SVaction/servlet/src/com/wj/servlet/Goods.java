package com.wj.servlet;

public class Goods {

  private String id;
  private String title;
  private String description;
  private long price;
  private String image;
  private String spec;
  private String detail;
  private String categoryId;
  private java.sql.Timestamp gmtCreate;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }


  public java.sql.Timestamp getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(java.sql.Timestamp gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  @Override
  public String toString() {
    return "Goods{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", image='" + image + '\'' +
            ", spec='" + spec + '\'' +
            ", detail='" + detail + '\'' +
            ", categoryId='" + categoryId + '\'' +
            ", gmtCreate=" + gmtCreate +
            '}';
  }
}
