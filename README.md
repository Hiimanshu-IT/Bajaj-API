# Bajaj Finserv Health (BFHL) - Campus Hiring REST API

[![Java Version](https://img.shields.io/badge/Java-17-orange.svg?style=flat-square&logo=openjdk)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen.svg?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Compatible-blue.svg?style=flat-square&logo=docker)](https://www.docker.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

A robust, enterprise-grade Spring Boot REST API developed for the **Bajaj Finserv Health (BFHL) Campus Hiring Challenge**. 

This application exposes endpoints to ingest, validate, classify, and transform a list of mixed-character inputs (numbers, letters, and special symbols). It performs calculations and implements a custom string modification algorithm on the inputs while remaining fault-tolerant.

---

## 🚀 Key Features

*   **Mixed Data Classification**: Automatically filters and classifies incoming strings into:
    *   **Odd Numbers**
    *   **Even Numbers**
    *   **Alphabets** (Converted to uppercase)
    *   **Special Characters**
*   **Numerical Aggregation**: Dynamically sums all integers present in the input array.
*   **Custom String Transformation (`buildConcatString`)**:
    1. Flattens all alphabets into a single string.
    2. Reverses the resulting string.
    3. Transforms the casing using an alternating capitalization scheme (e.g., upper case at index 0, lower case at index 1, and so on).
*   **Robust Exception Handling**: Global exception handler (`@RestControllerAdvice`) maps system errors and validation issues into a consistent, schema-compliant JSON format.
*   **Production Ready**: Out-of-the-box support for multi-stage Docker builds.

---

## 🛠️ Technology Stack

*   **Runtime Environment**: Java 17
*   **Framework**: Spring Boot 3.2.5 (Spring MVC & Jakarta Validation)
*   **Build Tool**: Apache Maven
*   **Containerization**: Docker (multi-stage Alpine JRE build)

---

## 🗂️ API Endpoints

### 1. Process Data (`POST /bfhl`)

Processes a list of input strings, categorizes them, and performs arithmetic and string manipulation logic.

*   **URL**: `/bfhl`
*   **Method**: `POST`
*   **Headers**: `Content-Type: application/json`

#### Request Body
```json
{
  "data": ["A", "1", "334", "R", "$", "o", "7", "!"]
}
```

#### Successful Response (`200 OK`)
```json
{
  "is_success": true,
  "user_id": "himanshu_thakur_13032005",
  "email": "himanshuthakur230609@acropolis.in",
  "roll_number": "0827IT231055",
  "odd_numbers": ["1", "7"],
  "even_numbers": ["334"],
  "alphabets": ["A", "R", "O"],
  "special_characters": ["$", "!"],
  "sum": "342",
  "concat_string": "OrA"
}
```
> **How `concat_string` was computed for `["A", "R", "O"]`:**
> 1. Flatten all alphabets: `ARO` (since they are converted to uppercase).
> 2. Reverse: `ORA`.
> 3. Alternating capitalization: `O` (index 0, Upper), `r` (index 1, Lower), `A` (index 2, Upper) ➜ `OrA`.

#### Validation / Error Response (`400 Bad Request` or `500 Internal Server Error`)
If the `"data"` field is omitted or null, the API returns a structured failure response:
```json
{
  "is_success": false,
  "user_id": "",
  "email": "",
  "roll_number": "",
  "odd_numbers": [],
  "even_numbers": [],
  "alphabets": [],
  "special_characters": [],
  "sum": "0",
  "concat_string": ""
}
```

---

### 2. Get Operation Code (`GET /bfhl`)

Returns a hardcoded operation code required by evaluators.

*   **URL**: `/bfhl`
*   **Method**: `GET`
*   **Response (`200 OK`)**:
    ```json
    {
      "operation_code": 1
    }
    ```

---

### 3. Health Check (`GET /health`)

A standard endpoint to verify the service status during CI/CD deployments and container probes.

*   **URL**: `/health`
*   **Method**: `GET`
*   **Response (`200 OK`)**:
    ```json
    {
      "status": "UP"
    }
    ```

---

## 🏃 Local Setup & Running Guide

### Prerequisites
*   Java Development Kit (JDK) 17 or higher
*   Maven 3.8+ installed locally (or run via the included wrapper if present)

### Option 1: Run via Maven
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Hiimanshu-IT/Bajaj-API.git
   cd Bajaj-API
   ```
2. **Build and Run the Application**:
   ```bash
   mvn clean spring-boot:run
   ```
3. **Verify Execution**:
   Open a terminal and ping the health endpoint:
   ```bash
   curl http://localhost:8080/health
   ```

### Option 2: Run via Docker (Recommended)
This repository contains a multi-stage Docker build optimized for minimal image sizes.
1. **Build the Image**:
   ```bash
   docker build -t bajaj-bfhl-api .
   ```
2. **Run the Container**:
   ```bash
   docker run -p 8080:8080 bajaj-bfhl-api
   ```
3. **Access API**: The API will be available at `http://localhost:8080`.

---

## 🏗️ Project Architecture

The application is structured following clean, decoupled MVC patterns:

```
src/main/java/com/bajaj/bfhl
├── BfhlApplication.java           # Main Spring Boot Entrypoint
├── controller
│   └── BfhlController.java        # REST Controller exposing POST and GET endpoints
├── dto
│   ├── BfhlRequest.java           # Incoming API Request Schema (Validator constraints)
│   └── BfhlResponse.java          # API Response Schema (Builder and Jackson mappings)
├── exception
│   └── GlobalExceptionHandler.java # REST Controller Advice for robust error handling
└── service
    ├── BfhlService.java           # Service contract defining processing routines
    └── BfhlServiceImpl.java       # Core business and classification logic
```

---

## 👤 Developer Details

*   **Name**: Himanshu Thakur
*   **Email**: [himanshuthakur230609@acropolis.in](mailto:himanshuthakur230609@acropolis.in)
*   **Roll Number**: 0827IT231055
*   **Organization**: Bajaj Finserv Health (Campus Hiring Challenge)
