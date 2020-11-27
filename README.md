# hiberblog

Hiberblog to prototypowa aplikacja do tworzenia własnego bloga.
Technologie, które zostały użyte w aplikacji to: Java 11 + Spring Boot. Są to technologie backendowe, które wykorzystałem do stworzenia api oraz HTML, CSS, JS z biblioteką React.js czyli technologie frontendowe, za pomocą których utworzyłem klienta http wraz z widokiem. Aplikacja hostowana jest na darmowym serwerze heroku.

## hiberblog-api

Hiberblog-api udostępnia 4 podstawowe metody CRUD takie jak:
* Wyświetlenie wszystkich artykułów.
* Dodanie nowego artykułu.
* Zmodyfikowanie artykułu.
* Usunięcie artykułu.

### Klasy hiberblog-api:

#### HiberblogApplication (pakiet główny)
Klasa uruchomieniowa z metodą main.
Zawiera adnotacje _@EnableSwagger2_, za pomocą której uruchamiany jest Swagger2 dependency, tworzący automatyczną dokumentację endpointów aplikacji.  

#### Start (pakiet główny)
Start jest to klasa zawierająca tylko jedną metodę *init()*. Metoda ta służy dodaniu kilku testowych artykułów do bazy danych przy pierwszym starcie aplikacji.
Zawiera adnotacja @EventListener(ApplicationReadyEvent.class), która powoduje natychmiastowe wykonanie metody po uruchomieniu aplikacji.

#### Article (pakiet model)
Article to klasa modelowa artykułu zawierająca takie pola jak:
* private articleId typu int.
* private title typu String.
* private text typu String.

Pole **articleId** zawiera adnotacje *@Id*, oraz *@GeneratedValue(strategy = GeneretionType.IDENTITY)*. 
Te dwie adnotacje pochodzące z JPA oznaczają iż pole oznaczone tymi adnotacjami jest kluczem głównym, dodatkowo generowanym automatycznie od zera i inkrementowanym o 1 zgodnie ze strategią IDENTITY.

Klasa zawiera również dwa konstruktory:

* bezparametrowy:
```
public Article() {}
```
* parametrowy tylko z dwoma parametrami, ze względu na automatyczne generowanie pola articleId.
```
public Article( String title, String text) {
    this.title = title;
    this.text = text;
  }
```
posiada również generowane automatycznie metody *get* oraz *set* dla wszystkich pól z wyjątkiem setArticleId, gdyż pole to nie może być ustawiane ręcznie i domyślną metodę toString.

#### HelpId (pakiet model)
Klasa pomocnicza, zawiera jedno pole articleId wraz z metodą get, używana tylko w metodzie deleteArticle

#### Interface ArticleRepo (pakiet repository)

Interfejs rozszerzający *JpaRepository* podając jednocześnie typ obiektu czyli *Article*, oraz używany typ klucza głównego *Long*.
Posiada również odpowiednią adnotację *@Repository*.

JpaRepository udostępnia szereg gotowych narzędzi do współpracy z bazami danych, pozwala na oznaczenie klasy modelowej jako encje, na podstawie której utwarzana jest tabela w samej bazie danych oraz oznaczenie pól encji adnotacjami takimi jak @Id czy @GeneratedValue, które zostały uzyte również w tej aplikacji. 

Kolejnym narzędziem do komunikacji z bazami danych oferowanym przez JpaRepository są proste gotowe metody do wykorzystania takie jak:

* *findAll()* używana do pobierania wszystkich elementów danej tabeli.
* *save(obiekt)* używana do dodawania elementu do bazy danych

a także pozwala na "układanie" z gotowych opcji bardziej skomplikowanych metod jak:
* *deleteArticlesByArticleId(id)*

Ponadto pozwala za pomocą adnotacji *@Modifying* tworzyć własne metody:
```
 @Modifying
   @Query(value = "UPDATE article SET article.title = :title, article.text =:text WHERE article.article_id = :id",nativeQuery = true)
   void updateArticle(@Param("id")int id,@Param("title")String title, @Param("text")String text);
```
#### ArticleApi (pakiet api)
Klasa wystawiająca endpointy z metodami *Get*, *Post*, *Put* oraz *Delete* wstrzykiwanymi do konstruktora przez obiekt interfejsu *ArticleRepo*. Co ciekawe Spring wykonuje tą operacje automatycznie, nie wymagając nawet adnotacji *@Autowired*, jednak prawdopodobnie byłoby lepiej oznaczać miejsce wstrzyknięcia.

Więcej o endpointach aplikacji, wraz z możliwością ich przetestowania w wygenerowanej dokumentacji *swagger*:
#### UWAGA!
W metodzie post o endpoincie */add* nie podaje się pola articleId.

https://hiberblog-api.herokuapp.com/swagger-ui.html#/article-api

ArticleApi zawiera również 3 adnotacje:
* *@RestController* - Adnotacja pozwalająca na wystawienie endpointów API różniąca się od adnotacji *@Controller* tym, iż nie wymaga ręcznego dodawania adnotacji @ResponseBody w przypadku gdy zwracany obiekt jest automatycznie pakowany do formatu JSONa.
* *@CrossOrigin* - jest to zaimplementowanie polityki przesyłania danych CORS pomiędzy różnymi Originami, umożliwa to aplikacji frontendowej komunikację z API.
* *@Transactional* - adnotacja ustawiona nad klasą powoduje otwarcie i zamknięcie transakcji dla każdej publicznej metody tej klasy. 

#### Plik application.properties
Plik zawiera konfiguracje połączenia z bazą danych.

#### Link do api:
https://hiberblog-api.herokuapp.com


