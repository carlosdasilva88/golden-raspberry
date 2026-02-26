# Golden Raspberry - Instruções para rodar

API em Spring Boot que carrega um CSV com indicações ou vencedores da categoria de Pior Filme do Golden Raspberry Award.

## Pré-requisitos
- Java recomendado 17+
- mvn 3+

## Compilação
- Na raiz do projeto rodar:
  - mvn clean install

## Executar aplicação
- Usando Maven: 
  - mvn spring-boot:run -Dspring-boot.run.arguments="--data.csv.path=/caminho/para/Movielist.csv"
- Usando JAR:
  - mvn package (isso vai criar um arquivo .jar na pasta target)
  - java -jar target/*.jar --data.csv.path=/caminho/para/Movielist.csv

## Observações sobre o CSV
- Por padrão a aplicação lê o caminho definido no application.properties (data.csv.path).
- Para facilitar o teste, coloque o arquivo Movielist.csv em src/main/resources e/ou passe o caminho via argumento (--data.csv.path=/full/path/Movielist.csv).
- Caso queria, você pode ataualizar o application.properties com o caminho do arquivo

## Executar testes de integração
- mvn clean verify

## Endpoints
- A aplicação inicia por padrão em http://localhost:8080
- O endpoint principal está no controlador ProducerAwardController.
  - Acesse http://localhost:8080//producers/interval para listar os produtores com menor e maior intervalo de vitórias
  - Para alimentar a tabela com novos dados, você pode chamar o endpoint http://localhost:8080/producers/upload?file passando o novo arquivo csv
- Para acessar o banco de dados http://localhost:8080/h2-console e informar os dados que estão no arquivo application.properties.