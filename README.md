Первый endpoint - GET запрос по ссылке http://localhost:8080/users
Пример полученных данных: 

[
  {
    "id": 3,
    "surname": "Pitt",
    "name": "Brad",
    "middleName": "William",
    "email": "pitt@gmail.com",
    "role": "SALE_USER"
  },
............ и так далее по списку
]
Список выводиться по странично по 10 элементов списка. Для получения второй страницы - запрос делается с параметром page ---> http://localhost:8080/users?page=2

Второй endpoint - POST запрос по ссылке http://localhost:8080/users С телом запроса (как пример): 

{
"surname": "Brad",
"name": "Brad",
"middleName": "William",
"email": "pitt@gmail.com",
"role": "ADMINISTRATOR"
}

Скрипты создания БД находятся ---> src/main/resources/user_service_db.sql 
Конфигурация пути к БД находится -----> src/main/resources/application.properties
