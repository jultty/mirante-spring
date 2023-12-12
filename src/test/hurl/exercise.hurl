# client can create an exercise
POST http://localhost:8080/exercise
{
    "id": "ex0001T",
    "instruction": "exercise test instruction containing a longer string",
    "set_id": "exset0001T"
}

HTTP 201

POST http://localhost:8080/exercise
{
    "id": "ex0002T",
    "instruction": "Lorem ipsum dolor sit amet, qui minim labore",
    "set_id": "exset0001T"
}

HTTP 201

POST http://localhost:8080/exercise
{
    "id": "ex0003T",
    "instruction": "Proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod.",
    "set_id": "exset0001T"
}

HTTP 201

POST http://localhost:8080/exercise
{
    "id": "ex0004T",
    "instruction": "Proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod.",
    "set_id": "exset0002T"
}

HTTP 201

POST http://localhost:8080/exercise
{
    "id": "ex0005T",
    "instruction": "Proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod.",
    "set_id": "exset0002T"
}

HTTP 201

POST http://localhost:8080/exercise
{
    "id": "ex0006T",
    "instruction": "Proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod.",
    "set_id": "exset0002T"
}

HTTP 201
