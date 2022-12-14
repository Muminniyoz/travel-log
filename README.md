# Travel log system
==========================

This system is created for interview task:
<p>According to accounting rules, travel logs should be kept when a personal car is used for company </p>


# How to run it?

If you have gradle installed and under linux/mac:

    gradle runJar

If gradle is not installed, but still under linux/mac

    gradlew runJar

And for windows without gradle

    gradlew.bat runJar

After the server is running, go to

```
GET http://localhost:8080/api/travel_log - get all travel logs as Page
GET http://localhost:8080/api/travel_log/{id} - get specified  travel log by id
POST http://localhost:8080/api/travel_log - create new travel log
PUT http://localhost:8080/api/travel_log - update travel log
DELETE  http://localhost:8080/api/travel_log/{id} - remove specified  travel log by id

GET http://localhost:8080/api/travel_log/report - generate report
    params: startDate - starting date format dd.MM.YYYY
    params: endDate - ending date format dd.MM.YYYY
    params: vehicleRegNum - vehicle registration number(any part of symbols)
    params: vehicleOwner - owner of vehicle (any part of symbols)
    

```

The backend is done with

- Spring Boot 2.7.5
- ```groovy
	 implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
	 implementation 'org.springframework.boot:spring-boot-starter-web'
	 implementation 'org.springframework.boot:spring-boot-starter-validation'
	 implementation 'org.liquibase:liquibase-core'
	 implementation 'junit:junit:4.13.2'
	 runtimeOnly 'org.postgresql:postgresql'
	 runtimeOnly 'com.h2database:h2'
	 testImplementation 'org.springframework.boot:spring-boot-starter-test'
```