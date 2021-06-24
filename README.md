# Person API

Person API é uma Rest API criada com Spring Boot, desenvolvida durante o Boot Camp Code Anywhere, disponibilizado pela [Digital Innovation One](https://web.digitalinnovation.one).

## :rocket: Tecnologias

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java para criação de APIs REST.
* [Project Lombok](https://projectlombok.org/) - Biblioteca Java que ajuda a remover a verbosidade do nosso código usando anotações.
* [Map Struct](https://mapstruct.org/) - Framerowk Java para mapear DTOs.
* [JUnit5](https://junit.org/junit5/) - Framework Java para testes unitários.
* [H2](http://www.h2database.com/html/features.html) - Banco de dados em memória.

## :wrench: Como executar

1. Baixe o repositório
2. Abra o seu terminal e navegue até o diretório onde o repositório se encontra
3. Execute o comando `mvn spring-boot:run`

## :heart: Contribuições

## :memo: Endpoints

| Endpoint                                 | Method | Path Variable | Request Body |
|------------------------------------------|--------|---------------|--------------|
| http://localhost:8080/api/v1/people      | GET    | No            | No           |
| http://localhost:8080/api/v1/people/{id} | GET    | Yes           | No           |
| http://localhost:8080/api/v1/people      | POST   | No            | Yes          |
| http://localhost:8080/api/v1/people/{id} | DELETE | Yes           | No           |
| http://localhost:8080/api/v1/people      | PUT    | No            | Yes          |

Caso queria contribuir com o projeto, por favor, abra um pull request.
