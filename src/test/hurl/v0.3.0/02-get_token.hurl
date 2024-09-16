# client can get a token with its credentials
POST http://localhost:8889/auth/token
{
    "registration": "jc000001",
    "password": "xyz6060"
}

HTTP 201

# wrong password returns a 400 error
POST http://localhost:8889/auth/token
{
    "registration": "jc000001",
    "password": "xyz6061"
}

HTTP 400

# wrong registration returns a 400 error
POST http://localhost:8889/auth/token
{
    "registration": "jc100001",
    "password": "xyz6060"
}

HTTP 400
