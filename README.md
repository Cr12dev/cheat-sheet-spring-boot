# Cheat Sheet Spring Boot

## Docker

### Build

```bash
docker build -t primeraweb .
```

### Run

```bash
docker run -p 8643:8643 -e DB_URL="jdbc:mysql://host.docker.internal:3306/miprimeraweb" -e DB_USERNAME="root" primeraweb
```