# ZeDeliveryTest
Teste de seleção para a Zé Delivery 

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6.2](https://maven.apache.org)
- [MySQL 8.0](https://dev.mysql.com/doc/)

## Before run the application, set up the database

In the application.properties located in src/main/resources set up the username and password of your local MySQL. After that, run the following SQL in your MySQL Console:

`CREATE DATABASE IF NOT EXISTS zedeliverytest;`

## Running the application locally

Execute the `main` method in the `br.com.zedeliverytest.ZeDeliveryTestApplication` class from your IDE.
