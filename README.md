# RankRedis

Sistema de votação em tempo real utilizando **Redis** como banco de dados NoSQL chave-valor, desenvolvido com **Java + Spring Boot**.

---

# Objetivo

O projeto simula uma eleição permitindo:

- Cadastro de candidatos
- Cadastro de usuários
- Login com JWT
- Logout com blacklist de tokens
- Votação em candidatos
- Expiração de tokens utilizando TTL do Redis

---

# Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Data Redis
- Redis
- JWT (JJWT)
- Maven
- Lombok

---

# Estrutura do Projeto

```bash
src/main/java/redis/com/RankRedis
│
├── config
│   ├── JwtFilter
│   ├── SecurityConfig
│   ├── SecurityUsersConfig
│   └── PasswordConfig
│
├── controller
│   ├── AdminController
│   ├── AuthController
|   ├── HomeController
│   └── VotacaoController
│
├── model
│   ├── Candidato
│   ├── Usuario
│   ├── TokenBlackList
|   ├── dto
│   |   └── LoginDTO
|   └── repository
|       ├── CandidatoRepository
|       ├── TokenBlackListRepository
|       └── UsuarioRepository
│
├── service
│   ├── AuthService
│   ├── CandidatoService
│   ├── JwtService
│   └── UsuarioService
│
└── RankRedisApplication
```

---

## Execução

### 1. Clonar o projeto
```bash
git clone https://github.com/CCodeKey/RankRedis
```

### 2. Subir o banco Redis com Docker
```bash
docker run --name redis -p 6379:6379 -d redis
```
### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

---

## Autores
<table> <tr> <td align="center"> <a href="https://github.com/CCodekey"> <img src="https://avatars.githubusercontent.com/u/105808889?v=4" width="100px;" alt="Gabriel T."/><br> <sub> <b>Gabriel Tertuliano</b> </sub> </a> </td> <td align="center"> <a href="https://github.com/kaleu-victor"> <img src="https://avatars.githubusercontent.com/u/169067294?v=4" width="100px;" alt="Kaléu V."/><br> <sub> <b>Kaléu Victor</b> </sub> </a> </td> </tr> </table>

