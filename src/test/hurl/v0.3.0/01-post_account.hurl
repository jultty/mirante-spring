# client can create an account
POST http://localhost:8889/auth
{
    "registration": "jc000001",
    "name": "Tania Wolfgramm",
    "email": "tania@jutty.dev",
    "password": "xyz6060"
}

HTTP 201
[Asserts]
header "Content-Type" == "application/json"
jsonpath "$.registration" == "jc000001"
jsonpath "$.email" == "tania@jutty.dev"
jsonpath "$.password" != "xyz6060"
jsonpath "$.token" == null
jsonpath "$.verified" == false
jsonpath "$.locked" == false
jsonpath "$.expired" == false

# # client can't create an account without providing a registration
# POST http://localhost:8889/auth
# {
#     "name": "Tania Wolfgramm",
#     "email": "tania@jutty.dev",
#     "password": "xyz6060"
# }
#
# HTTP 403
#
# # client can't create an account without providing a password
# POST http://localhost:8889/auth
# {
#     "registration": "jc000001",
#     "name": "Tania Wolfgramm",
#     "email": "tania@jutty.dev"
# }
#
# HTTP 403
