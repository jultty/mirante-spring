== Relatório de Desenvolvimento do Sistema Mirante
Juno Takano • 22 de março, 2024

#show link: underline

Mirante é um sistema de coleta e análise de dados relacionados ao processo de ensino-aprendizagem.

De janeiro a março de 2024, o sistema foi aprimorado em diferentes frentes:

Uma classe para autenticação foi implementada após pesquisa levantando algoritmos de criptografia mais adequados e técnicas de tokenização que evitem perda de performance na verificação de senhas. O sistema de autenticação servirá como base para a implementação da lógica de autorização, através da qual apenas serão acessíveis os recursos pertencentes a uma mesma conta ou curso compartilhado.

Foram implantadas verificações estáticas de segurança (_SAST_) e de análise estática de código no fluxo de integração contínua do sistema. Através das ferramentas Snyk, Bearer, Deepsource e CodeQL, foi possível identificar vulnerabilidades de segurança e outras formas possíveis de melhorar a segurança do sistema e a qualidade do código, bem como obter continuamente atualizações sobre a evolução destas análises.

A estrutura de dados retornada pela aplicação foi modificada, separando as estruturas internas daquelas que efetivamente são serializadas através da notação _JSON_ quando o servidor responde a uma solicitação. Através da implementação do padrão _Data Transfer Object_ (_DTO_), esta estrutura está sendo remodelada para que cada conjunto de exercícios possa conter todas as informações necessárias para a criação de um formulário, sem que a estrutura dos próprios exercícios precise ser acoplada à estrutura externa.

Foram implementadas ferramentas para gerar e organizar a documentação das classes e interfaces da aplicação, incluindo diagramas e relatórios de execução e cobertura de testes gerados automaticamente a partir da última versão do código. Foi criada uma #link("http://jultty.github.io/mirante/")[página web] para agregar todos estes recursos de documentação, que é atualizada automaticamente a partir do conteúdo mais recente do #link("https://github.com/jultty/mirante")[repositório git] onde o sistema é desenvolvido.
