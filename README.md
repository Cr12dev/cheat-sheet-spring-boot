# Cheat Sheet Spring Boot

Small project: a full REST API where I'm learning the basics of Spring Boot to build better things. (**Controllers**, **Repository**, **Services** & **DTOs**)

## ⭐ Stack
- **Backend**: Java SpringBoot
    - Lombok
    - JPA
-  **Frontend**: Next.ts  (The frontend development has not yet begun.)
-  **DB**: MySQL
-  **DevOps**: Docker, maybe kubernetes in the future



## 🐳 Docker

### Build

```bash
docker build -t primeraweb .
```

### Run

```bash
docker run -p 8643:8643 -e DB_URL="jdbc:mysql://host.docker.internal:3306/miprimeraweb" -e DB_USERNAME="root" primeraweb
```
