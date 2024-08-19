# cuenta-bancaria-api-root

Antecedentes
El proyecto está pensado para desplegar como un jar de forma independiente teniendo para 
esto el servidor de tomcat embebido, por lo que al construir se generara un jar. 
Además contiene el wrapper de maven en su version 3.8.6, asi pues puede construir usando 
mvn clean install en windows.

#Version Java: 11

#Version Maven: 3.8.6

#Version Motor DB: PostgreSql 14.4

Pasos para clonar el proyecto.
Clonar el proyecto cuenta-bancaria-api-root.
Ejecutar mvn clean package -Dmaven.test.skip
Version Jdk Java hasta la 17 
Para ejecutar:
1. `java -jar msvc-cliente-0.0.1-SNAPSHOT.jar.jar`
2. `java -jar -Dspring.profiles.active=CONSOLA msvc-cliente-0.0.1-SNAPSHOT.jar`  (En este caso spring boot el profile CONSOLA)
3. `java -jar msvc-cuenta-0.0.1-SNAPSHOT.jar.jar`
4. `java -jar -Dspring.profiles.active=CONSOLA msvc-cuenta-0.0.1-SNAPSHOT.jar`  (En este caso spring boot el profile CONSOLA)

Entregables:

*Repositorio del proyecto: https://github.com/paulpucha7389/cuenta-bancaria-api-root.git
*Script sql.
*Collection postman para pruebas de los webservices.