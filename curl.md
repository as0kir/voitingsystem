### Users

#### get all users
    curl -s http://localhost:8080/voiting-system/rest/admin/users --user admin@gmail.com:admin

#### get user
    curl -s http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin

#### create user
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users --user admin@gmail.com:admin

#### update user
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users/100015 --user admin@gmail.com:admin

#### delete user
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/users/100015 --user admin@gmail.com:admin

### Profile

#### register profile
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass"}' http://localhost:8080/voiting-system/rest/profile

#### get profile
    curl -s http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass

#### update profile
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Not new","email":"new@gmail.com","password":"newPass"}' http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass

#### delete profile
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass


### Restaurants

#### get all restaurants
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin

#### get restaurant
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002 --user admin@gmail.com:admin

#### create restaurant
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"3New"}' http://localhost:8080/voiting-system/rest/admin/restaurants --user admin@gmail.com:admin

#### update restaurant
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"4Update"}' http://localhost:8080/voiting-system/rest/admin/restaurants/100017 --user admin@gmail.com:admin

#### delete restaurant
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100017 --user admin@gmail.com:admin


### Dishes

#### get all dishes
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/2018-04-07/dishes --user admin@gmail.com:admin
    
#### get dish
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/2018-04-07/dishes/100008 --user admin@gmail.com:admin

#### create dish
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":50.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes --user admin@gmail.com:admin

#### update dish
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Hamberger","price":150.00}' http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100019 --user admin@gmail.com:admin

#### delete dish
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100003/menu/2018-05-23/dishes/100015 --user admin@gmail.com:admin

### Voites

#### get all 
    curl -s http://localhost:8080/voiting-system/rest/voites
    
#### get all for date    
    curl -s http://localhost:8080/voiting-system/rest/voites/2018-04-07

#### create voite
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" http://localhost:8080/voiting-system/rest/voites/100004 --user user@yandex.ru:password
    