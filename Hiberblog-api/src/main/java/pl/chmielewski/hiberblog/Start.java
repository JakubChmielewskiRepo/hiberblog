package pl.chmielewski.hiberblog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.chmielewski.hiberblog.model.Article;
import pl.chmielewski.hiberblog.repository.ArticleRepo;

import javax.transaction.Transactional;



@Component
@Transactional
public class Start {
    private ArticleRepo articleRepo;

@Autowired
    public Start(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
}
        @EventListener(ApplicationReadyEvent.class)
                public void init() {
            Article artTadeusz = new Article("Pan Tadeusz.", "Pan Tadeusz to książka bardzo cudowna.");
            Article artKleks = new Article("Akademia Pana Kleksa.", "Pan kleks jest dziwny.");
            Article artMateusz = new Article("Ojciec Mateusz ma dziecko!<SKANDAL>", "Artur żmijewski, aktor grający postać Ojca Mateusza ma trójkę dzieci.");
//            articleRepo.save(artTadeusz);
//            articleRepo.save(artKleks);
//            articleRepo.save(artMateusz);



        }
}
