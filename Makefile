run: start-database build-api

run-without-tests: start-database build-api-skip-test

init: build-database build-api

reset: stop-containers
	docker volume rm graphql_training_test_data

stop: stop-containers

build-api:
	./mvnw clean
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training
	docker-compose up --detach --build api

build-api-skip-test:
	./mvnw clean
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training -DskipTests
	docker-compose up --detach --build api

build-database:
	docker-compose up --detach --build test_database
	echo "Waiting 5 seconds for the database to start up..."
	sleep 5
	echo "Adding initial data..."
	./script/docker_data_init.sh

start-database:
	docker-compose up --detach test_database

stop-containers:
	docker-compose down