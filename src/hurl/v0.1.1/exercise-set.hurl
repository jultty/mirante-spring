# client can create an exercise
POST http://localhost:8888/exercise-set
{
    "id": "exset0001T",
    "name": "first test exercise set"
}

HTTP 201

POST http://localhost:8888/exercise-set
{
    "id": "exset0002T",
    "name": "second test exercise set"
}

HTTP 201

POST http://localhost:8888/exercise-set
{
    "id": "exset0003T",
    "name": "third test exercise set"
}

HTTP 201
