# REST API Testing & Automation – Postman + REST Assured

## Project Overview

This project demonstrates manual and automated REST API testing using **Postman, Java, REST Assured, TestNG, and Maven**.

The project uses the public [JSONPlaceholder API](https://jsonplaceholder.typicode.com/) to test common HTTP methods, validate response status codes, verify response data, and execute automated API test cases.

## Tools & Technologies

- Java (JDK 17+)
- REST Assured
- TestNG
- Maven
- Postman
- JSONPlaceholder REST API
- IntelliJ IDEA

## API Test Scenarios

The following API operations are covered:

| HTTP Method | Endpoint | Expected Status | Purpose |
|---|---|---:|---|
| GET | `/posts` | 200 | Retrieve all posts |
| GET | `/posts/1` | 200 | Retrieve a post by ID |
| GET | `/posts/999999` | 404 | Validate an unknown post |
| POST | `/posts` | 201 | Create a sample post |
| PUT | `/posts/1` | 200 | Update an existing post |
| DELETE | `/posts/1` | 200 | Delete a sample post |

## Testing Features

- HTTP status code validation
- Response body and JSON field validation
- Response structure validation
- Positive and negative API testing
- Data-driven testing using TestNG DataProvider
- Automated test execution using Maven
- Postman collection with automated assertions

## Project Structure

```text
REST_API_Testing_Postman_REST_Assured/
│
├── src/
│   └── test/
│       ├── java/
│       │   └── com/prince/qa/
│       │       ├── base/
│       │       │   └── BaseApiTest.java
│       │       └── tests/
│       │           └── PostsApiTests.java
│       └── resources/
│           └── testng.xml
│
├── postman/
│   ├── JSONPlaceholder_API_Collection.postman_collection.json
│   └── JSONPlaceholder_Environment.postman_environment.json
│
├── pom.xml
└── README.md
```

## How to Run the REST Assured Tests

### Prerequisites

- Install JDK 17 or later.
- Install IntelliJ IDEA.
- Install Maven.
- Ensure you have an active internet connection and working DNS access to `jsonplaceholder.typicode.com`.

### Setup Instructions

1. Download or clone the project.
2. Extract the project ZIP file.
3. Open the extracted project folder in IntelliJ IDEA.
4. Configure Project SDK to JDK 17 or later.
5. Allow Maven to download the required dependencies.
6. Open the IntelliJ Terminal.
7. Execute:

```bash
mvn clean test
```

When the API hostname cannot be resolved, the TestNG suite skips the API tests with a clear message. A Maven `BUILD SUCCESS` with skipped tests does not mean the API assertions ran; check the `Tests run`, `Failures`, `Errors`, and `Skipped` counts in the output.

## How to Run the Postman Collection

1. Open Postman.
2. Import the JSON collection from the `postman` folder.
3. Import the corresponding environment JSON file.
4. Select the `JSONPlaceholder - Local` environment.
5. Run individual requests or open Collection Runner.
6. Execute the complete collection and review the test results.

## Actual Test Execution Results

### REST Assured – Maven

Latest verified run in this workspace:

| Metric | Result |
|---|---:|
| Tests run | 9 |
| Failures | 0 |
| Errors | 0 |
| Skipped | 9 |
| Build status | SUCCESS |

All API tests were skipped because `jsonplaceholder.typicode.com` could not be resolved in the current environment. These results do not demonstrate successful API assertion execution. Rerun the suite with working internet and DNS access to obtain executed test results.

### Postman – Collection Runner

No Postman Collection Runner results have been verified in this workspace. Record the request, assertion, and timing metrics here after running the collection.

## Important Note

JSONPlaceholder is a public fake REST API designed for testing and practice.

POST, PUT, and DELETE operations return simulated responses. Changes are not permanently saved on the server.

This is a practice project and does not represent testing performed for a production client or application.
