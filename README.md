# University Administrative System

The project aims to supply a backend side for University Schedule Administrative system.

## Installation & Deployment Steps

### Requirements:

* `Docker` and `Maven` installed on your local machine

* Database Editor: Personally I prefer `DataGrip` but other db tools will work as well.

### Creating Application Image:

* If it is the first time you are running this application, in terminal run the commands below to create an image of the
  application and restore it to your local Docker image repo.

```shell script
 mvn clean package
 docker build --tag=university:latest .
```

### Creating Network:

* If it is the first time you are running this application, you have to create a Docker network use the command below,
  so your containers can communicate each other

```shell script
docker network create university_system_network
```

### Running Application on Docker:

* Just start the `docker-compose.yml` file using the command below, which will download the Postgres image and start the
  db then start the Application

```shell script
 docker-compose up -d
```

Note: database user will be created automatically, if you already have Postgres DB, please create user with the command
below

```shell script
 CREATE USER 'db_user' IDENTIFIED BY '112233ee';
```

### Running as Boot Application:

Just Run/Debug the Application from `UniversityApplication.java` which will start a Spring Boot Application.

## Testing

### Authentication & Authorization

Basic Authentication is used.

Admin user `(admin)` is included for the system authorization checks. You can see the credentials below:

```shell script
 # admin user
 Username: admin
 Password: 123

```

The user types can reach different API's specified in the requirements document: `\docs\RequirementDocument.pdf`

### APIS

For testing API's Swagger is enabled. You can reach them from the address: http://localhost:8080/swagger-ui/#/.

Optionally you can use the Postman JSON Collection provided from: `\docs\UNIVERSITY.postman_collection.json`

### Credits

github for giving us this amazing facility.

vmaltas
