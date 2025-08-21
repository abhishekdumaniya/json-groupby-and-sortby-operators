# JSON Dataset API

A Spring Boot REST API to manage JSON dataset records with dynamic insert, group-by, and sort-by operations using PostgreSQL.  
This project demonstrates how to store JSON records in PostgreSQL (using JSONB) and query them with flexible operators.

## Features & Tech Stack
- Insert JSON records into datasets
- Query datasets dynamically with Group By or Sort By (asc/desc)
- Data stored in PostgreSQL using JSONB column
- Java 17+, Spring Boot 3+, Spring Data JPA, Hibernate Types, PostgreSQL, Maven

## API Endpoints

### Insert Record  
**POST** `http://localhost:8080/api/dataset/{datasetName}/record`  
Request Body:
```json
{
  "id": 1,
  "name": "John Doe",
  "age": 30,
  "department": "Engineering"
}
```
### Response
```json
{
  "message": "Record added successfully",
  "dataset": "employee_dataset",
  "recordId": 1
}
```
### Query with Group-By
**GET** `http://localhost:8080/api/dataset/{datasetName}/query?groupBy=department`

### Response
```json
{
  "groupedRecords": {
    "Engineering": [
      { "id": 1, "name": "John Doe", "age": 30, "department": "Engineering" },
      { "id": 2, "name": "Jane Smith", "age": 25, "department": "Engineering" }
    ],
    "Marketing": [
      { "id": 3, "name": "Alice Brown", "age": 28, "department": "Marketing" }
    ]
  }
}
```
### Query with Sort-By
**GET** `http://localhost:8080/api/dataset/{datasetName}/query?sortBy=age&order=asc`
```json
{
  "sortedRecords": [
    { "id": 2, "name": "Jane Smith", "age": 25, "department": "Engineering" },
    { "id": 3, "name": "Alice Brown", "age": 28, "department": "Marketing" },
    { "id": 1, "name": "John Doe", "age": 30, "department": "Engineering" }
  ]
}
```

