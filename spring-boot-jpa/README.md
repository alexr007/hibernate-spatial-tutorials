This is a Spring Boot RESTful service for searching and managing Events (happenings at a certain time and place).

It uses JPA and Hibernate Spatial to persist and retrieve Events.


Note
====

This is very much a work-in-progress, so check back regularly and see it grow.

Getting Started
===============

Prequisites:
 - at least Java JDK8
 - maven 3.x
 - A Postgresql 9.5 or higher with Postgis 2.x or higher


To get started:

 1. Clone the repo or download the source.

 2. Change the settings in the `src/resources/application.properties` file to correspond with your database setup.
    On the first run you may want to change the `spring.jpa.hibernate.ddl-auto` property to `create` so that the database schema
    is automatically (re)generated. See [here for more information](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html)

 3. To start the server in development mode, run
    ```
    $ mvn  spring-boot:run
    ```


The [Spring Boot Devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html)
are included so the code automatically should restart when the code is changed on the classpath (so after the project is rebuilt).

