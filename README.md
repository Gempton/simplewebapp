# simplewebapp

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Additional information](#additional-information)

## General info
This project is Spring Boot application which represent REST service.

## Technologies
Project is created with:
* Spring Boot 2.4.0
* Java 1.8
* Maven 3
* JUnit 5
* Swagger 2
* Lombok
* Postgresql

## Setup
```
git clone https://github.com/Gempton/simplewebapp.git
cd ../simplewebapp
mvn install
mvn clean package
```
Then you should open file application.properties and change line <br />
From
```
spring.datasource.initialization-mode=always
```
To
```
spring.datasource.initialization-mode=never
```
Then
```
mvn spring-boot:run
```
### If you want to run tests
```
mvn test
```

## Additional information
SWAGGER URL <br />
http://localhost:8080/swagger-ui.html

### Body for request
```
{
    "firstName": "Petr",
    "lastName": "Ivanov",
    "jobTitle": "programmer",
    "dateOfBirth": "1999-10-03",
    "gender": "MALE",
    "departmentId": 1
}
```
