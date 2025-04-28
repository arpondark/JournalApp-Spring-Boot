# Test and Run JournalApp API

## Overview

JournalApp is a Spring Boot application that provides end-to-end encrypted journal entries. It includes a RESTful API for managing users and journal entries, making it easy to test and interact with the application.

## Prerequisites

Before testing the API, ensure the following are installed on your system:

- **Java 17**: Required to run the application.
- **Maven**: For building and managing dependencies.
- **MongoDB**: The application uses MongoDB as its database. Ensure MongoDB is running locally or provide a connection string.

## Steps to Test the API

1. **Clone the Repository**  
  Clone the JournalApp repository to your local machine:
  ```bash
  git clone <repository-url>
  cd JournalApp
  ```

2. **Configure MongoDB**  
  Update the `application.properties` file in `src/main/resources` with your MongoDB connection details:
  ```properties
  spring.data.mongodb.uri=mongodb://localhost:27017/journalapp
  ```

3. **Build the Application**  
  Use Maven to build the project:
  ```bash
  mvn clean install
  ```

4. **Run the Application**  
  Start the application using the following command:
  ```bash
  mvn spring-boot:run
  ```

5. **Access the API**  
  Once the application is running, the API will be available at `http://localhost:8080`. You can use tools like [Postman](https://www.postman.com/) or `curl` to test the endpoints.

## Example Endpoints

Here are some example API endpoints you can test:

- **Health Check**  
  Verify the application is running:
  ```bash
  GET http://localhost:8080/health
  ```

- **Create a Journal Entry**  
  Add a new journal entry:
  ```bash
  POST http://localhost:8080/journal-entries
  Content-Type: application/json

  {
   "title": "My First Entry",
   "content": "This is a test journal entry."
  }
  ```

- **Get All Journal Entries**  
  Retrieve all journal entries:
  ```bash
  GET http://localhost:8080/journal-entries
  ```

- **Create a User**  
  Register a new user:
  ```bash
  POST http://localhost:8080/users
  Content-Type: application/json

  {
   "username": "testuser",
   "password": "securepassword"
  }
  ```

## Testing with Postman

1. Import the API collection into Postman.
2. Set the base URL to `http://localhost:8080`.
3. Use the provided endpoints to test the application.

## Troubleshooting

- Ensure MongoDB is running and accessible.
- Check the logs for any errors during startup or API calls.

By following these steps, you can easily test and run the JournalApp API. JournalApp

## Overview

JournalApp is a Spring Boot application designed to provide end-to-end encrypted journal entries. It leverages MongoDB for data storage and includes a RESTful API for managing users and journal entries.

## Project Structure

- **src/main/java/com/arpon/JournalApp**
  - **Controller**: Contains the REST controllers for handling HTTP requests.
    - `JournalAppEntryController.java`: Manages journal entry-related requests.
    - `UserController.java`: Manages user-related requests.
    - `HealthCheck.java`: Provides health check endpoints.
  - **Service**: Contains the business logic.
    - `JournalEntryService.java`: Handles operations related to journal entries.
    - `UserService.java`: Handles operations related to users.
  - **Repository**: Interfaces for data access.
    - `JournalEntryRepository.java`: Repository for journal entries.
    - `UserRepository.java`: Repository for users.
  - **Entity**: Defines the data models.
    - `JournalEntry.java`: Represents a journal entry.
    - `User.java`: Represents a user.

## Dependencies

The project uses the following dependencies:

- **Spring Boot Starter Web**: For building web applications.
- **Spring Boot Starter Data MongoDB**: For MongoDB integration.
- **Lombok**: To reduce boilerplate code.
- **Spring Boot Starter Test**: For testing purposes.

## Build and Run

To build and run the application, use the following Maven commands:

```bash
# To build the project
mvn clean install

# To run the application
mvn spring-boot:run
```

## Java Version

The project is configured to use Java 17.

## License

This project is licensed under the MIT License. 