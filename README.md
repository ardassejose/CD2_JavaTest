# ** Teste de seleção para vaga de Java

# Pré-requisitos

API para gerenciar fretes (CRUD) em todo território nacional que faz parte do Desafio para pessoas desenvolvedoras backend que se candidatam para a CD2 Retail Tech

## Tecnologias
* Spring Boot;
* Spring MVC;
* Spring Data JPA;
* Hibernate;
* SpringDoc OpenAPI 2.5 (Swagger);
* Lombok;
* MySQL.

## Práticas adotadas
* SOLID, DRY, MVC;
* API REST;
* Consultas com Spring Data JPA;
* Injeção de Dependências;
* Testes automatizados;
* Tratamento de respostas de erro;
* Geração automática do Swagger com a OpenAPI 2.5;

## Como executar
* Clonar repositório git
> `git clone https://github.com/ardassejose/CD2_JavaTest.git`

* Construir o projeto:
> `$ ./mvnw clean package`

* Executar a aplicação
> `$ java -jar target/demo-0.0.1-SNAPSHOT.jar`

A API será acessar em [localhost:8080](localhost:8080). O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP, foi utilizada a ferramenta [Postman](https://www.postman.com/downloads/).
Anexo ao projeto está o arquivo _CD2 - Postman HTTP.postman_collection.json_ com uma Collection para importar e testar as rotas:
- GET
- POST
- PUT
- DELETE

## DESAFIO

Implementar apenas a API (Backend)
Versão Java +8 (caso seja Java 8, verificar compatibilidade da sua IDE)
Versão Spring Boot >= 2.4
Banco de dados fica a seu critério (SQL, NoSQL)
Seu projeto deve obrigatoriamente ter as anotações: @Repository, @Entity e @Controller
Documentação mínima da API (Swagger ou documento PDF)

# Objetivo
Implementar para empresa de transporte de cargas SigaBem o endpoint para o cálculo do preço do frete:

Você deve calcular o valor total do frete e a data prevista da entrega.

Considerar regras para calcular o valor do frete:
 * CEPs com DDDs iguais tem 50% de desconto no valor do frete e entrega prevista de 1 dia
 * CEPs de estados iguais tem 75% de desconto no valor do frete e entrega prevista de 3 dias
 * CEPs de estados diferentes não deve ser aplicado o desconto no valor do frete e entrega prevista de 10 dias
 * O valor do frete é cobrado pelo peso da encomenda, o valor para cada KG é R$1,00

Seu input de entrada deve ser “peso”, “cepOrigem”, “cepDestino” e “nomeDestinatario“

Você utilizará a API gratuita de consulta de CEP abaixo: 
Documentação da API: https://viacep.com.br/
Exemplo do GET: https://viacep.com.br/ws/<CEP_A_CONSULTAR>/json/

Endpoint pode ser público
Response/Output deve possuir: “vlTotalFrete” e “dataPrevistaEntrega”, “cepOrigem” e “cepDestino”
Deve ser persistido no banco os valores da cotação os valores consultados: “peso”, “cepOrigem”, “cepDestino”, “nomeDestinatario”, “vlTotalFrete”, “dataPrevistaEntrega” e “dataConsulta”

# Critérios de avaliação:
 * Implementação das regras de negócios para o cálculo do frete
 * Boas práticas de programação, arquitetura  e padrões de projetos

# Entrega: 
 * Disponibilizar um link do repositório no GitHub e encaminhar para developer@cd2.com.br
