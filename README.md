# README
## How to run

Run "make". This will create two docker containers: api and db using postgresql

## How to stop all containers

Run "make stop"

## How to run a query

Send the query as a query param to url localhost:8080/graphql. Below is an example query that fetches all the data for
 criminal with
 id 2:

{
	criminalById(id: "2") {
		id
		name
		appearance
		charge {
			id
			description
		}
	}
}