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

## API Endpoints

### Health Check
- **Health Check**  
  Verify the application is running:
  ```bash
  GET http://localhost:8080/health
  ```

### User Management

- **Get All Users**  
  Retrieve all users:
  ```bash
  GET http://localhost:8080/user
  ```

- **Get User by ID**  
  Retrieve a specific user:
  ```bash
  GET http://localhost:8080/user/{id}
  ```

- **Create a User**  
  Register a new user:
  ```bash
  POST http://localhost:8080/user
  Content-Type: application/json

  {
    "username": "testuser",
    "password": "securepassword"
  }
  ```

- **Update User**  
  Update an existing user:
  ```bash
  PUT http://localhost:8080/user/{username}
  Content-Type: application/json

  {
    "username": "updateduser",
    "password": "newpassword"
  }
  ```

- **Delete User**  
  Delete a user:
  ```bash
  DELETE http://localhost:8080/user/{id}
  ```

### Journal Entry Management

- **Get Journal Entries by Username**  
  Retrieve all journal entries for a specific user:
  ```bash
  GET http://localhost:8080/journal/{username}
  ```

- **Get Journal Entry by ID**  
  Retrieve a specific journal entry:
  ```bash
  GET http://localhost:8080/journal/id/{id}
  ```

- **Create a Journal Entry**  
  Add a new journal entry:
  ```bash
  POST http://localhost:8080/journal
  Content-Type: application/json

  {
    "title": "My First Entry",
    "content": "This is a test journal entry."
  }
  ```

- **Update Journal Entry**  
  Update an existing journal entry:
  ```bash
  PUT http://localhost:8080/journal/id/{id}
  Content-Type: application/json

  {
    "title": "Updated Entry",
    "content": "This is an updated journal entry."
  }
  ```

- **Delete Journal Entry**  
  Delete a journal entry:
  ```bash
  DELETE http://localhost:8080/journal/id/{id}
  ```

## Testing with Postman

1. Import the API collection into Postman or create requests manually.
2. Set the base URL to `http://localhost:8080`.
3. Test the endpoints in the following order:
   - First, create a user using the POST `/user` endpoint
   - Then, create journal entries using the POST `/journal` endpoint
   - Retrieve journal entries using GET `/journal/{username}`

### Sample Test Flow

1. **Create a User:**
   ```bash
   POST http://localhost:8080/user
   {
     "username": "john_doe",
     "password": "mypassword123"
   }
   ```

2. **Create Journal Entries:**
   ```bash
   POST http://localhost:8080/journal
   {
     "title": "First Day",
     "content": "Today was a great day to start journaling!"
   }
   ```

3. **Get User's Journal Entries:**
   ```bash
   GET http://localhost:8080/journal/john_doe
   ```

## Troubleshooting

- **MongoDB Connection**: Ensure MongoDB is running and accessible on the configured port.
- **Port Conflicts**: If port 8080 is in use, configure a different port in `application.properties`:
  ```properties
  server.port=8081
  ```
- **Application Logs**: Check the console logs for any errors during startup or API calls.
- **Data Validation**: Ensure required fields (username, password, title) are provided in requests.
- **ObjectId Format**: When using journal entry or user IDs, ensure they are valid MongoDB ObjectId format (24-character hex string).

## Notes

- Journal entries are automatically timestamped when created.
- The application uses MongoDB ObjectIds for entity identification.
- User passwords are stored as plain text (consider implementing encryption for production use).
- Journal entries are associated with users through the User entity's journalEntries list.

By following these steps, you can easily test and run the JournalApp API.

## Features

- **User Management**: Create, read, update, and delete users
- **Journal Entry Management**: Create, read, update, and delete journal entries  
- **User-Journal Association**: Journal entries are linked to specific users
- **MongoDB Integration**: Persistent data storage using MongoDB
- **RESTful API**: Clean and intuitive REST endpoints
- **Health Monitoring**: Built-in health check endpoint

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