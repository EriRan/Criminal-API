run: build
	docker-compose up --build --detach

build:
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training

stop:
	docker-compose down