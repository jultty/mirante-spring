#set heading(numbering: "1.1.")
#set enum(numbering: "1.1.1.", full: true)

#align(center, text(17pt)[Relatório de Desenvolvimento do Sistema Mirante])

Juno Takano • 22 de março, 2024

#show link: underline

Mirante é um sistema de coleta e análise de dados relacionados ao processo de ensino-aprendizagem.

De janeiro a março de 2024, o sistema foi aprimorado em diferentes frentes:

Uma classe para autenticação foi implementada após pesquisa levantando algoritmos de criptografia mais adequados e técnicas de tokenização que evitem perda de performance na verificação de senhas. O sistema de autenticação servirá como base para a implementação da lógica de autorização, através da qual apenas serão acessíveis os recursos pertencentes a uma mesma conta ou curso compartilhado.

Foram implantadas verificações estáticas de segurança (_SAST_) e de análise estática de código no fluxo de integração contínua do sistema. Através das ferramentas Snyk, Bearer, Deepsource e CodeQL, foi possível identificar vulnerabilidades de segurança e outras formas possíveis de melhorar a segurança do sistema e a qualidade do código, bem como obter continuamente atualizações sobre a evolução destas análises.

A estrutura de dados retornada pela aplicação foi modificada, separando as estruturas internas daquelas que efetivamente são serializadas através da notação _JSON_ quando o servidor responde a uma solicitação. Através da implementação do padrão _Data Transfer Object_ (_DTO_), esta estrutura está sendo remodelada para que cada conjunto de exercícios possa conter todas as informações necessárias para a criação de um formulário, sem que a estrutura dos próprios exercícios precise ser acoplada à estrutura externa.

Foram implementadas ferramentas para gerar e organizar a documentação das classes e interfaces da aplicação, incluindo diagramas e relatórios de execução e cobertura de testes gerados automaticamente a partir da última versão do código. Foi criada uma #link("http://jultty.github.io/mirante/")[página web] para agregar todos estes recursos de documentação, que é atualizada automaticamente a partir do conteúdo mais recente do #link("https://github.com/jultty/mirante")[repositório git] onde o sistema é desenvolvido.

#pagebreak()
= Requisitos

Os requisitos do sistema foram atualizados.

A lista abaixo utiliza números menores para os itens de *maior* prioridade.

1. Gerenciar contas
  1. Criar uma conta
  2. Obter uma conta
  3. Editar uma conta
  4. Apagar uma conta
2. Autenticação
  1. Autenticar-se
  2. Revogar a autenticação
  3. Restringir o acesso a um curso
  4. Restringir o acesso a um conjunto de exercícios
  5. Restringir o acesso a um exercício
3. Gerenciar cursos
  1. Criar um curso
  2. Obter os dados de um curso
    1. Obter as contas associadas a um curso
  3. Editar um curso
  4. Associar uma conta a um curso
  5. Desassociar uma conta a um curso
  6. Apagar um curso
4. Armazenar um conjunto de exercícios
  1. Criar um conjunto de exercícios
  2. Criar um exercício
  3. Obter um conjunto de exercícios
  4. Obter um exercício
  5. Editar um conjunto de exercícios
  6. Editar um exercício
  7. Associar um exercício a um conjunto de exercícios
  8. Apagar um conjunto de exercícios
  9. Apagar um exercício
5. Responder a um conjunto de exercícios
  1. Exibir o conjunto de exercícios em uma interface gráfica
  2. Retornar os dados resultantes para o backend
6. Geração de métricas
  1. Calcular a métrica de assiduidade
  2. Calcular a métrica de acuidade
  3. Calcular a métrica de retenção
  4. Criar uma métrica
  5. Obter uma métrica
  6. Editar uma métrica
  7. Apagar uma métrica
  8. Exibir as métricas em uma interface gráfica
7. Armazenar um registro datado de respostas
  1. Armazenar cada conjunto datado de alternativas escolhidas
  2. *Não* deve ser possível alterar um evento
  2. *Não* deve ser possível apagar um evento

#pagebreak()
= Partes interessadas

#table(
  columns: (auto, 60%, auto),
  inset: 10pt,
  align: horizon,
  table.header(
    [*Parte*], [*Interesse*], [*Influência*],
  ),
  "Discente", "Melhorar seu aprendizado e monitorar seu próprio desempenho", "Alta",
  "Docente", "Melhorar e monitorar o desempenho de seus cursos", "Média",
  "Desenvolvedora", "Entregar um sistema que atende aos requisitos e prioridades", "Baixa",
)

= Cenários

#table(
  columns: (auto, 60%, auto),
  inset: 10pt,
  align: horizon,
  table.header(
    [*Id.*], [*Cenário*], [*Prioridade*],
  ),
  "C1", [Uma aluna solicita um conjunto de exercícios no navegador Chrome, recebe um formulário contendo todos os exercícios e envia seus resultados.], "Alta",
  "C2", [Uma aluna solicita um conjunto de exercícios no navegador Firefox e recebe um formulário contendo todos os exercícios e envia seus resultados.], "Média",
  "C3", [Uma professora solicita as métricas de seu curso e recebe uma página web contendo todas as métricas disponíveis.], "Média",
  "C4", [Uma professora envia os seus dados de acesso desejados na página de criar contas e é cadastrada pelo sistema.], "Alta",
  "C5", [Uma desenvolvedora modifica o tipo de banco de dados na configuração da aplicação e a mudança não causa um erro de migração.], "Média",
  "C6", [Uma introduz uma linha de código inválida no ramo principal do repositório de código da aplicação e ele não é implantado no ambiente de produção.], "Alta",
)

= Requisitos arquiteturais

#table(
  columns: (auto, 60%, auto),
  inset: 10pt,
  align: horizon,
  table.header(
    [*Id.*], [*Cenário*], [*Prioridade*],
  ),
  "R1", [Deve ser possível trocar o tipo de banco de dados sem quebrar a aplicação.], "Alta",
  "R2", [Deve ser possível trocar o ambiente de hospedagem sem quebrar a aplicação.], "Ata",
  "R3", [A interface gráfica deve ser independente do sistema.], "Média",
  "R4", [O sistema deve se recuperar autonomamente de uma interrupção inesperada na execução.], "Média",
  "R5", [O sistema deve responder a requisições dentro de até 3 segundos.], "Baixa",
  "R6", [O sistema deve persistir dados de forma redundante.], "Baixa",
)
