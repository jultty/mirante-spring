## 0.3.0
- Conta de administração
- Tela de seleção dos conjuntos para docentes

## 0.x.0
- Novo frontend

## 0.x.0
- Página de documentação para usuáries
  - Migrar relatório para HTML gerado por Markdown
- Migrar ferramenta para diagramas de caso de uso

## 0.x.0
- Deleção segura de entidades, sem perda de dados
- Exportação de dados para CSV

## 0.x.0
- Exportação de dados para JSON

## 0.x.0
- OpenTelemetry
  - <https://opentelemetry.io/docs/languages/java/automatic/spring-boot/>
  - <https://www.baeldung.com/spring-boot-opentelemetry-setup>

## 0.x.0 
- Análise de código automatizada
  - [dependency-check](http://jeremylong.github.io/DependencyCheck)
  - [renovate](https://github.com/renovatebot/renovate)
  - [SpotBugs](https://spotbugs.readthedocs.io/)
  - [Error Prone](https://errorprone.info/)
  - [NullAway](https://github.com/uber/NullAway)
  - [Infer](https://fbinfer.com/docs/getting-started/)

## 0.x.0
- Duplicar um conjunto para derivar outro

## 0.x.0
- Deduplicação de questões

## 0.x.0
- Dificuldade (manual e pela porcentagem de acertos) 
  - da questão 
  - do conjunto
- Classificação (Médio, Intermediário, Avançado)
  - da questão 
  - do conjunto

## 0.x.0
- Repetição espaçada

## Backlog
- Página de documentação para desenvolvedores
- [ ] Novas estruturas de dados
  - [x] Course
  - [ ] Result
- [ ] Revisar estrutura de dados para questões de ordenação
  - [ ] Cada alternativa deve ter um valor numérico
  - [ ] Cada alternativa deve ter um valor correto
  - [ ] Ao receber o resultado, deve poder informar se está correto
- [ ] Adicionar estruturas de dados para contas
  - [x] Adicionar lógica para gerar tokens de acesso temporários
  - [ ] Somente um usuário autenticado pode enviar resultados
  - [ ] Somente um usuário autenticado pode alterar suas questões
  - [ ] Somente um usuário autenticado pode alterar seus conjuntos de questões
  - [ ] Somente um usuário autenticado pode alterar suas turmas
  - [ ] Somente um usuário autenticado pode alterar seus dados
- [ ] Adicionar etruturas de dados para níveis de acesso
  - [x] Enum Access para conjuntos de questões
  - [x] Novo conjunto de questões é privado por padrão
  - [ ] Acesso público permite acesso a todos os grupos
  - [ ] Acesso "Course" permite acesso somente ao curso associado ao conjunto
- [ ] Adicionar estruturas de dados para receber resultados
  - [ ] classe Result 
    - [ ] Atributos:
      - [ ] User
      - [ ] Date
      - [ ] Question
      - [ ] Chosen options
        - [ ] Exercícios de ordenação lidam diferentemente com este item
          - [ ] Valores numéricos para cada alternativa
      - [ ] Course
      - [ ] Accuracy
- [ ] Conta docente
- [ ] Interface gráfica para criação de cursos
- [ ] Invalidação de tokens
- [ ] Conjunto de questões em inglês
- [ ] Conjunto de questões de lógica

- [ ] Verificar formatação de data em JSON
  - Ver: <https://www.baeldung.com/jackson-serialize-dates?__s=m5n9kdgdsquuqqb9ufy2>
- [ ] `/set/demo-set-pdp` deveria retornar todos os ids de exercícios
