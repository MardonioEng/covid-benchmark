# 🚀 [Planisa](https://planisa.com.br/site/) Challenge | CODIV-19 Benchmark

## 📝 Descrição
Uma ferramenta web para análise comparativa de dados da COVID-19 entre países, permitindo cadastrar e visualizar benchmarks personalizados com métricas como casos confirmados e óbitos em períodos específicos. Esta aplicação consome dados da [API Ninjas](https://api-ninjas.com/api/covid19).

## 🛠️ Tecnologias Utilizadas
### Backend
- Java 17
- Spring Boot 3.4.2
- Junit 5.11
- PostgreSQL 15
### Frontend
- React 19
- Bootstrap 5.3
### Infra
- Docker
- Docker Compose

## 🏗️ Arquitetura
O projeto está dividido em duas principais aplicações:
- **Backend**: API REST desenvolvida com Spring Boot
- **Frontend**: Interface desenvolvida com React

## 🚀 Como Executar

### Pré-requisitos
- Docker
- Docker Compose
- Git

### Configuração do Ambiente
1. Clone o repositório:
```bash
git clone https://github.com/MardonioEng/covid-benchmark.git
cd covid-benchmark
```

2. Configure as variáveis de ambiente:
```bash
cp .env.example .env
```
Edite o arquivo `.env` com suas configurações locais. </br>
Para obter um token da API Ninjas, acesse: https://api-ninjas.com/api/covid19

### Executando o Projeto
Para iniciar todas as aplicações usando Docker Compose:
```bash
docker-compose up -d
```

O projeto estará disponível em:
- Frontend: http://localhost:3000

## 📚 Documentação da API
A documentação da API está disponível através do Swagger UI:
http://localhost:8080/docs.html

## 📦 Estrutura do Projeto
```
.
├── backend/           # Aplicação Spring Boot
├── frontend/          # Aplicação React
├── docker-compose.yml # Configuração Docker Compose
├── .env.example      # Exemplo de variáveis de ambiente
└── README.md
```

