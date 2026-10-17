# CSC340StreetFighterCRUDAPI

# Street Fighter Character API

A REST API for managing **Street Fighter characters**, built with Spring Boot using a layered architecture. Each character has a name, a description, the game they first appeared in, a fighter type, and a difficulty rating. Data is stored in PostgreSQL (Neon). Deployment is done with Docker on Render.

**Deployed API:** https://csc340streetfightercrudapi-3.onrender.com/api/characters

> **Cold-start note:** Render's free tier puts the service to sleep after about 15 minutes of inactivity, so the first request afterward can take up to a minute.

## Features

- Create, read, update, and delete characters
- Partial, case-insensitive search by name
- Filter by fighter type
- Bean validation with clear HTTP status codes (400, 404)
- Configured entirely through environment variables (no secrets in the repo)

## Tech Stack

| Area | Technology |
|------|------------|
| Language | Java 25 |
| Framework | Spring Boot (Web, Data JPA, Validation) |
| Database | PostgreSQL on [Neon](https://neon.com) |
| Build | Maven |
| Deployment | Docker on Render |

## Architecture

The app uses four layers. Each layer talks only to the one below it.

```
HTTP request
    |
Controller   - maps URLs to methods, validates input, returns HTTP responses
    |
Service      - application logic. throws domain exceptions
    |
Repository   - data access (Spring Data JPA), generates SQL
    |
Entity       - Java class mapped to the `characters` table
    |
PostgreSQL (Neon)
```

### Project Structure

```
src/main/java/com/example/demo/
  DemoApplication.java
  entity/
    StreetFighterCharacter.java
  repository/
    StreetFighterCharacterRepository.java
  service/
    StreetFighterCharacterService.java
  controller/
    StreetFighterCharacterController.java
src/main/resources/
  application.properties
Dockerfile
```

## Data Model

**StreetFighterCharacter** (table `characters`)

| Field | Type | Rules |
|-------|------|-------|
| `characterId` | Long | Unique, generated automatically |
| `name` | String | Required, not blank |
| `description` | String | Required, not blank |
| `firstAppearance` | String | Optional. The game the character first appeared in |
| `typeOfFighter` | String | Optional. Fighting archetype (for example Shoto, Grappler) |
| `difficulty` | String | Optional. Difficulty rating (for example Easy, Medium, Hard) |

## API Reference

Base path: `/api/characters`

| Method | Path | Description | Success |
|--------|------|-------------|---------|
| GET | `/api/characters` | List all characters (supports filters below) | 200 |
| GET | `/api/characters/{id}` | Get one character | 200 |
| POST | `/api/characters` | Create a character | 201 |
| PUT | `/api/characters/{id}` | Replace a character's fields | 200 |
| DELETE | `/api/characters/{id}` | Delete a character | 204 |

### Query parameters for `GET /api/characters`

| Parameter | Example | Behavior |
|-----------|---------|----------|
| `typeOfFighter` | `?typeOfFighter=Shoto` | Filter by fighter type (case-insensitive) |
| `name` | `?name=ry` | Partial, case-insensitive match ("ry" finds "Ryu") |

If both are sent, `typeOfFighter` is used.

### Example request (POST and PUT)

```http
POST /api/characters
Content-Type: application/json

{
  "name": "Ryu",
  "description": "A wandering martial artist seeking true strength.",
  "firstAppearance": "Street Fighter (1987)",
  "typeOfFighter": "Shoto",
  "difficulty": "Easy"
}
```

`PUT /api/characters/{id}` takes the same body.

### Example response

```json
{
  "characterId": 1,
  "name": "Ryu",
  "description": "A wandering martial artist seeking true strength.",
  "firstAppearance": "Street Fighter (1987)",
  "typeOfFighter": "Shoto",
  "difficulty": "Easy"
}
```

### Status codes

| Code | Meaning |
|------|---------|
| 200 | OK |
| 201 | Created |
| 204 | Deleted, no content |
| 400 | Validation failed (missing or blank `name` or `description`) or malformed JSON |
| 404 | Character with that id does not exist |

## Configuration

The app reads its database settings from environment variables. Spring Boot maps these names automatically, so nothing secret is stored in `application.properties`.

| Variable | Example |
|----------|---------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://<neon-host>/<db>?sslmode=require` |
| `PORT` | Set by Render; defaults to `8080` locally |

## Running Locally

1. Create a Neon project and copy the **Java** connection string (it starts with `jdbc:postgresql://`).
2. Set the `SPRING_DATASOURCE_URL` environment variable in the terminal you will run the app from.
    - Mac/Linux: `export SPRING_DATASOURCE_URL="jdbc:postgresql://<neon-host>/<db>?sslmode=require"`
    - Windows cmd: `set SPRING_DATASOURCE_URL=jdbc:postgresql://<neon-host>/<db>?sslmode=require`
    - PowerShell: `$env:SPRING_DATASOURCE_URL="jdbc:postgresql://<neon-host>/<db>?sslmode=require"`
3. Start the app:

```bash
./mvnw spring-boot:run        # Mac / Linux / PowerShell
.\mvnw.cmd spring-boot:run    # Windows cmd
```

4. Visit `http://localhost:8080/api/characters`. A fresh database returns `[]`.

Hibernate creates the `characters` table automatically on first run (`spring.jpa.hibernate.ddl-auto=update`).

## Deployment (Render)

Render has no native Java runtime, so the app is deployed with Docker. The `Dockerfile` at the repository root uses a multi-stage build: a JDK 25 image builds the JAR with the Maven wrapper, and a smaller JRE 25 image runs it with `java -jar`. It contains no secrets and no hardcoded port.

1. Push the repository to GitHub.
2. In Render, create a **Web Service**, connect the repo, choose the **Docker** runtime and the **Free** instance type.
3. Add the `SPRING_DATASOURCE_URL` environment variable (value only, no quotes).
4. Deploy, then open `https://<your-service>.onrender.com/api/characters`.

### Free-tier notes

- Render free services sleep after roughly 15 minutes of inactivity, so the first request afterward can take up to a minute.
- Neon scales idle databases to zero, which adds a short delay on the first query.
- Render's file system is ephemeral. All persistent data lives in Neon.