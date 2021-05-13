## Инструкция по запуску (рабочий вариант)


### Работа с MySQL

Запуск приложения - команда: java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar

Запуск тестов - команда: gradlew test -Durl.dbase=jdbc:mysql://localhost:3306/app

### Работа с Postgres 

Запуск приложения - команда: java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar aqa-shop.jar

Запуск тестов - команда: gradlew test -Durl.dbase=jdbc:postgresql://localhost:5432/postgres
