# client can create an option
POST http://localhost:8080/option
{
    "id": "xyz0003",
    "content": "Lorem ipsum sit dolor amet",
    "place": 2,
    "correct": true,
    "exercise_id": "ex0001T"

}

HTTP 201

POST http://localhost:8080/option
{
    "id": "xyz0004",
    "place": 3,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0001T"

}

HTTP 201

POST http://localhost:8080/option
{
    "id": "xyz0005",
    "place": 1,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0001T"

}

HTTP 201

POST http://localhost:8080/option
{
    "id": "xyz0006",
    "place": 2,
    "correct": true,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0003T"

}

HTTP 201

POST http://localhost:8080/option
{
    "id": "xyz0007",
    "place": 1,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0003T"

}

HTTP 201

# exercise 4
POST http://localhost:8080/option
{
    "id": "xyz0048",
    "place": 1,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0004T"

}

HTTP 201
POST http://localhost:8080/option
{
    "id": "xyz0047",
    "place": 2,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0004T"

}

HTTP 201
POST http://localhost:8080/option
{
    "id": "xyz0046",
    "place": 3,
    "correct": true,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0004T"

}

HTTP 201
# exercise 5
POST http://localhost:8080/option
{
    "id": "xyz0056",
    "place": 1,
    "correct": true,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0005T"

}

HTTP 201
POST http://localhost:8080/option
{
    "id": "xyz0057",
    "place": 2,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0005T"

}

HTTP 201
POST http://localhost:8080/option
{
    "id": "xyz0058",
    "place": 3,
    "correct": false,
    "content": "Lorem ipsum sit dolor amet",
    "exercise_id": "ex0005T"

}

HTTP 201
