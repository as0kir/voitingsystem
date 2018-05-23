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
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2018-05-02T07:45:34.895+0000","roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users/100001 --user admin@gmail.com:admin


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



### Menu

#### get All menu
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu --user admin@gmail.com:admin

#### get Menu 100002
    curl -s http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/100004 --user admin@gmail.com:admin

#### delete Menu
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/100004 --user admin@gmail.com:admin

#### create Menu
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"dateSet":"2018-04-09"}' http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu --user admin@gmail.com:admin

#### update Menu
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"dateSet":"2018-04-10"}' http://localhost:8080/voiting-system/rest/admin/restaurants/100002/menu/100004 --user admin@gmail.com:admin


#### Форматированный вывод 
   | python -m json.tool
