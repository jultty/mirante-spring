# client can query the version without authentication
GET http://localhost:8889/version

HTTP 200
[Asserts]
header "Content-Type" == "application/json"
jsonpath "$.version" isString
jsonpath "$.version" == "v0.3.0"
