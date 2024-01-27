# client can get a token
GET http://localhost:8888/token
{
    "registration": "jc000001",
    "password": "xyz6060"
}

HTTP 200
[Captures]
# 9604fd45-5ee0-495c-8658-3cab91f8d882
token: regex /token:([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})/
