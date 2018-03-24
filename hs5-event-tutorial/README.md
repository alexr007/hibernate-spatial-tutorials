This tutorial code is based on the [Hibernate Spatial 4 tutorial](http://www.hibernatespatial.org/documentation/02-Tutorial/01-tutorial4/),
but adapted to work with Hibernate 5.3 and DB2.

To get this tutorial code working on your system you need to
change the settings in the `src/resources/META-INF/persistence.xml` file to correspond
with your setup.

On the first run you may want to enable the `javax.persistence.schema-generation.database.action` property so that the database schema is (re)generated.


The application has two commands "store" and "find". You can run them as

```
$ mvn compile
$ mvn exec:java -Dexec.mainClass="event.EventManager" -Dexec.args="store POINT(10 5)"
$ mvn exec:java -Dexec.mainClass="event.EventManager" -Dexec.args="find POLYGON((1 1,20 1,20 20,1 20,1 1))"
```
