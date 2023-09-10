package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Component  // This annotation marks the class as a Spring Bean
@Scope("singleton")  // This ensures only one instance of this class is created in the Spring context
public class CourseRepository {

    // Maintaining a list of courses in-memory
    private List<Course> courseList = new ArrayList<>();

    // Add a new course to the list
    public void insertCourse(Course course) {
        courseList.add(course);
    }

    // Search for a course by its ID and return an Optional of it
    public Optional<Course> findCourseById(int id) {
        return courseList.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    // Search for courses based on given parameters and return the matching list
    public List<Course> searchCourses(String name, String professor, Integer grade, String test) {
        return courseList.stream()
                .filter(course -> (name == null || course.getName().equals(name)) &&
                        (professor == null || course.getProfessor().equals(professor)) &&
                        (grade == null || course.getGrade().equals(grade)) &&
                        (test == null || matchTestRequirement(course, test)))
                .collect(Collectors.toList());
    }

    // Helper method to determine if a course matches the given test requirement
    private boolean matchTestRequirement(Course course, String test) {
        boolean isTestRequired = test.toUpperCase().equals("TRUE") || test.toUpperCase().equals("TAK");
        return course.getIsTest() == isTestRequired;
    }

    // Clear all the courses from the list
    public void removeAllCourses() {
        courseList.clear();
    }

    // Remove a course by its ID, returns true if successfully removed
    public boolean removeCourseById(Integer id) {
        return courseList.removeIf(course -> course.getId().equals(id));
    }
}
