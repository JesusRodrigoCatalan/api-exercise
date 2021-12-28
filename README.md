#Project Title
Exercise 1 - REST API Automation

#Project Description
Java application implemented to test the following API: https://petstore.swagger.io/

The task is divided in four different checks, as follow:
-Get "available" pets. Assert expected result
-Post a new available pet to the store. Assert new pet added.
-Update this pet status to "sold". Assert status updated.
-Delete this pet. Assert deletion.

# How to install and usage
You can download the project from:
https://github.com/JesusRodrigoCatalan/api-exercise.git
Previous to running the test application in your IDE, check that you have installed the following:
-Java JDK 1.8
-Maven
To run the tests, open Terminal tab and go to the project root.
Execute the following command: mvn test
Or directly running from from the class src/test/java/jesus/rodrigo/runners/TestRunner.java

#Technologies used:
-Cucumber framework 7.1.0
-Rest Assured libraries 4.4.0 
-Junit 4.11

#Configuration 
The project contains the following packages and resources:

####src/main/java/jesus/rodrigo/api 
Contains PetApiService.class. 
Used for API interaction (requests/responses)
####src/main/java/jesus/rodrigo/constants
Contains Constants.class.
Used for storing constant config parameters.
####src/main/java/jesus/rodrigo/models
Contains Pet.class, PetCategory.class, Tag.class. 
Used for managing info according wth the data model used by the API

####src/main/java/jesus/rodrigo/runners
Contains TestRunner.class .
Class which triggers the application.
####src/main/java/jesus/rodrigo/steps
Contains CheckApiSteps.class.
Used for implementing steps corresponding to behavioural scenarios, that lead 
to API interaction methods.

###src/test/resources/features
Contains check_pet_store.feature
Used for implementing the different scenarios to be accomplished in the test, 
in Gherkin sintaxis.










