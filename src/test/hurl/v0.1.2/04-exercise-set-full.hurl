# exercise 1
POST http://localhost:8888/set
{
    "id": "demo-set-pdp",
    "name": "Paradigmas da Programação",
    "access": "PUBLIC"
}

HTTP 201
POST http://localhost:8888/exercise
{
    "id": "demo-pdp-0001",
    "set": "demo-set-pdp",
    "instruction": "Os pilares da POO são:"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0001A",
    "content": "Polimorfismo",
    "correct": true,
    "place": 1,
    "exercise_id": "demo-pdp-0001"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0001B",
    "content": "Herança",
    "place": 2,
    "correct": true,
    "exercise_id": "demo-pdp-0001"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0001C",
    "content": "Membros Estáticos",
    "place": 3,
    "correct": false,
    "exercise_id": "demo-pdp-0001"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0001D",
    "content": "Encapsulamento",
    "place": 4,
    "correct": true,
    "exercise_id": "demo-pdp-0001"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0001E",
    "content": "Composição",
    "place": 5,
    "correct": false,
    "exercise_id": "demo-pdp-0001"
}

HTTP 201

# exercise 2
POST http://localhost:8888/exercise
{
    "id": "demo-pdp-0002",
    "set": "demo-set-pdp",
    "instruction": "(UFAM) No paradigma de Programação Orientação a Objetos, a separação de programas em partes, o mais isolado possível, refere-se ao conceito de ___________. :"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0002A",
    "content": "abstração",
    "place": 1,
    "correct": false,
    "exercise_id": "demo-pdp-0002"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0002B",
    "content": "derivação",
    "place": 2,
    "correct": false,
    "exercise_id": "demo-pdp-0002"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0002C",
    "content": "encapsulamento",
    "place": 3,
    "correct": true,
    "exercise_id": "demo-pdp-0002"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0002D",
    "content": "herança",
    "place": 4,
    "correct": false,
    "exercise_id": "demo-pdp-0002"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0002E",
    "content": "polimorfismo",
    "place": 5,
    "correct": false,
    "exercise_id": "demo-pdp-0002"
}

HTTP 201

# exercise 3
POST http://localhost:8888/exercise
{
    "id": "demo-pdp-0003",
    "set": "demo-set-pdp",
    "instruction": "Na programação funcional, deve-se favorecer sempre que possível o uso de estruturas de dados:"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0003A",
    "content": "compostas",
    "place": 1,
    "correct": false,
    "exercise_id": "demo-pdp-0003"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0003B",
    "content": "em lote",
    "place": 2,
    "correct": false,
    "exercise_id": "demo-pdp-0003"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0003C",
    "content": "iteráveis",
    "place": 3,
    "correct": false,
    "exercise_id": "demo-pdp-0003"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0003E",
    "content": "imutáveis",
    "place": 4,
    "correct": true,
    "exercise_id": "demo-pdp-0003"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0003F",
    "content": "herdadas",
    "place": 5,
    "correct": false,
    "exercise_id": "demo-pdp-0003"
}

HTTP 201

# exercise 4
POST http://localhost:8888/exercise
{
    "id": "demo-pdp-0004",
    "set": "demo-set-pdp",
    "instruction": "Complete: \"Um tipo de dado ________, na programação funcional, é um tipo de dado composto por outros tipos.\" :"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0004A",
    "content": "algébrico",
    "place": 1,
    "correct": true,
    "exercise_id": "demo-pdp-0004"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0004B",
    "content": "imutável",
    "place": 2,
    "correct": false,
    "exercise_id": "demo-pdp-0004"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0004C",
    "content": "functor",
    "place": 3,
    "correct": false,
    "exercise_id": "demo-pdp-0004"
}

HTTP 201
POST http://localhost:8888/option
{
    "id": "option-demo-pdp-0004E",
    "content": "colecionado",
    "place": 4,
    "correct": false,
    "exercise_id": "demo-pdp-0004"
}

HTTP 201
