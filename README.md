Приложение написано на Java 21, Spring boot 3, PostgresSQL 15

Сборка артефакта
```
./gradlew clean build 
```

Запуск артефакта. при запуске необходимо указать адрес БД, логин и пароль с указанием jar файла
```
java -jar -Dspring.datasource.url=jdbc:postgresql://localhost:5432/highload -Dspring.datasource.username=highload -Dspring.datasource.password=highload highload-0.0.1-SNAPSHOT.jar
```