package pl.chmielewski.hiberblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.chmielewski.hiberblog.model.Article;


@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {

    @Modifying
   @Query(value = "UPDATE article SET article.title = :title, article.text =:text WHERE article.article_id = :id",nativeQuery = true)
   void updateArticle(@Param("id")int id,@Param("title")String title, @Param("text")String text);

    public void deleteArticlesByArticleId(int articleId);

//    @Modifying
//    @Query("update Article a set a.title = ?1, a.text = ?2 where a.article_id = ?3")
//    void updateArticleInfoById(String title, String text, Long article_id);
}
