POST http://localhost:8888/account
{
    "username": "anab",
    "name": " Ana B.",
    "email": "anab@ifsp.edu.br",
    "password": "xxyz"
}

HTTP 201

POST http://localhost:8888/exercise-set
{
    "id": "exercise-set-0001",
    "name": "exercise set one"
}

HTTP 201

POST http://localhost:8888/exercise
{
    "id": "exercise-0001",
    "set_id": "exercise-set-0001",
    "instruction": "The pillars of OOP are:"
}

HTTP 201

POST http://localhost:8888/option
{
    "id": "option-0001-A",
    "content": "Polymorphism",
    "place": 1,
    "correct": true,
    "exercise_id": "exercise-0001"
}

HTTP 201

POST http://localhost:8888/option
{
    "id": "option-0001-B",
    "content": "Inheritance",
    "place": 2,
    "correct": true,
    "exercise_id": "exercise-0001"
}

HTTP 201

POST http://localhost:8888/option
{
    "id": "option-0001-C",
    "content": "Encapsulation",
    "place": 3,
    "correct": true,
    "exercise_id": "exercise-0001"
}

HTTP 201

POST http://localhost:8888/option
{
    "id": "option-0001-D",
    "content": "Static Members",
    "place": 4,
    "correct": false,
    "exercise_id": "exercise-0001"
}

HTTP 201

POST http://localhost:8888/option
{
    "id": "option-0001-E",
    "content": "Interfaces",
    "place": 5,
    "correct": false,
    "exercise_id": "exercise-0001"
}

HTTP 201
