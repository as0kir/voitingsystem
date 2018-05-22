### Работа с пользователями

#### Получить список пользователей
    Формат запроса:
        curl -s <имя хоста>/rest/admin/users -- user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/users --user admin@gmail.com:admin
    Пример ответа:
        [
            {
                "id": 100001,
                "name": "Admin",
                "email": "admin@gmail.com",
                "enabled": true,
                "registered": "2018-05-22T13:26:42.393+0000",
                "roles": [
                    "ROLE_ADMIN"
                ]
            },
            {
                "id": 100000,
                "name": "User",
                "email": "user@yandex.ru",
                "enabled": true,
                "registered": "2018-05-22T13:26:42.393+0000",
                "roles": [
                    "ROLE_USER"
                ]
            }
        ]

#### Получить пользователя по ID
    Формат запроса:
        curl -s <имя хоста>/rest/admin/users/<ID пользователя> --user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin
    Пример ответа:
        {
            "id": 100001,
            "name": "Admin",
            "email": "admin@gmail.com",
            "enabled": true,
            "registered": "2018-05-22T13:26:42.393+0000",
            "roles": [
                "ROLE_ADMIN"
            ]
        }

#### Добавить пользователя
    Формат запроса:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '<Описание пользователя в формате JSON>' <имя хоста>/rest/admin/users -- user <логин администратора>:<пароль>
        Описание пользователя в формате JSON:
            {"name":"<Имя пользователя>","email":"<E-mail>","password":"<Пароль>","enabled":<Доступность (true/false)>,"roles":["<Перечень ролей>"]}
        Перечень ролей:
            ADMIN, USER
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' <имя хоста>/rest/admin/users --user admin@gmail.com:admin
    Пример ответа:
        {
            "id": 100016,
            "name": "New",
            "email": "new@gmail.com",
            "enabled": true,
            "registered": "2018-05-22T13:39:01.328+0000",
            "roles": [
                "ROLE_USER"
            ]
        }
    
    Пример ответа в случае ошибки: 
        {
            "url": "<имя хоста>/rest/admin/users",
            "type": "VALIDATION_ERROR",
            "detail": "email User with this email already exists"
        }

#### Обновить пользователя
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '<Описание пользователя в формате JSON>' <имя хоста>/rest/admin/users/<ID пользователя> -- user <логин администратора>:<пароль>
        Описание пользователя в формате JSON:
            {"name":"<Имя пользователя>","email":"<E-mail>","password":"<Пароль>","enabled":<Доступность (true/false)>,"roles":["<Перечень ролей>"]}
        Перечень ролей:
            ADMIN, USER
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' <имя хоста>/rest/admin/users/100001 --user admin@gmail.com:admin
    Пример ответа:
        {
            "id": 100001,
            "name": "Admin",
            "email": "admin@gmail.com",
            "enabled": true,
            "registered": "2018-05-22T13:26:42.393+0000",
            "roles": [
                "ROLE_ADMIN"
            ]
        }


#### Удалить пользователя
    Формат запроса:
        curl -s <имя хоста>/rest/admin/users/<ID пользователя> --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE <имя хоста>/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url": "<имя хоста>/rest/admin/users/100016",
            "type": "DATA_NOT_FOUND",
            "detail": "ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100016"
        }

