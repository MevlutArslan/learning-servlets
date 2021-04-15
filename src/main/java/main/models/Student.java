package main.models;

import java.util.List;
import java.util.UUID;

public class Student {

    private String name;
    private String surname;
    private final UUID id;
    private List<Course> courseList;


    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = generateStudentId();
    }

    private UUID generateStudentId() {
        return UUID.randomUUID();
    }

    public UUID getStudentId() {
        return id;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    private void addCourse(Course course){
        this.courseList.add(course);
    }

    private void removeCourse(Course course){
        this.courseList.remove(course);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
