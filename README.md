# cloud-parking-project

## Run Database
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Stop Database
docker stop parking-db
## Start Database
docker start parking-db

## Close the port 5432 before start
lsof -i :5432
kill "pid using the port"

## if running the test "whenFindOutThenCheckResult" return in console "permission denied". Try to run this command in Linux terminal.
chmod -R a+rwx target/
