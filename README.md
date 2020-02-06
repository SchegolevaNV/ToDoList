````$xslt
1. Показать весь список дел:
http://127.0.0.1:8080/deals/
method: GET
````
````$xslt
2. Добавить дело
http://127.0.0.1:8080/deals/?name=<<name>>&date=<<date>>
method: POST
````
```$xslt
3. Удалить дело
http://127.0.0.1:8080/deals/{id}
method: DELETE
```
````$xslt
4. Очистить весь список
http://127.0.0.1:8080/deals/all
method: DELETE
````
````$xslt
5. Показать конкретное дело по id
http://127.0.0.1:8080/deals/{id}
method: GET
````
````$xslt
6. Обновить существующую запись по id
http://127.0.0.1:8080/deals/{id}/?name=<<name>>&date=<<date>>
method: PUT
params (required): name, date
````