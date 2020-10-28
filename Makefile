run: build-database build-api
	docker-compose up --detach --build api

build-api:
	./mvnw clean
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training

build-database:
	docker-compose up --detach --build test_database
	echo "Waiting 5 seconds for the database to start up..."
	sleep 5
	echo "Adding initial data..."
	./script/docker_data_init.sh

stop:
	docker-compose down