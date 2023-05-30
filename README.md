##Description
Simple pizzeria survey app, for more details 
see task description in task.txt
###Implementation details
As Db I've chosen PosgresSQL, but you can change to any other DB, just update connection details 
& hibernate dialect in application.properties. Also, **generate-ddl** is enabled,
so schema should be created upon launch.
Project itself has 3 APIS:
1. Add/update user data, e.g.:
`curl --location --request PUT 'localhost:8080/api/v1/customer/survey' \
 --header 'Content-Type: application/json' \
 --data '{
     "email" : "Mizuki",
     "toppings" : ["Havaian", "Calzone"]
 }'`
 2. Retrieve statistics on toppings:
 `curl --location 'localhost:8080/api/v1/topping'`
 
 3. My favorite topping:
 `curl --location 'localhost:8080/api/v1/favorite/topping'`

####Here is a list of desirable features that I did not have time to implement.:
- add custom error handling, e.g. via ControllerAdvice
- make a separation on public/private API - e.g. hide ToppingController
behind authorization "wall" (via spring-security)
- add Swagger documentation
- create docker file with postgres/other db instance
- move db configuration to profiles 
- add email validation (e.g. via custom validator + regex)
- create testConfig for inetgration tests