## v0.2.0
- [x] Novas estruturas de dados
  - [x] Course
  - [x] Result
- [x] Revisar estrutura de dados para questões de ordenação
  - [x] Cada alternativa deve ter um valor numérico
  - [x] Cada alternativa deve ter um valor correto
  - [x] Ao receber o resultado, deve poder informar se está correto

## v0.3.0
- [x] Adicionar estruturas de dados para contas
- [x] Implementar lógica de autenticação por tokens de acesso temporários
  - [ ] Somente um usuário autenticado pode enviar resultados
  - [ ] Somente um usuário autenticado pode alterar suas questões
  - [ ] Somente um usuário autenticado pode alterar seus conjuntos de questões
  - [ ] Somente um usuário autenticado pode alterar suas turmas
  - [ ] Somente um usuário autenticado pode alterar seus dados
- [ ] Adicionar estruturas de dados para níveis de acesso
  - [x] Enum Access para conjuntos de questões
  - [x] Novo conjunto de questões é privado por padrão
  - [ ] Acesso público permite acesso a todos os grupos
  - [ ] Acesso "Course" permite acesso somente ao curso associado ao conjunto
- [ ] Invalidação de tokens

## v0.4.0
- [ ] Adicionar estruturas de dados para receber resultados
  - [ ] classe Result 
    - [ ] Atributos:
      - [x] Account
      - [x] Date
      - [x] Question (conjunto completo)
      - [x] Chosen options (no conjunto)
        - [x] Exercícios de ordenação lidam diferentemente com este item
          - [x] Valores numéricos para cada alternativa
      - [x] Course (idem ao conjunto)
      - [ ] Accuracy

## v0.5.0
- [ ] Revisão da interface gráfica de demonstração
- [ ] Interface gráfica para criação de cursos
- [ ] Conjunto de questões em inglês
- [ ] Conjunto de questões de lógica

## v0.6.0
- [ ] Verificação de um exercício e suas opções por docentes
  - [ ] Armazenar hash da versão verificada]

## 0.7.0
- [ ] Página de documentação melhorada
  - [ ] Estilos/SSG
  - [ ] Documentação para usuários
  - [ ] Documentação para desenvolvedores
- [ ] Verificar formatação de _Datetimes_ em JSON
  - Ver: <https://www.baeldung.com/jackson-serialize-dates?__s=m5n9kdgdsquuqqb9ufy2>
- [ ] `/set/demo-set-pdp` deveria retornar todos os _ids_ de exercícios

## 0.8.0
- [ ] Conta de administração
- [ ] Tela de seleção dos conjuntos para docentes

## 0.9.0
- [ ] Novo frontend

## 0.10.0
- [ ] Página de documentação para usuáries
  - [ ] Migrar relatório para HTML gerado por Markdown
- [ ] Migrar ferramenta para diagramas de caso de uso

## 0.11.0
- [ ] Deleção segura de entidades, sem perda de dados
- [ ] Exportação de dados para CSV

## 0.12.0
- [ ] Exportação de dados para JSON

## 0.13.0
- [ ] OpenTelemetry
  - <https://opentelemetry.io/docs/languages/java/automatic/spring-boot/>
  - <https://www.baeldung.com/spring-boot-opentelemetry-setup>

## 0.14.0 
- [ ] Análise de código automatizada
  - [dependency-check](http://jeremylong.github.io/DependencyCheck)
  - [renovate](https://github.com/renovatebot/renovate)
  - [SpotBugs](https://spotbugs.readthedocs.io/)
  - [Error Prone](https://errorprone.info/)
  - [NullAway](https://github.com/uber/NullAway)
  - [Infer](https://fbinfer.com/docs/getting-started/)

## 0.15.0
- [ ] Duplicar um conjunto para derivar outro

## 0.16.0
- [ ] Deduplicação de questões

## 0.17.0
- [ ] Dificuldade (manual e pela porcentagem de acertos) 
  - [ ] da questão 
  - [ ] do conjunto
- [ ] Classificação (Médio, Intermediário, Avançado)
  - [ ] da questão 
  - [ ] do conjunto

## 0.18.0
- [ ] Repetição espaçada
