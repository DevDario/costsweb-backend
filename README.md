<h1 align="center">
  CostsWeb Backend
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Purpose&message=Backend Infrastructure for a Frontend Project &color=8257E5&labelColor=000000" alt="Desafio" />
</p>

> Backend Infrastructure for [Costs-Web]([https://github.com/DevDario/Costs-Web) Project, A minimal **Project Manager System**

## Tecnologies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)

## Good Practices Applied

- SOLID, DRY, YAGNI, KISS
- API REST
- Queries With Spring Data JPA
- DI(Dependency Injection)
- Error Treatments
- Automatic API docs Generation with Swagger OpenAPI 3

## How to Run 

- First you need to clone this to your machine
```bash
  git clone https://github.com/DevDario/costsweb-backend
```

- Then access it
```bash
  cd costsweb-backend
```

- Now you can build the project:
```bash
$ ./mvnw clean package
```
- and then run it:
```bash
$ java -jar target/costswebapi-0.0.1-SNAPSHOT.jar
```

You can access the API here: [localhost:8080](http://localhost:8080). <br>
And you can see Swagger running right here [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints
1. Operations(CRUD) with Projects
2. Operations(CRUD) with Project Services

### 1. Operations(CRUD) with Projects

> _Note: I've used [httpie](https://httpie.io) to perform the above requests in the CLI_

- Create a new Project
```
$ http POST :8080/project/new name="project1" budget=1000 usedBudget:0.0 category:"DEVELOPMENT" deadline:"2024-10-14T00:00:00.000+00:00"

[
  {
    "id": 1,
	"name": "project1",
	"budget": 1000.0,
	"usedBudget": 0.0,
	"category": "DEVELOPMENT",
	"createdAt": "2024-10-14T00:00:00.000+00:00",
	"deadline": "2024-10-20T00:00:00.000+00:00",
	"services": []
  }
]
```

- List all Projects
```
$ http GET :8080/project/all

[
  {
    "id": 1,
	"name": "project1",
	"budget": 1000.0,
	"usedBudget": 0.0,
	"category": "DEVELOPMENT",
	"createdAt": "2024-10-14T00:00:00.000+00:00",
	"deadline": "2024-10-20T00:00:00.000+00:00",
	"services": []
  }
]
```

- Update a Project
```
$ http PUT :8080/project/edit/1 name="project1" budget=2000 usedBudget:0.0 category:"PLANNING" deadline:"2024-10-14T00:00:00.000+00:00"

[
  {
    "id": 1,
	"name": "project1",
	"budget": 2000.0,
	"usedBudget": 0.0,
	"category": "PLANNING",
	"createdAt": "2024-10-14T00:00:00.000+00:00",
	"deadline": "2024-10-20T00:00:00.000+00:00",
	"services": []
  }
]

```

- Delete a Project
```
http DELETE :8080/project/del/1

[ ]
```

### 2. Operations(CRUD) with Project Services