## command to start postgres
```bash

docker run -d --name postgres-db -e POSTGRES_PASSWORD=user -e POSTGRES_USER=user -e POSTGRES_DB=productdb -p 5432:5432 -v pgdata:/var/lib/postgresql/data postgres:latest

```