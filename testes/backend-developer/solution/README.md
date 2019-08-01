## Place Api 


## Getting Started

Commands sequences

-- compiling the app

```
 mvn package 
```

-- Ways to startup app

* 2.1 here is the Heroku documentation reference

-- https://devcenter.heroku.com/articles/getting-started-with-java#deploy-the-app

``` 
Heroku local web	
```
 
* 2.2 Running Java compiled package 

```
java -jar ./target/backend-developer-0.0.1-SNAPSHOT.jar  
```

The application link will be available in the link below.

* http://localhost:8080/v1/places

Documentation link 

* http://localhost:8080/swagger-ui.html#/place-controller

## Api links

* ** List place by name ** 
- GET http://localhost:8080/v1/places/place/pina


* ** List place by id 1 ** 
- GET http://localhost:8080/v1/places/1


* **Create place ** 
- POST http://localhost:8080/v1/places/1


* **Update place ** 
- PUT http://localhost:8080/v1/places/1


* **Delete place by id 1 ** 
- DELETE http://localhost:8080/v1/places/1


### Prerequisites 

Java 8


## Author

* **Felipe Oliveira Pina**