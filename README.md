# Golden Raspberry - Instruções para rodar

API em Spring Boot que carrega um CSV com indicações ou vencedores da categoria de Pior Filme do Golden Raspberry Award.

## Pré-requisitos
- Java recomendado 17+ (na versão 24+ o lombok ainda não é executado)
- mvn 3+

## Compilação
- Na raiz do projeto rodar:
  - `mvn clean install`

## Executar aplicação
- Na raiz do projeto rodar:
  - `mvn package`
  - `java -jar target/*.jar`

## Executar testes de integração
- `mvn clean verify`

## Endpoints
- A aplicação inicia por padrão na porta `8080`
- O endpoint principal está no controlador ProducerAwardController `producers/award-interval`
- Para acessar o banco de dados `h2-console` e informar os dados que estão no arquivo application.properties.