# Relatório de Desenvolvimento: Autenticação
Juno Takano • ter 02 abr 2024 13:24:18 GMT-03

Durante os meses de março e abril está sendo implementado um mecanismo de autenticação através dos endpoints `/auth` e `/auth/token`.

## Março

No começo do mês de março foi realizado um trabalho de [reestruturação das respostas](https://github.com/jultty/mirante/commit/6af74189dc3f51c9174908a8494a0378693931d2) nos endpoints `/exercise`e `/option`.

Há uma dependência circular entre estes pacotes, ainda a ser resolvida.

O [relatório da versão 0.1.2](https://github.com/jultty/mirante/blob/df2cf4e8d6fc058644df72235b77539094e54604/docs/reports/v0.1.2/relatorio.md) contém mais detalhes sobre o contexto anterior.

A partir do dia 10 de março, um endpoint `/version` foi introduzido, retornando apenas a versão atual do sistema. Este endpoint foi utilizado para realizar testes de integração onde, mesmo após a implementação da configuração de segurança, a partir da qual todos os endpoints passam a ser privados por padrão, este endpoint poderia permanecer configurado como público.

Isso foi [concluído](https://github.com/jultty/mirante/commit/71f7242bd4c94d3b532992ae4517666c39c1ec0f) no dia 11 de março, quando também [foram introduzidos](https://github.com/jultty/mirante/commit/532ed6d0ae5ef49bccad9bc61aa257baec1a6818) testes automatizados que verificam a configuração a partir de uma instância em execução do sistema. Estes testes, portanto, não executam apenas a lógica interna da aplicação, mas iniciam o servidor e obtém as respostas das requisições tal como seriam feitas por uma aplicação cliente.

Na semana seguinte, foram implantadas ferramentas de análise estática de segurança e de qualidade do código no fluxo de integração contínua da aplicação no GitHub, bem como a geração automática de documentação com uma página web associada:

As seguintes ferramentas de análise estática e documentação foram implantadas:

- [SpotBugs](https://github.com/jultty/mirante/commit/5553dec8a4b5d0c1cbdcd0fc80fc4d30b2e5bcf1) (análise estática de código)
- [JaCoCo](https://github.com/jultty/mirante/commit/1b2c478948e24995c9a8f5c23b4c91acb0ac7fe4) (cobertura de testes)
- [DeepSource](https://github.com/jultty/mirante/commit/272f1a4ccf472599d9fdc09dc51e0c8558c8fd84) (análise estática de código)
- [Bearer](https://github.com/jultty/mirante/commit/4c145def7a8beabd196fb893b74f674b97dfbc52) (análise estática de segurança)
- [Snyk](https://github.com/jultty/mirante/commit/87571711ca10f51ece09c6120a2b8f4f69ce1a89) (análise de segurança estática e de _supply chain_)
- CodeQL (análise estática de código)
- [PlantUML](https://github.com/jultty/mirante/commit/630a37c01f3d6be0e23bd1ea57d88bb99c2e85b4) (geração automática de diagramas)
- [Página de documentação](https://github.com/jultty/mirante/commit/ef90552b1fae3aca1f7c08972de178184d5464ec)

Cabe ressaltar que já havia outra ferramenta de análise estática implantada, [Modernizer](https://github.com/jultty/mirante/commit/10c86f48b826509e30252ce8bd6a0b773fcd4cd9), que detecta o uso de APIs legadas e outros padrões em desuso.

Por volta da metade do mês, foi iniciado o processo de escrita do projeto de pesquisa. Foram realizadas menos ações de desenvolvimento a partir deste período.

Em 22 de março foi escrito [um relatório](https://github.com/jultty/mirante/blob/0166e622826b6ccd95579c1c9243a0ddbed5756c/docs/reports/v0.3.0/relatorio_2024-03.typ) resumindo as atividades realizadas desde o início do ano.

Na última semana do mês, a configuração de segurança [foi consolidada](https://github.com/jultty/mirante/commit/14394af16002da4deecc44d1df22a6420d39412a) através de estudos da documentação do Spring Security:

- A classe `SecUtils`, que define o algoritmo para encriptação de senhas atual, foi movido para o novo pacote `mirante.api.security`, que foi introduzido para agregar a configuração de segurança e os controladores de autenticação.
- Foi adicionada uma classe de [configuração de segurança](https://github.com/jultty/mirante/commit/14394af16002da4deecc44d1df22a6420d39412a#diff-165cdea4c467c61ee81e8580807a1e95c7958cfdd8a5eada9c194d9c0dbc5596) que substitui a configuração automática provida pelo framework.
- No commit referenciado no link acima, foi definido o endpoint `/version` como público e todos os demais como protegidos pelo mecanismo de autenticação.
- Foram definidos o mecanismo de codificação das informações de autenticação através do [algoritmo escolhido previamente](https://github.com/jultty/mirante/blob/main/docs/reports/v0.1.2/relatorio.md), Argon 2.

## Abril
- Foi criado um controlador especificamente para autenticação, [`mirante.api.security.AuthenticationController`](https://github.com/jultty/mirante/commit/cbeac1cea9c18cdc11cf355f5fd93d7c72e5cfaf#diff-5f1c19c438f306eb22f85313083aa9d38e27457ea5855311ad41c0e29c098c1c).
- O endpoint de criação de contas `/account` e endpoint de autenticação `/token` foi [movido](https://github.com/jultty/mirante/commit/cbeac1cea9c18cdc11cf355f5fd93d7c72e5cfaf#diff-93bc02e314f5e394053842ea3f6b2095c896f65fa575abf2bfbc1fa48b73722b) da classe `AccountController`, pertencente ao pacote `mirante.api.account` para a classe `AuthenticationController`, do pacote `mirante.api.security`.
- A configuração de segurança [foi atualizada](https://github.com/jultty/mirante/commit/cbeac1cea9c18cdc11cf355f5fd93d7c72e5cfaf#diff-165cdea4c467c61ee81e8580807a1e95c7958cfdd8a5eada9c194d9c0dbc5596) para que estes novos endpoints também fossem públicos. Ficaram como públicos os endpoints `version`, `auth`, e qualquer endpoint cuja URL seja derivada de `auth`, por exemplo, `auth/health`, utilizado para verificar o status do serviço de autenticação e para os [testes](https://github.com/jultty/mirante/commit/cbeac1cea9c18cdc11cf355f5fd93d7c72e5cfaf#diff-f18fb4239bba98cb1510c4bbcfcc0b0297d8b99bfe0c498eddefeab2a6d0d5a9) implementados.
- A versão do Spring Boot utilizada foi atualizada da `3.2.3` para o patch `3.2.4`, e do Spring Security da versão `6.2.1` para o patch `6.2.3`.

Em 03/04, os primeiros testes começaram a ser escritos para o processo de criação de contas no novo endpoint configurado, `/auth`.

Apesar da configuração descrita anteriormente, onde este endpoint, juntamente com `/version`, era público apesar da configuração padrão tornar todos os demais privados, ao realizar solicitações HTTP do tipo `POST` foi verificado retorno do erro `403` (Não autorizado).

A princípio, foi iniciada investigação quanto à possibilidade do erro ter origem nas políticas padrão de mitigação de vulnerabilidades de CSRF e CORS.

Considerando que o método de autenticação escolhido, por tokens temporários, já requer a troca de uma chave única que expira rapidamente, a proteção padrão para CORS foi desativada.

Para a atualização do front-end, será necessário levar em conta estas proteções. As seguintes seções de documentação que apareceram ao estudar este problema podem vir a ser úteis nesta etapa:
- [Cross Site Request Forgery (CSRF) :: Spring Security - Integrating with CSRF Protection](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-integration)
- [Cross Site Request Forgery (CSRF) :: Spring Security](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-integration-form)

Em seguida foi apresentado novo erro, neste caso pelo curso ao qual a conta estava vinculada ainda não existir.

Em um primeiro momento, foi configurado um script SQL específico para a configuração de teste, que criava o curso referido na inicialização do banco de dados.

Após esta configuração, o teste passou. Contudo, é importante que seja possível criar uma conta desassociada a um curso para que esta conta seja capaz de criar os cursos que irá gerenciar.

Para tanto, o atributo `course` foi removido. Futuramente, a agregação de quais estudantes pertencem a um dado curso será responsabilidade exclusiva da classe `Course`.

No dia 8 de abril foi implantada uma estrutura para a escrita do [manual de uso](https://jultty.github.io/mirante/generated/asciidoc/index.html), parte da documentação do sistema que deve servir como referência mais progressiva e didática. Foi utilizada a ferramenta Asciidoctor e o Spring REST Docs, um projeto associado ao Spring que gera documentação a partir de testes automatizados, validando a veracidade dos trechos de código que estão sendo associados a cada explicação.

Ainda no dia 8, foi adicionada uma [referência dos endpoints](https://app.swaggerhub.com/apis-docs/jutty/mirante/v0.3.0) com base na biblioteca Swagger, que gera uma especificação através do padrão OpenAPI 3.0 em JSON e YAML. A referência está disponível online e também em dois endpoints da própria aplicação, `/api-docs` (esquema em JSON) e `/api-docs/ui` (interface web).
