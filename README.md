# Web Development challenge:

## _Hospital patient record - Back End_
### _By: Sofía Idárraga_


---
Saint Claire is a hospital that needs to hold in an organized manner the records of their patients.

- This Backend were created with Spring Boot

## Starting

- Download the complete project
- Open the project in IntelliJ Idea (Ultimate by suggestion).
- Set the user and password of your DataSource in _application.properties_
  Like this:
```sh
spring.datasource.username=root
spring.datasource.password= ${mypassword}
```
- Create a schema in MySQL Workbench called _**hospital**_
- Run the project

You must see in consol:
```sh
2022-07-29 12:12:09.756  INFO 12524 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
```

Now you can test the project with an API platform like Postman. Or download the frontend to see the complete project.

---
## Routes
```
${BaseURL} = http://localhost:8080/api
```
| Action                       | Method   | Rout                             |
|------------------------------|----------|----------------------------------|
| Get all medical specialities | `GET`    | `${BaseURL}/speciality`          |
| Create medical speciality    | `POST`   | `${BaseURL}/create/speciality`   |
| Create patient               | `POST`   | `${BaseURL}/create/patient`      |
| Update medical speciality    | `PUT`    | `${BaseURL}/update/speciality`   |
| Update patient date          | `PATCH`  | `${BaseURL}/update/date/patient` |
| Delete speciality            | `DELETE` | `${BaseURL}/delete/speciality`   |
| Delete patient               | `DELETE` | `${BaseURL}/delete/patient`      |

### Take in count

- You cannot repeat specialities' names. So, if you try to create or update one speciality which name is alredy created you will receive an error
- You can delete a medical speciality only if it doesn’t contains any registry of a patient.
- Name, age and dni of a patient cannot be updated
- A new date for a patient will be added to their list of dates
