# client can get a token
GET http://localhost:8888/token
{
    "registration": "jc000001",
    "password": "xyz6060"
}

HTTP 200
[Captures]
token: jsonpath "$.token"
