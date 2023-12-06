# client can create an account
POST http://localhost:8080/account
{
    "username": "janex",
    "name": "Jane Foobar",
    "email": "jane@mirante.dev",
    "password": "xyz6060"
}

HTTP 201

# client can modify an account through another POST
POST http://localhost:8080/account
{
    "username": "janex",
    "name": "Almirante Jane Foobar",
    "email": "alm.jane@mirante.dev",
    "password": "xyz8080"
}

HTTP 201
[Asserts]
jsonpath "$.name" == "Almirante Jane Foobar"
jsonpath "$.email" == "alm.jane@mirante.dev"

GET http://localhost:8080/account/janex
HTTP 200
[Asserts]
jsonpath "$.name" == "Almirante Jane Foobar"
jsonpath "$.email" == "alm.jane@mirante.dev"
