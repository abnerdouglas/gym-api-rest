# Gym Api Rest

Este projeto consiste em uma api rest que permite o cadastro e login de usuários, além de fornecer uma interface para consulta de informações sobre exercícios físicos. O backend foi desenvolvido em Java, utilizando Spring Boot, e o frontend em Next.js. Toda a aplicação é containerizada com Docker, utilizando Docker Compose para orquestrar os serviços.

## Funcionalidades

### Backend
- **Cadastro de Usuários**: Permite o registro de novos usuários no sistema.
- **Login de Usuários**: Autenticação de usuários utilizando tokens JWT.
- **Consulta de Exercícios**: Integração com uma API externa para buscar informações sobre exercícios físicos, filtrando por parâmetros como tipo, músculo, dificuldade, equipamento, etc.
- **Validações**: As requisições são validadas com base nos parâmetros fornecidos, garantindo que apenas dados válidos sejam processados.
- **Tratamento de Exceções**: Implementação de tratamento de exceções para garantir respostas adequadas para diferentes cenários de erro.
- **Segurança**: Implementação de autenticação e autorização com Spring Security e JWT.

### Frontend
- **Tela de Login**: Interface para que os usuários possam se autenticar no sistema.
- **Tela de Cadastro**: Interface para registro de novos usuários.
- **Dashboard de Exercícios**: Tela onde os usuários autenticados podem consultar exercícios físicos de acordo com os parâmetros desejados.

### Docker
- **Containerização**: A aplicação é totalmente containerizada, utilizando Docker para criar e gerenciar os containers.

## Tecnologias Utilizadas

### Backend
- **Java**: Linguagem de programação utilizada no desenvolvimento do backend.
- **Spring Boot**: Framework que facilita o desenvolvimento de aplicações Java.
- **Spring Security**: Framework de segurança para autenticação e autorização.
- **JWT (JSON Web Token)**: Utilizado para autenticação segura de usuários.
- **JPA (Java Persistence API)**: Framework para persistência de dados.
- **MySQL**: Banco de dados relacional utilizado para armazenar as informações da aplicação.
- **Docker**: Plataforma de containerização que garante que a aplicação rode de maneira consistente em qualquer ambiente.

### Frontend
- **Next.js**: Framework React para renderização do frontend.
- **React**: Biblioteca JavaScript para construção de interfaces de usuário.
- **Chakra UI**: Biblioteca de componentes React.

## Como Executar o Projeto Sem Docker

- Adicione as variáveis necessárias no arquivo application.properties dentro da pasta resources
- Inicialize o projeto através da classe main: GymApplication.java

## Como Executar o Projeto Com Docker

- Adicione o arquivo .env na raiz do projeto com as variáveis necessárias
- Execute o Docker Compose para subir os serviços:
```bash
$ docker-compose up -d
```
- Frontend: http://localhost:3000/login
- Backend: http://localhost:8080/api
- Adminer: http://localhost:8081
