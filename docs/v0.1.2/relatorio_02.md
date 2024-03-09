# Mirante: Relatório para v0.1.2

## Tarefas
- Resolução de uma regressão de recursão infinita ao tentar implementar a lógica de autenticação

## Desenvolvimento

A implementação da versão v0.1.1 fornecia todos os dados apenas no endpoint `/option`, que retornava todas as opções existentes.

Para a versão `0.2.0` previa-se o oposto: `/option` não deveria retornar nada, e `/option/{id}` deveria retornar apenas as informações daquela opção, com seu exercício na forma simples do _id_  e não toda a estrutura do exercício.

Embora esta questão tenha sido resolvida com o uso do padrão de `DTOs`, foi introduzido um _bug_ de recursão infinita onde, ao tentar criar opções, elas tentavam referenciar o exercício a que pertenciam, que por sua vez referenciava todas as suas opções, e assim por diante.

A primeira solução encontrada relaciona-se ao uso das anotações e `@JsonManagedReference` e `@JsonBackReference`, fornecidas no pacote `com.fasterxml.jackson.annotation.JsonBackReference`, que instruem o serializador de JSON saber em qual ponta da relação ele deve colocar a listagem completa, e em qual ponta apenas o _id_.

