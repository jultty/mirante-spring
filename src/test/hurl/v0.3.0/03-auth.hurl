# client can get a token with its credentials
POST http://localhost:8889/auth/token
{
    "registration": "jc000001",
    "password": "xyz6060"
}

HTTP 201
[Captures]
token: jsonpath "$.token"

# account endpoint returns 403 if unauthenticated
GET http://localhost:8889/account
HTTP 403

# account endpoint returns 200 if authenticated
GET http://localhost:8889/account
Authorization: Basic {{token}}
HTTP 200
