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

[#1]: https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
[#2]: https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-argon2
[#3]: https://docs.spring.io/spring-security/site/docs/6.2.1/api/org/springframework/security/crypto/argon2/Argon2PasswordEncoder.html#encode(java.lang.CharSequence)
