# README
##How to run

Run "make" or "make run"

##How to query

Send the query as a query param. Below is an example query that fetches all the data for criminal with id 2:

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