package pl.chmielewski.hiberblog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Article {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int articleId;
  private String title;
  private String text;

  public Article() {
  }

  public Article( String title, String text) {
    this.title = title;
    this.text = text;
  }

  public int getArticleId() {
    return articleId;
  }

  


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "Article{" +
            "articleId=" + articleId +
            ", title='" + title + '\'' +
            ", text='" + text + '\'' +
            '}';
  }
}
