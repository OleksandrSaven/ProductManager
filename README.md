# Technologies and Tools Used

This project is built using a variety of technologies and tools. Here's an overview of the key components:

- **Spring Boot**: This project is based on the Spring Boot framework, which simplifies the setup and development of Java applications.

- **Spring Security**: Spring Security is used to handle authentication and authorization, ensuring that only authorized users can access certain endpoints.

- **Spring Data JPA**: Spring Data JPA simplifies database access and management, allowing seamless interaction with the database using Java objects.

- **Swagger**: Swagger is integrated into the project to provide interactive API documentation. You can explore and test the API endpoints using the Swagger UI.

- **Docker**: Docker is used for containerization, making it easy to deploy and manage the application in various environments.

- **Database**: The project uses a relational database MySQL to store book and user data. The choice of database can be configured to fit your requirements.

- **Maven**: Maven is used for project management and dependency resolution. It simplifies the build and package process.
-
- ## How to use

Clone the project's repository from a version control system like Git.
- **Database Configuration**:
    - ***Local database:*** Check the project's configuration files (usually in the src/main/resources directory) to configure your database connection settings. Ensure you have a MySQL database set up and that you provide the correct credentials.
    - ***Using Docker:*** If you haven't already installed Docker, you can download and install it from the official Docker website: https://docs.docker.com/get-docker/. In the root directory you can find docker-compose.yml file and run it. This setup will allow you to run this application along with a MySQL database using Docker without the need to manually install and configure MySQL:

  <pre>docker-compose up </pre> 

- **Build the Project**:

Open a terminal/command prompt in the project's root directory and run the following command to build the project using Maven:
<pre>
mvn clean install
</pre>
- **Start the Application**:

If you're not using Docker, you can start the Spring Boot application directly by running the following command:
bash
<pre>
java -jar target/online-book-store-0.0.1-SNAPSHOT.jar
</pre>
If you're using Docker, you can create a Docker image for your application and then run a container. Ensure that Docker is properly configured on your system. Refer to your project's documentation for Docker-specific instructions.
Access the Application:
Once the application is up and running, you can access it by opening a web browser and navigating to the appropriate URL. The URL should be provided in the project's documentation or configuration.
API Documentation (Swagger):
Swagger is integrated into the project, you can access the interactive API documentation by navigating to the Swagger UI URL. It usually looks something like: http://localhost:8080/api/swagger-ui/index.html.
