# UNIVERSITY ADMINISTRATIVE System

The system aims to supply a backend side for University Schedule Administrative system.

## Installation & Deployment Steps

### Requirements:

* Docker installed on your local machine

* Database Editor: Personally I prefer Data Grip but other tools works as well.

### Installation:

1. Just start the `docker-compose.yml` file using the command below, which will download the Postgres image and start
   the db

```shell script
 docker-compose up -d
```

2. database user will be created automatically, if you already have Postgres DB, please create user with the command
   below

```shell script
 CREATE USER 'db_user' IDENTIFIED BY '112233ee';
```

3. Run the Application by running `UniversityApplication.java` which will start a Spring Boot Application.

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
