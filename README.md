# README

## Requirements

* make (to run build scripts more conveniently)
* Maven (For building the application)
* Docker (For running the containers)

## Quick start

Run "make init". This will build and deploy both api and database docker images.

## Other make commands

* make 
  * This will start the database if it is not started already and rebuild the api image
* make stop
  * Stop all containers but leave the Postgresql data intact
* make reset
  * Stop all containers and remove Postgresql data

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
