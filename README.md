# Package-Webservices

This simple web service exposes the following endpoints:

|Method | Path       | Body | Notes |
|------ | ---------- | ---- | ----- |
| PUT   | /packages/ | ``` {"description" : "Your Package Description", "name" : "Your Package Name", "productIds" : ["id1", "id2"]} ``` | Creates a new package using the body as the definition. Each field in the body json is REQUIRED, empty productIds arrays are not accepted. Will return a Location header detailing where the newly created package can be found. |
| GET   | /packages/{id} | N/A  | Gets the package with the package id provided in {id} |
| GET   | /packages/{id}/{currencyCode} | N/A  | Gets the package with the package id provided in {id} however the value of the price will be converted to the currency provided, if the currency provided does not match any known currency, USD will be used by default |
| POST   | /packages/{id} | ``` {"description" : "Your Package Description", "name" : "Your Package Name", "productIds" : ["id1", "id2"]} ```  | Modifies the package with the package id provided in {id} using the details in the body. Unlike the PUT, each field here is optional and if omitted, the field will be unchanged on the package. |
| DELETE   | /packages/{id} | N/A  | Deletes the package with the package id provided in {id} |
| GET   | /packages | N/A  | Lists all available packages |

# About the data store
This current implementation uses an in memory HSQL database, but we use Spring Boot's auto configuration and simple changes to maven dependencies and application.properties file will allow this system to run on any JPA compatible data store.

# How do I run it?
0. From your IDE: Set up a run profile which uses ```PackagesService.main()``` as the main entrypoint
1. From the command line run: ```mvn clean package``` and then ```java -jar package-services-1.0-SNAPSHOT.jar```
