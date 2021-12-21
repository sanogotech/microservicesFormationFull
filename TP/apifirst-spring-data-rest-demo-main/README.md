# apifirst-spring-data-rest-demo

- https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
- https://www.baeldung.com/spring-data-rest-customize-http-endpoints
- https://spring.io/guides/tutorials/rest/
- https://mkyong.com/spring-boot/spring-boot-spring-data-elasticsearch-example/

- https://dbdiagram.io/d

## API Doc
- http://localhost:8080/api/

## DB  InMemory

http://localhost:8080/h2web/

# findBy* on @RepositoryRestResource

```
List<Personne> findByNom(@Param("nom") String nom);

//URL pour tester
 - http://localhost:8080/personnes/search/findByNom?nom=dalton
```



```
@RestResource(path="nom")
List<Personne> findByNomContains(@Param("nom") String nom,Pageable pageable);

//URL pour tester
- http://localhost:8080/personnes/search/nom?nom=i&page=0&size=2
```

## Response Status
- POST: 201
- GET:  200 or 404
- DELETE: 204
# Run
mvn clean  spring-boot:run
