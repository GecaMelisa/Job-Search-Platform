# Job Search Platform Backend

## Description

This Spring Boot project is developed as part of the Web Engineering Course. It serves as the backend for a Job Search Platform, providing APIs for managing users, companies, company owners, jobs, and application forms.

## Models

### User
The `User` model represents individuals using the platform. It includes attributes such as user ID, name, email and other relevant information.

### CompanyOwner
The `CompanyOwner` model extends the `User` model and represents individuals who own or manage companies on the platform. It inherits all attributes from the `User` model and is associated with a `Company`.

### Company
The `Company` model represents companies that can post job listings on the platform. It includes attributes such as company ID, name, address and other relevant details.

### Job
The `Job` model represents job listings posted by companies. It includes attributes such as job ID, position, requirements and other details.

### Application Form
The `ApplicationForm` model represents the form that users submit when applying for a job. It includes attributes such as application ID, education, work experience, cv and other relevant information.

## Key Annotations

- `@Document`: Denotes the class as a MongoDB collection of the same name.
- `@Id`: Represents the primary key of the collection.
- `@Repositoy`: Is part of the Spring Data repository support. It is used to indicate that the annotated class is a repository, which is responsible for encapsulating the interaction with the database or other persistent storage mechanisms.
- `@Service`: Is used to indicate that the annotated class is a service. In Spring, a service is a class that provides business functionalities and represents the service layer of an application.
- `@Controller`: Is used to mark a class as a Spring MVC controller. Controllers in Spring handle HTTP requests, process them, and return an appropriate response.

## Technologies

The backend is built using the following technologies:

- Maven (Build and Dependency Management)
- Java 
- Spring Boot
- NoSQL: MongoDB

-Swagger is used to test routes

## TESTING
-For the purpose of testing the functionality of the routes, UNIT tests were written

## Deployment

This backend project is deployed using Docker and Render.



