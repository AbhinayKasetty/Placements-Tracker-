# Placement Tracker

A full-stack web app to manage student profiles for campus placements.

**Tech:** Java, Spring Boot, Spring Data JPA, MySQL, HTML/CSS/JavaScript

**Live demo:** https://placements-tracker.onrender.com
*(Hosted on a free tier, so the first load can take up to a minute.)*

## Features
- Add, view, edit and delete student profiles
- Validation (CGPA range, required fields)
- Duplicate roll number detection with clear error messages

## Run locally
1. Install Java 21+ and MySQL, and start MySQL.
2. Copy `secrets.properties.example` to `secrets.properties` and set your MySQL password.
3. Run `.\mvnw.cmd spring-boot:run` (Windows) or `./mvnw spring-boot:run`.
4. Open http://localhost:8080/

## API
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/students | List all students |
| GET | /api/students/{id} | Get one student |
| POST | /api/students | Add a student |
| PUT | /api/students/{id} | Update a student |
| DELETE | /api/students/{id} | Delete a student |