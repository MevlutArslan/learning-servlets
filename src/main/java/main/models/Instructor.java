package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Instructor {
    private final UUID id;

    private String name;
    private String surname;
    private String title;

    private final List<String> coursesTaught;

    public Instructor(String name, String surname, String title){
        this.name = name;
        this.surname = surname;
        this.title = title;
        coursesTaught = new ArrayList<>();

        this.id = generateId();
    }

    private UUID generateId(){
        return UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }

    public void assignCourse(String courseId){
        this.coursesTaught.add(courseId);
    }

    public void removeCourse(String courseId){
        this.coursesTaught.remove(courseId);
    }

    public List<String> getCoursesTaught() {
        return coursesTaught;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
