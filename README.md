# cloud-parking-project

## Run Database
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Close the port 5432 before start
lsof -i :5432
kill "pid using the port"

