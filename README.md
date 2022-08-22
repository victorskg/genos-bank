# Caso de Estudo

## O que são Microserviços?
São aplicações ou serviços pequenos e de baixo acoplamento que podem falhar independente uma das outras. Quando um microserviço falha, apenas uma pequena parte ou funcionalidade do sistema se torna indisponível, enquanto o resto do sistema continua funcionando.

### Principios
- Não devem compartilhar código ou dados
- Deve-se evitar acoplamento desnecessário entre microserviços
- Deve-se ter alta coesão e baixo acoplamento 
- Independencia e autonomia são mais importantes que componentes reutilizáveis
- Cada microserviço deve ter uma única funcionalidade
- É preferível que a comunicação entre microserviços ocorra por meio de eventos, evitando comunicação direta

## CQRS
Design pattern de microserviço (Command Query Responsability Segregation) que prega que comandos devem guiar mudanças de estado de entidades enquanto querys devem recuperar (consultar) o estado de um objeto na aplicação

## Event Sourcing
Prega que cada evento de mudança de estado de uma entidade de domínio deve ser armazeno em uma base de dados de maneira sequêncial e imutável, invés de apenas salvar o estado atual do objeto

### Benefícios
- O armazenamento dos eventos garante um log completo de mudanças de estado do objeto de domínio
- O estado de um objeto pode ser recriado simplesmente reenviando os eventos armazenados
- Em caso de falhas na base de leituras, é possível recria-las simplesmente lendo a base de eventos e recriando o estado dos objetos
