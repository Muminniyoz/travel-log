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
    params: start_date - starting date format dd.MM.YYYY
    params: end_date - ending date format dd.MM.YYYY
    params: reg_num - vehicle registration number(any part of symbols)
    params: ownder - owner of vehicle (any part of symbols)
    

```

The backend is done with
- Spring Boot 0.5.0.M5
- Spring 4.0.0.M3
- Hibernate 4.2.1
- Spring MVC, Spring Data JPA, Spring security and so on.

There is embedded tomcat and embedded, in-memory hsql inside.

There is a standard set of libs, like guava, joda-time and so on.

Backend is done with Java 7 and Groovy (you can safely mix both as long as you keep your sources in src/main/groovy folder), without any xml. Tests are written in groovy using Spock framework and one with Spring MVC (had to test if my view resolver is working, and that's only possible with spring Dispatcher/Front Controller).

Frontend is using old version of AngularJS, but it's just so you can fire it up and see it works. I used the
templating from scotch.io <http://scotch.io> but changed it out a bit.

A nice tutorial is here: http://scotch.io/tutorials/javascript/single-page-apps-with-angularjs-routing-and-templating

