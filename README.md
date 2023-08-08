# Coding Challenge

## Approach:

This is the brief Description of the solution:

**Task 1:**

1. **Create ReportingStructure Class:**

   * Create a new Java class named `ReportingStructure` in the `com.mindex.challenge.data` package.
   * Add private fields for `employee` (of type `Employee`) and `numberOfReports` (of type `int`).
   * Generate constructor, getters, and setters for the class.
2. **Update Employee Controller:**

   * Open the `EmployeeController` class and add a new `@GetMapping` endpoint to handle the reporting structure request.
   * Define a method to handle the endpoint. This method should call the `report` method from the `EmployeeService` and return the calculated `ReportingStructure`.
3. **Implement Reporting Logic:**

   * In the `EmployeeServiceImpl` class, implement the `report` method as described in the task.
   * Create a private helper method, `getNumberOfReports`, to calculate the number of direct and indirect reports for an employee using a stack-based approach.
   * Use the `EmployeeRepository` to retrieve employee data from the database.
4. Test Endpoints manually using tools like POSTMAN:

   **sample Endpoint:** http://localhost:8080/employee/report/16a596ae-edd3-4847-99fe-c4518e82c86f

   Which Should return follwing format in JSON

```
{{

    "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3",

    "firstName": null,

    "lastName": null,

    "position": null,

    "department": null,

    "directReports": null

},

{

    "employeeId": "03aa1462-ffa9-4978-901b-7c001562cf6f",

    "firstName": null,

    "lastName": null,

    "position": null,

    "department": null,

    "directReports": null

}
]},"numberOfReports": 4
}
```

**Task 2:**

1. **Create Compensation Entity:** Create the `Compensation` class with the required fields: `employeeId`, `salary`, and `effectiveDate`.
2. **Create Compensation Repository:** Create the `CompensationRepository` interface that extends `MongoRepository<Compensation, String>`.
3. **Create Compensation Service:** Create the `CompensationService` interface with methods for creating and reading compensations.
4. **Implement Compensation Service:** Create the `CompensationServiceImpl` class that implements the `CompensationService` interface. Implement the methods to create and read compensations using the repository.
5. **Create Compensation Controller:** Create the `CompensationController` class with `@RestController` annotation. Inject the `CompensationService` and create two endpoints:
   * `POST /compensation` for creating compensations.
   * `GET /compensation/{employeeId}` for reading compensations by employeeId.
6. **Test the Endpoints Manually:**
   * Use tools like Postman or curl to test the endpoints.
   * Create compensations using POST request to `/compensation` with JSON body.
   * Read compensations using GET request to `/compensation/{employeeId}`.

**Also added Unit Test cases :**

* Create the `EmployeeServiceImplTest` class for testing the `EmployeeService` methods.
* Create the `CompensationServiceImplTest` class for testing the `CompensationService` methods.
* Use JUnit and SpringBootTest annotations.

## What's Provided

A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped
with data. The application contains information about all employees at a company. On application start-up, an in-memory
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run

The application may be executed by running `gradlew bootRun`.

### How to Use

The following endpoints are available to use:

```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```

The Employee has a JSON schema of:

```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```

For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement

Clone or download the repository, do not fork it.

### Task 1

Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example,
given the following employee structure:

```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```

The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4.

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will
not be persisted.

### Task 2

Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the
Compensation from the persistence layer.

## Delivery

Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
