# Mirante: Relatório para v0.1.2

## Tarefas
- [ ] Organizar estruturas de dados existentes
- [ ] Revisar estrutura de dados para questões de ordenação
- [ ] Adicionar estruturas de dados para contas
- [ ] Adicionar etruturas de dados para nĩíveis de acesso
- [ ] Adicionar estruturas de dados para receber resultados

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

[#1]: https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
[#2]: https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-argon2
[#3]: https://docs.spring.io/spring-security/site/docs/6.2.1/api/org/springframework/security/crypto/argon2/Argon2PasswordEncoder.html#encode(java.lang.CharSequence)
[#4]: https://openjpa.apache.org/builds/1.2.3/apache-openjpa/docs/jpa_overview_pc.html#:~:text=The%20JPA%20specification%20requires%20that,include%20a%20no%2Darg%20constructor.