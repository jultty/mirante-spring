# Relatório de Desenvolvimento: Autenticação
Juno Takano

terça, 02 de abril de 2024, 10:12:34 GMT-03

Durante os meses de março e abril está sendo implementado um mecanismo de autenticação através dos endpoints `/auth` e `/auth/token`.

# Março

No começo do mês de março foi realizado um trabalho de [reestruturação das respostas](https://github.com/jultty/mirante/commit/6af74189dc3f51c9174908a8494a0378693931d2) nos endpoints `/exercise`e `/option`.

Ainda persiste uma dependência circular entre estes pacotes, ainda a ser resolvida.

Ver o [relatório da versão 0.1.2](https://github.com/jultty/mirante/blob/df2cf4e8d6fc058644df72235b77539094e54604/docs/reports/v0.1.2/relatorio.md) para mais detalhes.

A partir do dia 10 de março, um endpoint `/version` foi introduzido, retornando apenas a versão atual do sistema. Este endpoint foi utilizado para realizar testes onde, mesmo após a implementação da configuração de segurança, a partir da qual todos os endpoints passam a ser privados por padrão, este endpoint poderia permanecer configurado como público.

Isso foi [concluído](https://github.com/jultty/mirante/commit/71f7242bd4c94d3b532992ae4517666c39c1ec0f) no dia 11 de março, quando também [foram introduzidos](https://github.com/jultty/mirante/commit/532ed6d0ae5ef49bccad9bc61aa257baec1a6818) testes automatizados que verificam a configuração a partir de uma instância em execução do sistema. Estes testes, portanto, não executam apenas a lógica interna da aplicação, mas iniciam o servidor e obtém as respostas das requisições tal como seriam feitas por uma aplicação cliente.

Na semana seguinte, foram implantadas ferramentas de análise estática de segurança e de qualidade do código no fluxo de integração contínua da aplicação no GitHub, bem como a geração automática de documentação com uma página web associada:
- [SpotBugs](https://github.com/jultty/mirante/commit/5553dec8a4b5d0c1cbdcd0fc80fc4d30b2e5bcf1) (análise estática de código)
- [JaCoCo](https://github.com/jultty/mirante/commit/1b2c478948e24995c9a8f5c23b4c91acb0ac7fe4) (cobertura de testes)
- [DeepSource](https://github.com/jultty/mirante/commit/272f1a4ccf472599d9fdc09dc51e0c8558c8fd84) (análise estática de código)
- [Bearer](https://github.com/jultty/mirante/commit/4c145def7a8beabd196fb893b74f674b97dfbc52) (análise estática de segurança)
- [Snyk](https://github.com/jultty/mirante/commit/87571711ca10f51ece09c6120a2b8f4f69ce1a89) (análise de segurança estática e de _supply chain_)
- [PlantUML](https://github.com/jultty/mirante/commit/630a37c01f3d6be0e23bd1ea57d88bb99c2e85b4) (geração automática de diagramas)
- [Página de documentação](https://github.com/jultty/mirante/commit/ef90552b1fae3aca1f7c08972de178184d5464ec)

Cabe ressaltar que já havia outra ferramenta de análise estática implantada, [Modernizer](https://github.com/jultty/mirante/commit/10c86f48b826509e30252ce8bd6a0b773fcd4cd9), que detecta o uso de APIs legadas e outros padrões em desuso.

Por volta da metade do mês, foi iniciado o processo de escrita do projeto de pesquisa. Foram realizadas menos ações de desenvolvimento a partir desse período.

Em 22 de março foi escrito um relatório resumindo as atividades realizadas desde o início do ano (https://github.com/jultty/mirante/blob/0166e622826b6ccd95579c1c9243a0ddbed5756c/docs/reports/v0.3.0/relatorio_2024-03.typ).

Na última semana do mês, a configuração de segurança [foi consolidada](https://github.com/jultty/mirante/commit/14394af16002da4deecc44d1df22a6420d39412a) através de estudos da documentação do Spring Security:

- A classe `SecUtils`, que define o algoritmo para encriptação de senhas presente, foi movido para o novo pacote `mirante.api.security`, que foi introduzido para agregar a configuração de segurança e os controladores de autenticação.
- Foi adicionada uma classe de [configuração de segurança](https://github.com/jultty/mirante/commit/14394af16002da4deecc44d1df22a6420d39412a#diff-165cdea4c467c61ee81e8580807a1e95c7958cfdd8a5eada9c194d9c0dbc5596) que substitui a configuração automática provida pelo framework.
- No commit referenciado no link acima, foi definido o endpoint `/version` como público e todos os demais como protegidos pelo mecanismo de autenticação.
- Foram definidos o mecanismo de codificação das informações de autenticação através do [algoritmo escolhido previamente](https://github.com/jultty/mirante/blob/main/docs/reports/v0.1.2/relatorio.md), Argon 2.
- A versão do Spring Boot utilizada foi atualizada da `3.2.3` para o patch `3.2.4`, e do Spring Security da versão `6.2.1` para o patch `6.2.3`.
