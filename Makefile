run: build
	docker run -p 8080:8080 -t graphql-training

build:
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training