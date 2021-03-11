# World Vaccination

Desenvolvimento de uma api para os dados de vacinação mundial

Esta aplicação foi desenvolvida em dois projetos, um projeto spring com o backend e um projeto em Angular com o frontend
da aplicação.

---

## Links para os projetos

<br/>

[Backend Spring](https://github.com/alisonjs/world-vaccination)

[Frontend Angular](https://github.com/alisonjs/world-vaccination-dashboard)

#### Demo
[World vaccination API](https://world-vaccinations.herokuapp.com/swagger-ui.html)

[World vaccination dashboard](https://world-vaccinations-dashboard.herokuapp.com/swagger-ui.html)

<br/>

---

## Backend Spring

RESTFul API desenvolvida com Spring Boot e PostgreSQL

### O que foi desenvolvido

- [x] Carregamento do Dataset na base de dados PostgreSQL
- [x] Apresentação de gráficos com frontend Angular 11
- [x] Utilização da biblioteca Plotly
- [x] Consumo do dataset via API

### Pendente

- [ ] Operação administrativa para atualização do dataset
- [x] Autenticação do usuário via JWT
- [x] Documentação da API com Swagger
- [x] Publicação do projeto no heroku

# Item Extras (Pendentes)

- [ ] Utilizar testes de unidade
- [ ] Utilizar técina de regressão linear


### Banco de dados

```shell
CREATE DATABASE world_vaccination;
```

### Parâmetros

É possível configurar os seguintes parâmetros para conexão com o banco de dados disponível em `persistence-application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/world_vaccination
spring.datasource.username=user
spring.datasource.password=pass
```

APIs Dataset

### Como executar o projeto

```shell
git clone https://github.com/alisonjs/world-vaccination.git
cd world-vaccination  
```

### Linux

```shell
./mvnw clean install && ./mvnw spring-boot:run -pl world-vaccination-rest-producer  
```

### Windows

```shell
./mvnw.cmd clean install && ./mvnw.cmd spring-boot:run -pl world-vaccination-rest-producer  
```

### Parâmetros

### API

#### Auth

- auth/signin -> autenticação de usuários
  ```json 
  {"username": "alisonjs", "password":"123"}
  ```

#### Dataset

- dataset/countries -> retorna os nomes dos países
- dataset/countries/total_vaccinations?date -> Retorna o total de pessoas vacinadas de todos os paises
  ```json 
  {"date": "2021-03-04"}
  ```
- dataset/countries/total_vaccinations?limit&date -> Retorna os países com os maiores números de vacinação
    ```json  
    {"limit": 10, "date": "2021-03-04"}
    ```
- dataset/country/?country&date -> Retorna as informações de um país por data
  ```json
    {"country": "Brazil", "date": "2021-03-04"}
  ```
- dataset/country/daily_vaccinations?country&data -> Retorna os dados do histórico de vacinação diária de um país
   ```json
    {"country": "Brazil", "date": "2021-03-04"}
  ```
- dataset/country/dates?country -> Retorna a data inicial e final dos registros de vacinação de um país
    ```json
    {"country": "Brazil"}
  ```

---

## Frontend Angular

Aplicação front end para exibição das informações do Dataset de vacinação.

### Como executar o projeto

```shell
git clone https://github.com/alisonjs/world-vaccination-dashboard.git
cd world-vaccination-dashboard
ng update
ng serve
```

---

### Dependências

 - git
 - node
 - npm
 - @algular/cli

### Referências
- [Angular](https://cli.angular.io/)
- [Node](https://cli.angular.io/)

**Desenvolvedor**

- Alison Jhonas - alison.dev.silva@gmail.com - [Linkedin](https://www.linkedin.com/in/alisonsilva/)