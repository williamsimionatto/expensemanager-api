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

## Requisitos Funcionais
Utilizando o Expense Manager os usuários poderão inserir e categorizar todas as suas
despesas, definir um orçamento para cada categoria e visualizar relatórios detalhados de suas
despesas para ajudá-los a tomar decisões financeiras mais informadas.

###  RF001 - Manutenção de Categoria de Gastos

> Necessidades Identificadas

Cadastrar, Atualizar e Listar as categorias de gastos

> Solução

Disponibilizar formulário para cadastro/edição e visualização das categorias.
Para o cadastro considerar:
* Nome
* Descrição
* Código (Sequencial)

### RF002 - Manutenção Período de Despesas

> Necessidades Identificadas

Cadastrar, Atualizar e Listar os períodos de despesa

> Solução

Disponibilizar formulário para cadastro/edição e visualização dos períodos
Para o cadastro considerar:
* Código (Sequencial)
* Data Inicial
* Data Final
* Nome
* Orçamento Geral

> Regras de Negócio

* O orçamento Geral deve ser maior que 0
* Data Final não deve ser menor que Data Inicial
* Não devem existir dois períodos para o mesmo intervalo de data

## Build Setup
To run this project is necessary to have installed the following tools:
- Gradle
- Java 17
- Docker

Before that, run the following command to create the docker containers:
``` bash
  docker-compose up -d
```

To run the application, run the following command:
``` bash
  gradle bootRun
```

The application will be available on http://localhost:8080 using mysql database.

# 
> Trabalho final da disciplina Desenvolvimento Web e Mobile - UPF 2023/1
