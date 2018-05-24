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
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users --user admin@gmail.com:admin
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
            "url": "http://localhost:8080/voiting-system/rest/admin/users",
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
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin
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
        curl -s -X DELETE <имя хоста>/rest/admin/users/<ID пользователя> --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url": "http://localhost:8080/voiting-system/rest/admin/users/100016",
            "type": "DATA_NOT_FOUND",
            "detail": "ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100016"
        }


### Работа с профилем
 
#### Получить профиль
    Формат запроса:
        curl -s <имя хоста>/rest/profile --user <логин>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/profile --user user@yandex.ru:password
    Пример ответа:
        {
           "id":100000,
           "name":"User",
           "email":"user@yandex.ru",
           "enabled":true,
           "registered":"2018-05-24T12:32:43.436+0000",
           "roles":["ROLE_USER"]
        }
 
#### Обновить профиль
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '<Описание пользователя в формате JSON>' <имя хоста>/rest/profile --user <логин>:<пароль>
        Описание пользователя в формате JSON:
            {"name":"<Имя пользователя>","email":"<E-mail>","password":"<Пароль>"}
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass"}' http://localhost:8080/voiting-system/rest/profile --user user@yandex.ru:password
    Пример ответа:
        Ничего не возвращается
  
#### Удалить профиль
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/profile --user <логин>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/profile --user user@yandex.ru:password
    Пример ответа:
        Ничего не возвращается


### Работа со справочником ресторанов

#### Получить список ресторанов
    Формат запроса:
        curl -s <имя хоста>/rest/restaurants --user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin
    Пример ответа:
        [
            {
                "id":100002,
                "name":"1Утюг"
            },
            {
                "id":100003,
                "name":"2Шашлык"
            }
        ]

#### Получить ресторан по ID
    Формат запроса:
        curl -s <имя хоста>/rest/admin/restaurants/<ID ресторана> --user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002 --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100002,
            "name":"1Утюг"
        }

#### Добавить ресторан
    Формат запроса:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название ресторана>"}' <имя хоста>/rest/admin/restaurants --user <логин администратора>:<пароль>
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"3New"}' http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100015,
            "name":"3New"
        }
    
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants",
            "type":"APP_ERROR",
            "detail":"javax.validation.ConstraintViolationException: Validation failed for classes [ru.askir.voitingsystem.model.Restaurant] during persist time for groups [javax.validation.groups.Default, ]\nList of constraint violations:[\n\tConstraintViolationImpl{interpolatedMessage='не может быть пусто', propertyPath=name, rootBeanClass=class ru.askir.voitingsystem.model.Restaurant, messageTemplate='{javax.validation.constraints.NotBlank.message}'}\n]"
        }

#### Обновить ресторан
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название ресторана>"}' <имя хоста>/rest/admin/restaurants/<ID ресторана> -- user <логин администратора>:<пароль>
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"4Update"}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается


#### Удалить ресторан
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/admin/restaurants/<ID пользователя> --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100002 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100012",
            "type":"DATA_NOT_FOUND",
            "detail":"ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100012"
        }

### Работа со справочником блюд

#### Получить список блюд
    Формат запроса:
        curl -s <имя хоста>/rest/admin/restaurants/<ID ресторана>/menu/<Дата меню>/dishes --user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes --user admin@gmail.com:admin
    Пример ответа:
        [
            {
                "id":100018,
                "name":"Hamberger",
                "price":50.00
            }
        ]

#### Получить блюдо по ID
    Формат запроса:
        curl -s <имя хоста>/rest/admin/restaurants/<ID ресторана>/menu/<Дата меню>/dishes/<ID блюда> --user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100018 --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100018,
            "name":"Hamberger",
            "price":50.00
        }
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100019",
            "type":"DATA_NOT_FOUND",
            "detail":"ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100019"
        }

#### Добавить блюдо
    Формат запроса:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название блюда>","price":<Цена>}' <имя хоста>/rest/admin/restaurants --user <логин администратора>:<пароль>
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":50.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100018,
            "menu": {
                    "id":100015,
                    "dateSet":"2018-05-23"
                    },
            "name":"Hamberger",
            "price":50.00
        }
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes",
            "type":"DATA_ERROR",
            "detail":"org.hsqldb.HsqlException: integrity constraint violation: unique constraint or index violation: DISHES_UNIQUE_ID_MENU_NAME_IDX"
        }

#### Обновить блюдо
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название блюда>","price":<Цена>}' <имя хоста>/rest/admin/restaurants/100003/menu/2018-05-23/dishes/<ID блюда> -- user <логин администратора>:<пароль>
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":50.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100018 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается

#### Удалить блюдо
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100015 --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100015 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100015",
            "type":"DATA_NOT_FOUND",
            "detail":"ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100015"
        }

### Работа с голосованием

#### Получить список ресторанов с меню и количеством голосов          
    Формат запроса:
        curl -s <имя хоста>/rest/voites
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/voites
    Пример ответа:
        [
            {
                "restaurantId":100002,
                "restaurantName":"1Утюг",
                "menuId":100004,
                "countVoices":2,
                "dishes":
                    [
                        {
                            "id":100008,
                            "name":"1Борщ",
                            "price":50.00
                        },
                        {
                            "id":100009,
                            "name":"2Котлета",
                            "price":30.00
                        },
                        {
                            "id":100010,
                            "name":"3Компот",
                            "price":20.50
                        }
                    ]
            },
            {
                "restaurantId":100003,
                "restaurantName":"2Шашлык",
                "menuId":100006,
                "countVoices":0
            }
        ]

#### Получить список ресторанов с меню и количеством голосов на дату          
    Формат запроса:
        curl -s <имя хоста>/rest/voites/<дата>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/voites/2018-04-07
    Пример ответа:
        [
            {
                "restaurantId":100002,
                "restaurantName":"1Утюг",
                "menuId":100004,
                "countVoices":2,
                "dishes":
                    [
                        {
                            "id":100008,
                            "name":"1Борщ",
                            "price":50.00
                        },
                        {
                            "id":100009,
                            "name":"2Котлета",
                            "price":30.00
                        },
                        {
                            "id":100010,
                            "name":"3Компот",
                            "price":20.50
                        }
                    ]
            },
            {
                "restaurantId":100003,
                "restaurantName":"2Шашлык",
                "menuId":100006,
                "countVoices":0
            }
        ]

#### Проголосовать
    Формат запроса:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" <имя хоста>/rest/voites/<ID меню> --user <логин>:<пароль>
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" http://localhost:8080/voiting-system/rest/voites/100004 --user user@yandex.ru:password
    Пример ответа:
        Ничего не возвращается
        
