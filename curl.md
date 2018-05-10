### Users

#### get All Users
    curl -s http://localhost:8080/voiting-system/rest/admin/users

#### get User 100001
    curl -s http://localhost:8080/voiting-system/rest/admin/users/100001

#### delete User
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/admin/users/100001

#### create User
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2018-05-02T07:45:34.895+0000","roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users

#### update User
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2018-05-02T07:45:34.895+0000","roles":["ROLE_USER"]}' http://localhost:8080/voiting-system/rest/admin/users/100001


### Restaurants

#### get All restaurants
    curl -s http://localhost:8080/voiting-system/rest/restaurants

#### get Restaurant 100002
    curl -s http://localhost:8080/voiting-system/rest/restaurants/100002

#### delete Restaurant
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/restaurants/100002

#### create Restaurant
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"3New"}' http://localhost:8080/voiting-system/rest/restaurants

#### update Restaurant
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"4Update"}' http://localhost:8080/voiting-system/rest/restaurants/100003



### Menu

#### get All menus
    curl -s http://localhost:8080/voiting-system/rest/restaurants/100002/menus

#### get Menu 100002
    curl -s http://localhost:8080/voiting-system/rest/restaurants/100002/menus/100004

#### delete Menu
    curl -s -X DELETE http://localhost:8080/voiting-system/rest/restaurants/100002/menus/100004

#### create Menu
    curl -s -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"dateSet":"2018-04-09"}' http://localhost:8080/voiting-system/rest/restaurants/100002/menus

#### update Menu
    curl -s -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"dateSet":"2018-04-10"}' http://localhost:8080/voiting-system/rest/restaurants/100002/menus/100004


#### Форматированный вывод 
   | python -m json.tool