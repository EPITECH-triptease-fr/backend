# Triptease API

This is a Spring application that implements an API for a website using TripAdvisor and Amedeus API.

## Prerequisites

Before getting started, make sure you have the following installed:

- Java Development Kit (JDK) version 17
- Maven 4.0.0
- A database (e.g., MySQL, PostgreSQL)

## Installation

1. Clone this repository to your local machine.
2. Navigate to the root directory of the application.
3. Execute in a shell `docker-compose up -d`.

## Configuration

1. Open the `src/main/resources/application.properties` file.
2. Configure the database settings according to your environment (if you want a custom db):  

`spring.datasource.url=jdbc:mysql://localhost:3306/database_name`  
`spring.datasource.username=user`  
`spring.datasource.password=password` 

3. Modify other properties if necessary.

## Build and Run

1. Open a terminal and execute the following command to build the application:

`mvn clean install`


2. Once the build is successful, run the following command to start the application:

`mvn spring-boot:run`


3. The application will start running and will be accessible at the following URL: `http://localhost:8080`.

## Usage

Here are some important points regarding the usage of the API:

- The API follows a RESTful architecture.
- Endpoints can be accessed using HTTP requests (GET, POST, PUT, DELETE).
- API responses are generally returned in JSON format.

## Contribution

Contributions to this project are welcome. Here's how you can contribute, for this use Karma Commit convention:

1. Fork this repository.
2. Create a branch for your feature (`git checkout -b my-new-feature`).
3. Commit your changes (`git commit -m 'Add a new feature'`).
4. Push your branch to the remote repository (`git push origin my-new-feature`).
5. Open a pull request for your changes to be reviewed.

## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
