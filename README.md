# DeckOfCards
Deck of Cards Assignment for Appian 


# How to Run in Eclipse IDE 

	Right click on CodeTestApplication.java and click Run As -> Java Application. Will start Tomcat 
	webserver, with all of the logic from the Spring Container. 

# How to Run in VSCode IDE (I used)

    Right click on CodeTestApplication.java and click Run. Will start Tomcat 
	webserver, with all of the logic from the Spring Container. 

# How to run from CommandLine

In the project root directory run the below maven commands

`mvn clean install`

Then

`mvn spring-boot:run`


# How to Test the Services

    I attempted to make as easy as possible for the requirements. I include 3 endpoints that can be hit from the browser or using Postman.


    http://localhost:8080/shuffle
    http://localhost:8080/dealcard
    http://localhost:8080/reset


    Where reset creates a new deck and essentially starts back from scratch -- designed so you don't need to restart spring to reset deck.
