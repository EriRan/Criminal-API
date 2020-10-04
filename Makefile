build:
	echo "Running build"
	./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=graphql-training