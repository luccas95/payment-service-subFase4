
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

## ⚙️ Como executar localmente

### Pré-requisitos:

- Docker
- Java 17
- PostgreSQL

### Passos:

1. Clone o repositório
```bash
git clone <url-do-repositorio>
```

2. Entre no diretório
```bash
cd payment-service
```

3. Suba o banco PostgreSQL com Docker
```bash
docker-compose up -d
```

4. Execute o projeto
```bash
./gradlew bootRun
```

## 📦 Docker

Para criar e executar o container:

```bash
docker build -t payment-service .
docker run -d -p 8082:8082 payment-service
```

## 🚀 Deploy no Kubernetes

```bash
kubectl apply -f k8s-deployment.yaml
```

## 📑 Documentação Swagger

Acesse:

```
http://localhost:8082/swagger-ui.html
```

---

Desenvolvido por Felipe 🚀
