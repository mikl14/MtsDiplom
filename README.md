# Дипломный Проект Финтех Академии МТС Банка

Этот проект представляет собой дипломную работу, выполненную в рамках обучения в Финтех Академии МТС Банка. Он включает в себя три сервиса, взаимодействующие между собой посредством REST API.
Все 3 сервиса для удобства представлены в этом репозитории.


## Функционал

- **Управление Базой Данных**: Проект обеспечивает работу с базой данных пользователей, их персональных счетов и вкладов, используя PostgreSQL в качестве СУБД. <img src="https://www.postgresql.org/media/img/about/press/elephant.png" width="20" height="20">
- **Технологии**: Для взаимодействия с базой данных используется Spring Data JPA, а для аутентификации пользователей — Spring Security.<img src="https://spring.io/img/projects/spring-data.svg" width="20" height="20"> <img src="https://spring.io/img/projects/spring-security.svg" width="20" height="20">
- **Система Миграции**: Для управления версиями базы данных используется Flyway, который позволяет автоматически обновлять схему базы данных посредством миграций  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Flyway_logo.svg/330px-Flyway_logo.svg.png" width="20" height="20">
- **Графический Интерфейс**: Для организации работы с графическим интерфейсом используется Thymeleaf.  <img src="https://www.thymeleaf.org/images/thymeleaf.png" width="20" height="20">
- **Тестирование**: Функционал проекта покрыт JUnit тестами, с использованием Mockito. <img src="https://junit.org/junit5/assets/img/junit5-logo.png" width="20" height="20">

## Краткое описание

- **Custumer Service**: обеспечивает полный цикл работы с аккаунтами пользователей в базе данных.
  - **возможности**:
    - **Получение баланса кошелька пользователя** 
    - **Изменение баланса кошелька пользователя**
    - **Получение истории операций пользователя**
    - **Добавление операции в историю операций пользователя**
    - **Получения данных для аунтификации пользователя**
      
- **Agregator Service**: (diplomMts) обеспечивает работу пользовательского интерфейса, а так же работает с базой данных вкладов пользователей.
  - **возможности**:
    - **Работа в графическом интерфейсе доступного на localhost:8090** 
    - **Аунтификация пользователей по логину и паролю**
    - **Добавление и удаление вкладов**

### Ввиду ограничености времени на решение задачи, сервис Deposit не активен, так как его функционал фактически выполняет Agregator Service

## Начало работы

## Требования
- **Java 11**
- **Gradle**
- **Postgres**

## Сборка проекта
- Клонируйте репозиторий 
- Выставите в application.properties данные вашей базы psql
- Выполните сборку и запустите сервисы **Agregator Service**: (diplomMts) и **Custumer Service**
- Перейдите в графический интерфейс на http://localhost:8090


## Графическая реализация
<img src="https://github.com/mikl14/MtsDiplom/assets/107858531/36eb5fa1-2fba-47ba-9923-3c4669418a52"  width="400vh" height="300vh"> 
<img src="https://github.com/mikl14/MtsDiplom/assets/107858531/c81ec748-498b-4cef-9d1f-df2750c59f90"  width="400vh" height="300vh"> 
<img src="https://github.com/mikl14/MtsDiplom/assets/107858531/90ebeba0-1a4c-4db7-be37-1d49d7dacf95"  width="400vh" height="300vh"> 
<img src="https://github.com/mikl14/MtsDiplom/assets/107858531/54195b2d-efc9-4017-bf26-f560ff8872cf"  width="400vh" height="300vh"> 
<img src="https://github.com/mikl14/MtsDiplom/assets/107858531/adcf392b-1b67-45a5-b4d6-2273dc5ed95b"  width="300vh" height="100vh"> 
