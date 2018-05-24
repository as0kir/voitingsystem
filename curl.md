### Users

#### get All Users
    curl -s http://localhost:8080/voiting-system/rest/admin/users --user admin@gmail.com:admin

#### get User 100001
    curl -s http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin

#### delete User
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin

#### create User
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2018-05-02T07:45:34.895+0000","roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users --user admin@gmail.com:admin

#### update User
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2018-05-02T07:45:34.895+0000","roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users/100000 --user admin@gmail.com:admin


### Profile

#### get Profile
    curl -s http://localhost:8080/voiting-system/rest/profile --user admin@gmail.com:admin

#### delete Profile
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/profile --user admin@gmail.com:admin

#### update Profile
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass"}' http://localhost:8080/voiting-system/rest/profile --user admin@gmail.com:admin


### Restaurants

#### get All restaurants
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin

#### get Restaurant 100002
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002 --user admin@gmail.com:admin

#### delete Restaurant
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100002 --user admin@gmail.com:admin

#### create Restaurant
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"3New"}' http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin

#### update Restaurant
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"4Update"}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003 --user admin@gmail.com:admin



### Dishes

#### get All dishes
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes --user admin@gmail.com:admin

#### get dish 100018
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100018 --user admin@gmail.com:admin

#### delete dish
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100015 --user admin@gmail.com:admin

#### create dish
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":50.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes --user admin@gmail.com:admin

#### update dish
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":50.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100018 --user admin@gmail.com:admin


### Voites

#### create voite
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" http://localhost:8080/voiting-system/rest/voites/100004 --user user@yandex.ru:password

#### get All 
    curl -s http://localhost:8080/voiting-system/rest/voites
    
#### get All for date    
    curl -s http://localhost:8080/voiting-system/rest/voites/2018-04-07


#### Форматированный вывод 
   | python -m json.tool
