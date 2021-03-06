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
            "id": 100015,
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
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users/100015 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url": "http://localhost:8080/voiting-system/rest/admin/users/100015",
            "type": "VALIDATION_ERROR",
            "detail": "User with this email already exists"
        }

#### Удалить пользователя
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/admin/users/<ID пользователя> --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/users/100015 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url": "http://localhost:8080/voiting-system/rest/admin/users/100016",
            "type": "DATA_NOT_FOUND",
            "detail": "ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100016"
        }


### Работа с профилем

#### Зарегистрироваться
    Формат запроса:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '<Описание пользователя в формате JSON>' <имя хоста>/rest/profile
        Описание пользователя в формате JSON:
            {"name":"<Имя пользователя>","email":"<E-mail>","password":"<Пароль>"}
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass"}' http://localhost:8080/voiting-system/rest/profile
    Пример ответа:
        {
            "id":100016,
            "name":"New",
            "email":"new@gmail.com",
            "enabled":true,
            "registered":"2018-05-25T01:01:38.703+0000",
            "roles":["ROLE_USER"]
        }
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/profile",
            "type":"VALIDATION_ERROR",
            "detail":"email User with this email already exists"
        }

#### Получить профиль
    Формат запроса:
        curl -s <имя хоста>/rest/profile --user <логин>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass
    Пример ответа:
        {
            "id": 100016,
            "name": "New",
            "email": "new@gmail.com",
            "enabled": true,
            "registered": "2018-05-25T23:20:03.077+0000",
            "roles": [
                "ROLE_USER"
            ]
        }
 
#### Обновить профиль
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '<Описание пользователя в формате JSON>' <имя хоста>/rest/profile --user <логин>:<пароль>
        Описание пользователя в формате JSON:
            {"name":"<Имя пользователя>","email":"<E-mail>","password":"<Пароль>"}
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Not new","email":"new@gmail.com","password":"newPass"}' http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/profile",
            "type":"VALIDATION_ERROR",
            "detail":"email User with this email already exists"
        }
  
#### Удалить профиль
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/profile --user <логин>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass
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
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100012",
            "type":"DATA_NOT_FOUND",
            "detail":"ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100012"
        }    

#### Добавить ресторан
    Формат запроса:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название ресторана>"}' <имя хоста>/rest/admin/restaurants --user <логин администратора>:<пароль>
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"3New"}' http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100017,
            "name":"3New"
        }
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants",
            "type":"VALIDATION_ERROR",
            "detail":"name не может быть пусто"
        }
    
#### Обновить ресторан
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название ресторана>"}' <имя хоста>/rest/admin/restaurants/<ID ресторана> -- user <логин администратора>:<пароль>
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"4Update"}' http://localhost:8080/voiting-system/rest/admin/restaurants/100017 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100017",
            "type":"VALIDATION_ERROR",
            "detail":"Restaurant with this name already exists"
        }

#### Удалить ресторан
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/admin/restaurants/<ID пользователя> --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100017 --user admin@gmail.com:admin
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
        curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/2018-04-07/dishes --user admin@gmail.com:admin
    Пример ответа:
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

#### Получить блюдо по ID
    Формат запроса:
        curl -s <имя хоста>/rest/admin/restaurants/<ID ресторана>/menu/<Дата меню>/dishes/<ID блюда> --user <логин администратора>:<пароль>
    Пример:
        curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/2018-04-07/dishes/100008 --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100008,
            "name":"1Борщ",
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
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название блюда>","price":<Цена>}' <имя хоста>/rest/admin/restaurants/<ID ресторана>/menu/<Дата меню>/dishes --user <логин администратора>:<пароль>
    Пример:
        curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":50.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes --user admin@gmail.com:admin
    Пример ответа:
        {
            "id":100019,
            "menu":
                {
                    "id":100018,
                    "restaurant":
                        {
                            "id":100003,
                            "name":"2Шашлык"
                        },
                    "dateSet":"2018-05-26"
                },
            "name":"Hamberger",
            "price":50.00
        }
    Пример ответа в случае ошибки:
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes",
            "type":"VALIDATION_ERROR",
            "detail":"Dish with this name already exists in this menu"
        }

#### Обновить блюдо
    Формат запроса:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"<Название блюда>","price":<Цена>}' <имя хоста>/rest/admin/restaurants/<ID ресторана>/menu/<Дата меню>/dishes/<ID блюда> -- user <логин администратора>:<пароль>
    Пример:
        curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":150.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100019 --user admin@gmail.com:admin
    Пример ответа:
        Ничего не возвращается
    Пример ответа в случае ошибки:        
        {
            "url":"http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100026",
            "type":"DATA_NOT_FOUND",
            "detail":"ru.askir.voitingsystem.util.exception.NotFoundException: Not found entity with id=100026"
        }

#### Удалить блюдо
    Формат запроса:
        curl -s -X DELETE <имя хоста>/rest/admin/restaurants/<ID ресторана>/menu/<Дата меню>/dishes/<ID блюда> --user <логин администратора>:<пароль>
    Пример:
        curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100019 --user admin@gmail.com:admin
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
    Пример ответа в случае ошибки:        
        {
            "url":"http://localhost:8080/voiting-system/rest/voites/100004",
            "type":"VALIDATION_ERROR",
            "detail":"ru.askir.voitingsystem.util.exception.IllegalRequestDataException: Not allow operation"
        }
