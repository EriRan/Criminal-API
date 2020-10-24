#!/bin/sh
# Initialize test data in the docker database
# Ran as a part of the make script so the file paths are from the root of the project

echo "Initializing database..."

docker cp script/database/schema.sql test_database:/schema.sql
docker container exec -it test_database chmod o+x schema.sql
docker container exec -it test_database psql -h localhost -d postgres -U root -p 5432 -a -q -f schema.sql

echo "Database initialization done!"