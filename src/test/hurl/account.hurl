# client can create an account
POST http://localhost:8888/account
{
    "username": "wtania",
    "name": " Tania Wolfgramm",
    "email": "tania@mirante.dev",
    "password": "xyz6060"
}

HTTP 201

# a GET to a non-existing account returns 404
GET http://localhost:8888/account/nonexistent
HTTP 404

# client can modify an account through another POST
POST http://localhost:8888/account
{
    "username": "wtania",
    "name": "Doctor Tania Wolfgramm",
    "email": "tania.wolfgramm@mirante.dev",
    "password": "xyz8888"
}

HTTP 201
[Asserts]
jsonpath "$.name" == "Doctor Tania Wolfgramm"
jsonpath "$.email" == "tania.wolfgramm@mirante.dev"

GET http://localhost:8888/account/wtania
HTTP 200
[Asserts]
jsonpath "$.name" == "Doctor Tania Wolfgramm"
jsonpath "$.email" == "tania.wolfgramm@mirante.dev"

# an account that was created can be deleted
POST http://localhost:8888/account
{
    "username": "heafield",
    "name": "Margaret Hamilton",
    "email": "me.hamilton@mirante.dev",
    "password": "xzx1010"
}

HTTP 201
[Asserts]
jsonpath "$.name" == "Margaret Hamilton"
jsonpath "$.email" == "me.hamilton@mirante.dev"

GET http://localhost:8888/account/heafield
HTTP 200
# DELETE http://localhost:8888/account/heafield
# HTTP 200
# GET http://localhost:8888/account/heafield
# HTTP 404
