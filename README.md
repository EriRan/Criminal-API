# README

## Requirements

* make (to run build scripts more conveniently)
* Docker (For the containers)

## How to run for the first time

Run "make init". This will build and deploy both api and database docker images

## How to rebuild

Run "make". This will start the database if it is not started already and rebuild the api image

## How to stop all containers

Run "make stop"

## How to do a total reset

Run "make reset". This will shut down all containers and remove the data from the database

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