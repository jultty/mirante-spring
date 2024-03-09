# Mirante: Relatório para v0.1.2

## Tarefas
- [x] Resolução de uma regressão de recursão infinita ao tentar implementar a lógica de autenticação

## Desenvolvimento

A implementação da versão v0.1.1 fornecia todos os dados apenas no endpoint `/option`, que retornava todas as opções existentes.

Para a versão `0.2.0` previa-se o oposto: `/option` não deveria retornar nada, e `/option/{id}` deveria retornar apenas as informações daquela opção, com seu exercício na forma simples do _id_  e não toda a estrutura do exercício.

Embora esta questão tenha sido resolvida com o uso do padrão de `DTOs`, foi introduzido um _bug_ de recursão infinita onde, ao tentar criar opções, elas tentavam referenciar o exercício a que pertenciam, que por sua vez referenciava todas as suas opções, e assim por diante.

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
