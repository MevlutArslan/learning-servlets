package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Student {
    private final UUID id;

    private String name;
    private String surname;
    private final List<String> courseList;


    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = generateStudentId();
        courseList = new ArrayList<>();
    }

    private UUID generateStudentId() {
        return UUID.randomUUID();
    }

    public UUID getStudentId() {
        return id;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void addCourse(String courseId){
        this.courseList.add(courseId);
    }

    public void removeCourse(String courseId){
        this.courseList.remove(courseId);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
