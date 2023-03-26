# expensemanager-api
## Problema

Atualmente muitas pessoas têm falta de controle financeiro e de organização de suas finanças
pessoais, devido alguma dificuldade em acompanhar suas receitas e despesas, podendo
resultar em gastos desnecessários e endividamento.

## Objetivo
Este Software de Gestão de Despesas tem como objetivo ajudar os usuários a ter uma visão
clara e organizada de suas finanças, permitindo que eles tomem decisões mais conscientes e
sejam mais bem-sucedidos no gerenciamento de seu dinheiro.

## Público alvo
O Expense Manager é destinado a pessoas que desejam gerenciar suas despesas pessoais e
de negócios de forma eficiente e fácil.

## Build Setup
To run this project is necessary to have installed the following tools:
- Gradle
- Java 17
- Docker

Run the following command to download the dependencies:
``` bash
  gradle build --refresh-dependencies
```

Before that, run the following command to create the docker containers and start the application:
``` bash
  docker-compose up --build -d
```

The application will be available on http://localhost:8080 using PostgreSQL database.

# 
> Trabalho final da disciplina Desenvolvimento Web e Mobile - UPF 2023/1
