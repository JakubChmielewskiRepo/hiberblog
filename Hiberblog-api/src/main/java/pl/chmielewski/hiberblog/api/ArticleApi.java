package pl.chmielewski.hiberblog.api;

import org.springframework.web.bind.annotation.*;
import pl.chmielewski.hiberblog.model.Article;
import pl.chmielewski.hiberblog.model.HelpId;
import pl.chmielewski.hiberblog.repository.ArticleRepo;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@Transactional
public class ArticleApi {





        private ArticleRepo articleRepo;

    public ArticleApi(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @GetMapping("/all")
        public Iterable<Article> getAll() {
            return articleRepo.findAll();
        }

        @PostMapping("/add")
        public Article addArticle(@RequestBody Article article) {
            return articleRepo.save(article);
        }

        @PutMapping("/update")
    public void updateArticle(@RequestBody Article article) {
        articleRepo.updateArticle(article.getArticleId(), article.getTitle(), article.getText());
    }

        @DeleteMapping("/delete")
        public void deleteArticle(@RequestBody HelpId helpId) {
            articleRepo.deleteArticlesByArticleId(helpId.getArticleId());
        }
    }


