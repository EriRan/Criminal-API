run: build
	docker-compose up --build --detach
	echo "Waiting 5s for db to start up..."
	sleep 5
	echo "Continuing"
	./script/docker_data_init.sh

build:
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training

stop:
	docker-compose down