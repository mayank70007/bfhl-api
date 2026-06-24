# BFHL API Challenge

Spring Boot 3 + Java 21 REST API for processing alphanumeric inputs.

## Setup

### Maven (Local)
Build:
```bash
mvn clean package
```

Run:
```bash
mvn spring-boot:run
```

Test:
```bash
mvn test
```

### Docker (Local)
Build image:
```bash
docker build -t bfhl-api .
```

Run container:
```bash
docker run -p 8080:8080 bfhl-api
```

## API Specifications

### GET /bfhl
Returns operational status.
Response:
```json
{
  "operation_code": 1
}
```

### GET /health
Returns application health.
Response:
```json
{
  "status": "UP"
}
```

### POST /bfhl
Processes and partitions inputs.
Request:
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```
Response:
```json
{
  "is_success": true,
  "user_id": "mayank_24062003",
  "email": "your_email@example.com",
  "roll_number": "2310990000",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra",
  "sepcial_characters": ["$"]
}
```

## Deployment on Render
Render will automatically detect the `Dockerfile` and deploy using it.
1. Create a new **Web Service** on Render.
2. Link the repository.
3. Select **Runtime**: `Docker`.
4. Click **Deploy**.
