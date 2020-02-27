# ZeDeliveryTest
Teste de seleção para a Zé Delivery 

[Spring Boot](https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/getting-started-system-requirements.html) minimum requirements.

## Requirements for this App

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6.2](https://maven.apache.org)
- [MySQL 8.0](https://dev.mysql.com/doc/)

## Before run the application, set up the database

In the application.properties located in src/main/resources set up the username and password of your local MySQL. After that, run the following SQL in your MySQL Console:

`CREATE DATABASE IF NOT EXISTS zedeliverytest;`

## Running the application locally

Execute the `main` method in the `br.com.zedeliverytest.ZeDeliveryTestApplication` class from your IDE.

## Available Endpoints

1) Get Partner By Id (Method GET)
  `{Server}:{Port}/app/zedeliverytest/pdv/{id}`
  `Ex: localhost:8080/app/zedeliverytest/pdv/1`
  
2) Get Partners By Longitude and Latitude (Method GET)
  `{Server}:{Port}/app/zedeliverytest/pdv/lng/{lng}/lat/{lat}`
  `Ex: localhost:8080/app/zedeliverytest/pdv/lng/-46.720875/lat/-23.584986`
  
3) Create Partner (Method POST)
  `{Server}:{Port}/app/zedeliverytest/pdv`
  `Ex: localhost:8080/app/zedeliverytest/pdv`
  `Ex (Body): {
	"tradingName": "Adega Osasco",
    "ownerName": "Ze da Ambev",
    "document": "02.453.716/000110",
    "coverageArea": {
        "type": "Polygon",
        	"coordinates": [
                [
                   [
                      [
                         -43.36556,
                         -22.99669
                      ],
                      [
                         -43.36539,
                         -23.01928
                      ],
                      [
                         -43.26583,
                         -23.01802
                      ],
                      [
                         -43.25724,
                         -23.00649
                      ],
                      [
                         -43.23355,
                         -23.00127
                      ],
                      [
                         -43.2381,
                         -22.99716
                      ],
                      [
                         -43.23866,
                         -22.99649
                      ],
                      [
                         -43.24063,
                         -22.99756
                      ],
                      [
                         -43.24634,
                         -22.99736
                      ],
                      [
                         -43.24677,
                         -22.99606
                      ],
                      [
                         -43.24067,
                         -22.99381
                      ],
                      [
                         -43.24886,
                         -22.99121
                      ],
                      [
                         -43.25617,
                         -22.99456
                      ],
                      [
                         -43.25625,
                         -22.99203
                      ],
                      [
                         -43.25346,
                         -22.99065
                      ],
                      [
                         -43.29599,
                         -22.98283
                      ],
                      [
                         -43.3262,
                         -22.96481
                      ],
                      [
                         -43.33427,
                         -22.96402
                      ],
                      [
                         -43.33616,
                         -22.96829
                      ],
                      [
                         -43.342,
                         -22.98157
                      ],
                      [
                         -43.34817,
                         -22.97967
                      ],
                      [
                         -43.35142,
                         -22.98062
                      ],
                      [
                         -43.3573,
                         -22.98084
                      ],
                      [
                         -43.36522,
                         -22.98032
                      ],
                      [
                         -43.36696,
                         -22.98422
                      ],
                      [
                         -43.36717,
                         -22.98855
                      ],
                      [
                         -43.36636,
                         -22.99351
                      ],
                      [
                         -43.36556,
                         -22.99669
                      ]
                   ]
                ]
             ]
          },
   "address": {
     "type": "Point",
     "coordinates": [
        -43.297337,
        -23.013538
     ]
      }
}`
