# Unified Eats API

Projeto desenvolvido como parte do **Tech Challenge – Pós FIAP (Arquitetura e Desenvolvimento Java)**.

Esta API fornece endpoints para gerenciamento de usuários, incluindo autenticação, validações, atualização de dados e troca de senha, seguindo boas práticas de arquitetura REST.

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL 8
* Docker e Docker Compose
* Swagger / OpenAPI
* Postman

---

## Como rodar o projeto

### Pré-requisitos

* Docker
* Docker Compose

### Subindo a aplicação

No diretório raiz do projeto, execute:

```bash
docker compose up --build
```

Aguarde os containers subirem completamente.

---

## URLs importantes

* API: [http://localhost:8080](http://localhost:8080)
* Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Banco de Dados

* Banco: MySQL 8
* Porta: 3307
* Database: tech_challenge

As configurações estão definidas no arquivo `docker-compose.yml`.

---

## Testes com Postman

A collection do Postman está disponível na pasta:

```
postman/unifiedeats.postman_collection.json
```

Ela contém exemplos de:

* Casos de sucesso
* Casos de falha
* Validações
* Autenticação
* CRUD completo de usuários

---

## Funcionalidades Implementadas

* CRUD de usuários
* Busca de usuários por nome
* Validação de e-mail e login únicos
* Autenticação de usuário
* Troca de senha em endpoint separado
* Atualização de dados em endpoint separado
* Tratamento de erros utilizando RFC 7807 (ProblemDetail)
* Documentação automática com Swagger

---

## Observações

Este projeto foi estruturado seguindo boas práticas de organização, separação de responsabilidades e padrões REST.

---

Desenvolvido para fins educacionais no contexto do Tech Challenge – FIAP.
