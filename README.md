# expensemanager-api
## Problem
Currently, many people lack financial control and organization, struggling to keep track of their income and expenses which can result in unnecessary spending and debt.

## Objective
The Expense Manager software aims to help users have a clear and organized view of their finances, enabling them to make more informed decisions and be more successful in managing their money.

## Target audience
Expense Manager is intended for individuals who want to efficiently and easily manage their personal and business expenses.

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
> Final project for the Web and Mobile Development course - UPF 2023/1
