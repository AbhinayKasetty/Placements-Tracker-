package com.placementtracker.student;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return findOrThrow(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student) {
        if (repository.existsByRollNumber(student.getRollNumber())) {
            throw duplicate(student.getRollNumber());
        }
        return repository.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student updated) {
        Student existing = findOrThrow(id);

        boolean rollChanged = !existing.getRollNumber().equals(updated.getRollNumber());
        if (rollChanged && repository.existsByRollNumber(updated.getRollNumber())) {
            throw duplicate(updated.getRollNumber());
        }

        existing.setName(updated.getName());
        existing.setRollNumber(updated.getRollNumber());
        existing.setBranch(updated.getBranch());
        existing.setCgpa(updated.getCgpa());
        existing.setSkills(updated.getSkills());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        findOrThrow(id);
        repository.deleteById(id);
    }

    private Student findOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + id));
    }

    private ResponseStatusException duplicate(String rollNumber) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Roll number already exists: " + rollNumber);
    }
}