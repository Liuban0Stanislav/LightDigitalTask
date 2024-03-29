# LightDigital

### Тестовое задание

### [Условие задания](https://docs.google.com/document/d/19RBkDRhoSKJzJhRzIiy9G8rmBjuHJtyOycC-G3pw9GE/edit)

## Содержание

- [Что сделано](#что-сделано)
- [Структура проекта](#структура-проекта)
- [Описание проекта](#описание-проекта)
- [Используемые технологии](#используемые-технологии)
- [Над проектом работал](#над-проектом-работал)

## Что сделано
Проект выполнен с использованием SpringBoot.
В ходе работы реализованы следующие эндпоинты:

1. Операции с пользователями:
* GET-Посмотреть список ползователей.
* GET-Получить пользователя по его ID.
* PATCH-Изменить роль пользователя.

2. Операции с ававтаром:
* GET-Получить аватар пользователя по его ID.
* PATCH-Обновить аватар.

3. Операции с заявками:
* GET-Посмотреть все заявки с сортировкой по возрастанию.
* GET-Для USER. Посмотреть все заявки? созданные пользователем, сортированные по дате и статусу и с пагинацией.
* GET-Для OPERATOR. Посмотреть все отправленные на рассмотрение заявки с возможностью
  сортировки по дате создания в оба направления (как от самой старой к самой
  новой, так и наоборот) и пагинацией по 5 элементов. Есть фильтрация по имени.
* GET-Для OPERATOR. Просматривать отправленные заявки только конкретного пользователя по его
  имени/части имени.

4. Регистрация
5. Авторизация


## Структура проекта
Настоящий SpringBoot прект имеет классическую трехслойную структуру:
- слой контроллеров, содержащий эндпоинты;
- слой сервисов, содержащий бизнесс логику;
- слой DAO(Data Access Object) или же слой репозиториев;

База данных используемая в проекте PostgreSQL.

## Описание проекта
Для выполнения поставленной задачи, были созданы три сущности: 
* [User](https://github.com/Liuban0Stanislav/LightDigitalTask/blob/master/src/main/java/com/_lightdigitaltask/models/Photo.java)
* [Application](https://github.com/Liuban0Stanislav/LightDigitalTask/blob/master/src/main/java/com/_lightdigitaltask/models/Application.java)
* [Photo](https://github.com/Liuban0Stanislav/LightDigitalTask/blob/master/src/main/java/com/_lightdigitaltask/models/Photo.java)


В корневом каталоге проекта можно найти файл с экспортированной коллецией запросов Postman в формате JSON.

## Используемые технологии
Проект использует следующие технологии и библиотеки:

- [Spring Boot](https://spring.io/projects/spring-boot): Фреймворк для создания веб-приложений на языке Java.
- [Hibernate](https://hibernate.org/): Фреймворк для работы с базой данных.
- [PostgreSQL](https://www.postgresql.org/): База данных для разработки.
- [Postman](https://www.postman.com/) Инструменты для работы с эндпоинтами.

## Над проектом работал
- [Любань Станислав](https://github.com/Liuban0Stanislav)
