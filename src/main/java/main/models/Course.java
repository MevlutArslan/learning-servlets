package main.models;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Course {
    private String id;
    private String name;
    private Instructor instructor;
    private List<Student> enrolledStudents;

    public Course(){}

    public Course(String name, Instructor instructor) {
        this.name = name;
        this.instructor = instructor;
        this.id = generateId();
    }

    public String getId() {
        return id;
    }

    public String generateId(){
        StringBuilder sb = new StringBuilder();

        String tempName = name.toUpperCase(Locale.ROOT);

        sb.append(tempName.charAt(0));

        if(tempName.contains(" ")){
            for(int i = 0; i < tempName.length(); i++){
                if(tempName.charAt(i) == ' '){
                    sb.append(tempName.charAt(i+1));
                }
            }
        }

        sb.append(UUID.randomUUID());

        return sb.toString();
    }

    public void enrollStudent(Student student){
        this.enrolledStudents.add(student);
    }

    public void removeStudent(Student student){
        this.enrolledStudents.remove(student);
    }

    public List<Student> getStudents() {
        return enrolledStudents;
    }

    public int getNumberOfStudents(){
        return enrolledStudents.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
