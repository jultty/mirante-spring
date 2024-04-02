= Relatório de instalação local do SISEN
Juno Takano

== Ambiente local

Debian GNU/Linux 12 (bookworm)

- Python 3.11.2
- node v20.10.0
- git 2.43.0

== Servidor

=== Clonagem do repositório

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```sh
git clone https://github.com/sire-edu/sisen-server
cd sisen-server
```
]

=== Configuração do ambiente virtual

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```sh
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
```
]

=== Migração do banco de dados

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```sh
python manage.py migrate
```
]

=== Inicialização do servidor

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```
python manage.py runserver
```
]

=== Obtenção do _token_ de acesso

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```sh
curl -X POST -H "Content-Type: application/json" -d \
  "{\"username\": \"admin1\", \"password\": \"admin\"}" \
  http://localhost:8000/api/v1/auth
```
]

==== Resposta
#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```
Invalid HTTP_HOST header: 'localhost:8000'. You may need to add 'localhost' to ALLOWED_HOSTS.
Bad Request: /api/v1/auth
[19/Dec/2023 21:33:50] "POST /api/v1/auth HTTP/1.1" 400 73966
```
]

O erro informa que o endereço `localhost` (`127.0.0.1`) não é um dos endereços configurados como permitidos na variável `ALLOWED_HOSTS`.

Com a ferramenta `ripgrep` foi possível encontrar essa variável em `sisen/settings.py`.

#pagebreak()
==== Correção

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```python
# alterar esta linha
ALLOWED_HOSTS = ['54.94.120.131']

# adicionar 'localhost'
ALLOWED_HOSTS = ['localhost', '54.94.120.131']
```
]

Se desejável, a modificação acima poderia ser configurada para ocorrer automaticamente em ambientes de desenvolvimento.

Após a alteração, o servidor detectou a mudança automaticamente e se reinicializou sozinho. A obtenção do _token_ de acesso ocorreu com sucesso.

#pagebreak()
== Cliente

=== Configuração do ambiente

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```sh
git clone https://github.com/sire-edu/sisen-client
cd sisen-client
npm install
```
]

=== Inicialização do cliente

#block( fill: luma(230), inset: 8pt, radius: 4pt, breakable: false)[
```
npx au run watch
```
]

Conforme o relatório de instalação disponível no Classroom, ocorreu o erro `ERR_OSSL_EVP_UNSUPPORTED`, resolvido com `export NODE_OPTIONS=--openssl-legacy-provider`.

#image("img/01_tela-login-cliente.png")
