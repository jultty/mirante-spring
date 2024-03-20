# Mirante: Relatório para v0.1.2

## Tarefas
- [ ] Organizar estruturas de dados existentes
- [ ] Revisar estrutura de dados para questões de ordenação
- [ ] Adicionar estruturas de dados para contas
- [ ] Adicionar etruturas de dados para nĩíveis de acesso
- [ ] Adicionar estruturas de dados para receber resultados

### Subtarefas
- [x] Resolução de uma regressão de recursão infinita ao tentar implementar a lógica de autenticação

## Desenvolvimento

Foram levantadas as opções atuais para o armazenamento seguro de senhas. Conforme [recomedações da OWASP][#1] e da própria [documentação do framework Spring][#2], a opção selecionada foi o algoritmo Argon2, que fornece forte segurança criptográfica resistente a ataques de força bruta, com [suporte nativo][#3] no framework através do Spring Security.

A implementação utilizou a classe `Argon2PasswordEncoder` do Spring, que fornece os métodos `encode` e `matches`, permitindo armazenar apenas o resultado de uma operação unidirecional de _hashing_, de tal forma que o sistema nunca armazena a senha em si, mas apenas o resultado de sua codificação.

A classe utilizada cuida da geração de um _salt_ para tornar a senha armazenada resistente a ataques por _rainbow table_, armazenado na mesma saída do método `encode`.

Durante a implementação, um erro inicial enfrentado após o _commit_ `0b41f38` foi que as senhas eram gravadas no banco como valores nulos.

Ao investigar a raiz do problema, foram indetificadas algumas novas informações:

- O uso da classe `Argon2PaswordEncoder` requer a inserção manual da dependência `org.bouncycastle:bcprov-jdk15on:1.64`
- A [especificação do JPA][#4] exige que exista um construtor padrão (sem argumentos) em classes que definem uma entidade

A segunda informação levou à compreensão de que o ORM não estava chamando o construtor onde a senha era codificada, mas sim utilizando o construtor padrão.

Como solução, o controlador do _endpoint_ `account` passou a serializar os dados da requisição em um objeto de uma nova classe intermediária, criada com acesso restrito somente ao pacote `account`, e então instanciar um objeto da classe `Account` utilizando o construtor correto, que codifica a senha recebida, e então a passa para o repositório JPA para ser persistida no banco.

Uma estrutura de dados para agrupar turmas (`Course`) foi criada. As classes `Account` e `ExerciseSet` passaram a ter um campo 'Course` onde a turma à qual se associam pode ser especificada.

A classe `Account` recebeu ainda o campo `token`, que armazena o token de acesso atual que permite que a conta autentique suas requisições e que o sistema valide que aquela conta tem acesso ao recurso solicitado.

A classe AccountService foi criada para fornecer métodos de obtenção de tokens de acesso e de busca por uma conta através de um token fornecido. Nesta classe é feita a verificação onde o token apenas é retornado se a senha fornecida passa no método de verificação da senha codificada.

Um novo endpoint `/token` foi adicionado para a obtenção de um token através de uma solicitação `GET`.

Para facilitar a captura do token nos testes com o Hurl, ele será retornado como uma estrutura JSON simples:

```json
{ "token": "3a321f5a-ddaf-4800-9530-49cb39a2effc" }
```

Uma classe `Result` foi criada para armazenar resultados. Esta classe tem apenas um campo do tipo `Timestamp`, que armazena a data e o horário em um formato padronizado e compatível com o banco de dados, e um campo da classe `Exercise`, que corresponde a um objeto equivalente ao exercício recebido como resposta.

Esse exercício conterá um conjunto de opções que poderá ser comparado com o conjunto armazenado no conjunto de exercícios original para obter a acuidade do resultado. A partir desse valor e da data, será possível calcular também as métricas de retenção e assiduidade.

Os diagramas de casos de uso, sequência e telas foram atualizados para corresponder à nova implementação. O diagrama de casos de uso da ferramenta usada (PlantUML) torna-se confuso quando há grande quantidade de setas. Para os próximos, planejo utilizar a ferramenta Draw.io neste tipo de diagrama especificamente.

### Regressão: Regressão infinita na serialização para JSON
A implementação da versão v0.1.1 fornecia todos os dados apenas no endpoint `/option`, que retornava todas as opções existentes.

Para a versão `0.2.0` previa-se o oposto: `/option` não deveria retornar nada, e `/option/{id}` deveria retornar apenas as informações daquela opção, com seu exercício na forma simples do _id_  e não toda a estrutura do exercício.

Embora esta questão tenha sido resolvida com o uso do padrão de `DTOs`, foi introduzida uma regressão de recursão infinita onde, ao tentar criar opções, elas tentavam referenciar o exercício a que pertenciam, que por sua vez referenciava todas as suas opções, e assim por diante.

A primeira solução encontrada relaciona-se ao uso das anotações e `@JsonManagedReference` e `@JsonBackReference`, fornecidas no pacote `com.fasterxml.jackson.annotation.JsonBackReference`, que instruem o serializador de JSON saber em qual ponta da relação ele deve colocar a listagem completa, e em qual ponta apenas o _id_.

Como próximo passo, prossegui à listagem das opções de um exercício ao solicitá-lo pelo _id_, por exemplo, `/exercise/ex002`.

O endpoint `/exercise` passou a construir manualmente os DTOs aninhados, através da seguinte lógica:

```java
    exercises.forEach(e -> {
      ExerciseDTO dto = new ExerciseDTO();
      dto.id = e.getId();
      dto.instruction = e.getInstruction();
      dto.set = e.getSetId();
      dto.options = new HashSet<OptionDTO>();
      e.getOptions().forEach(o -> {
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.id = o.getId();
        optionDTO.content = o.getContent();
        optionDTO.place = o.getPlace();
        optionDTO.correct = o.getCorrect();
        optionDTO.exercise = o.getExerciseId();
        dto.options.add(optionDTO);
      });
      exerciseDTOs.add(dto);
    });
```

Este código pode ainda ser melhorado com o uso da biblioteca `modelMapper`, que reduziria muito o código. De toda forma, ele agora atende à necessidade atual.

[#1]: https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
[#2]: https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-argon2
[#3]: https://docs.spring.io/spring-security/site/docs/6.2.1/api/org/springframework/security/crypto/argon2/Argon2PasswordEncoder.html#encode(java.lang.CharSequence)
[#4]: https://openjpa.apache.org/builds/1.2.3/apache-openjpa/docs/jpa_overview_pc.html#:~:text=The%20JPA%20specification%20requires%20that,include%20a%20no%2Darg%20constructor.
