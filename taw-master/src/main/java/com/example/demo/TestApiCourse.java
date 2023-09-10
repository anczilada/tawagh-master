package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class TestApiCourse {

    @Autowired
    private CourseRepository courseRepository;

    // Licznik do generowania unikalnych identyfikatorów dla dodawanych kursów
    private Integer courseIdCounter = 1;

    // Endpoint do sprawdzania stanu API
    @GetMapping("/ping")
    public String healthCheck() {
        return "Serwer działa!";
    }

    // Endpoint do dodawania nowego kursu
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCourse(@RequestBody Course course) {
        course.setId(courseIdCounter++);
        courseRepository.insertCourse(course);
    }

    // Endpoint do pobierania listy kursów na podstawie podanych filtrów
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> listCourses(
            @RequestParam(value = "nazwa", required = false) String name,
            @RequestParam(value = "prowadzący", required = false) String professor,
            @RequestParam(value = "ocena", required = false) Integer grade,
            @RequestParam(value = "egzamin", required = false) String test
    ) {
        return ResponseEntity.ok(courseRepository.searchCourses(name, professor, grade, test));
    }

    // Endpoint do usuwania wszystkich kursów
    @DeleteMapping
    public void clearCourses() {
        courseRepository.removeAllCourses();
    }

    // Endpoint do pobierania konkretnego kursu według jego identyfikatora
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return courseRepository.findCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint do usuwania konkretnego kursu według jego identyfikatora
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCourse(@PathVariable Integer id) {
        if (courseRepository.removeCourseById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
