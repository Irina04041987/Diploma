### Дипломный проект "Автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка " 
Программа предназначена для автоматизированного тестирования сервиса по покупке тура с помощью дебетовой карты или при оплате с помощью кредита.

### Документация по проекту 
  * [План по автоматизации тестирования](https://github.com/Irina04041987/Diploma/blob/master/Diploma_Reports/Plan.md)
  * [Отчет по итогам тестирования](https://github.com/Irina04041987/Diploma/blob/master/Diploma_Reports/Report.md)
  * [Отчет по итогам автоматизации](https://github.com/Irina04041987/Diploma/blob/master/Diploma_Reports/Summary.md)

     
### Необходимое окружение: 
 * установленный  Docker; 
 * свободные  порты  8080, 9999 и 5432 или 3306.

### Инструкции по запуску 
1. Скачайте репозиторий.
2. Запустите контейнер, в котором разворачиваются базы данных (далее БД) и симулятор платежной системы : 
 `docker-compose up `.
4. Запустить приложение командой :  
  3.1. Работа с MySQL : `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`  
  3.2. Работа с Postgres : `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar aqa-shop.jar`
5. Запустить тестирование:  
  4.1. Работа с MySQL : `gradlew test -Durl.dbase=jdbc:mysql://localhost:3306/app`  
  4.2. Работа с Postgres : `gradlew test -Durl.dbase=jdbc:postgresql://localhost:5432/postgres`

