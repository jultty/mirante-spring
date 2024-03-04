# client can create an account
POST http://localhost:8888/account
{
    "registration": "jc000001",
    "name": "Tania Wolfgramm",
    "email": "tania@mirante.dev",
    "password": "xyz6060",
    "course": "LBDI4"
}

HTTP 201
