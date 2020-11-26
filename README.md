# hiberblog

Hiberblog to prototypowa aplikacja do tworzenia własnego bloga.
Technologie, które zostały użyte w aplikacji to: Java + Spring Boot. Są to technologie backendowe, które wykorzystałem do stworzenia api oraz HTML, CSS, JS z biblioteką React.js czyli technologie frontendowe, za pomocą których utworzyłem klienta http wraz z widokiem.

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

Klaza zawiera również dwa konstruktory:

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

https://hiberblog-api.herokuapp.com/swagger-ui.html#/article-api


