# Visão Geral do Projeto


*Sistema de revenda digital com gestão de veículos, vendas e integração com pagamentos*

Este projeto foi desenvolvido como parte do **Tech Challenge - Pós-Tech SOAT (Fase 2)**. Ele simula a transformação digital de uma empresa de revenda de veículos, oferecendo uma API robusta para gestão de produtos e vendas, além de integração com um serviço de pagamentos.

## 🎯 Visão de Negócio

A plataforma online permite:

- ✅ **Cadastrar veículos para venda** (marca, modelo, ano, cor, preço)
- ✏️ **Editar informações** dos veículos
- 💰 **Efetuar a venda** de veículos, vinculando comprador e data
- 📃 **Listar veículos disponíveis e vendidos**, ordenados por preço
- 🔄 **Receber notificações de pagamento via webhook** com status (efetuado/cancelado)

> **Objetivo**: tornar o processo de revenda mais transparente, rastreável e eficiente.

---

## 🧱 Visão Técnica

*Separação de responsabilidades usando Clean Architecture*

O projeto segue os princípios de:
- **Clean Architecture**
- **SOLID**
- **Desenvolvimento orientado a microsserviços**

A infraestrutura foi desenhada para execução em ambientes **Docker** e **Kubernetes**.

### Inclui:
- `Dockerfile` para cada serviço
- `docker-compose.yml` para ambiente local
- Manifestos Kubernetes: `Deployment`, `Service`, `ConfigMap`, `Secret`
- Documentação de API via **Swagger/OpenAPI**

---

## 📦 Estrutura dos Microsserviços

*Serviços independentes com integração via REST*

- `vehicle-service`: Cadastro e listagem de veículos
- `sale-service`: Venda e status da transação
- `payment-service`: Recebimento de confirmação de pagamento e atualização da venda




# Payment Service

Este é o microserviço responsável por gerenciar pagamentos na solução do Tech Challenge.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Gradle
- PostgreSQL
- Clean Architecture
- Docker
- Kubernetes
- Swagger

## 🛠️ Estrutura do Projeto

```
payment-service
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── fiap
│   │   │           ├── controller
│   │   │           ├── dto
│   │   │           ├── entity
│   │   │           ├── usecase
│   │   │           ├── gateway
│   │   │           ├── repository
│   │   │           └── client
│   │   └── resources
│   │       └── application.yml
│   └── test
│       └── java
│           └── com
│               └── fiap
└── build.gradle
```

## 🚦 Fluxo do Pagamento

1. Recebe solicitação de pagamento.
2. Cria registro do pagamento.
3. Simula a confirmação do pagamento.
4. Atualiza status da venda no `sale-service` para `CONCLUIDO`.

## 🚀 Como executar localmente (Docker Compose)

1. Gere o build da aplicação:

```bash
./gradlew clean build
```

2. Criar a network para que as aplicações se comuniquem entre os containers:


```bash
docker network create microservices-network
```

3. Suba os containers com Docker Compose:

```bash
docker-compose up --build
```

4. Acesse o Swagger:

```
http://localhost:8083/swagger-ui/index.html
```

## ☸️ Como executar no Kubernetes (Docker Desktop)

1. Certifique-se que o Kubernetes está habilitado no Docker Desktop.

2. Construa a imagem da aplicação:

```bash
docker build -t payment-service:latest .
```

3. Aplique os manifests do Kubernetes:

```bash
kubectl apply -f .\k8s\postgres\
kubectl apply -f .\k8s\payment\local\
```

4. Verifique os serviços e pegue a porta NodePort:

```bash
kubectl get svc
```

Acesse no navegador usando a porta exibida:

```
http://localhost:<NODE_PORT>/swagger-ui/index.html
```

A aplicação irá subir na porta **8083**.

## 🧪 Testes

Para rodar os testes automatizados:

```bash
./gradlew test
```
![Cobertura de Testes](images/Cobertura%20de%20Testes%20-%20payment-service.png)

---

