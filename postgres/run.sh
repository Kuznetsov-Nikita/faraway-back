docker build -t faraway_postgres_bd .
docker run -d -p 9000:5432 --name faraway_postgres_bd faraway_postgres_bd
