package main.models;

import java.util.*;

public class Course {
    private String id;
    private String name;
    private Instructor instructor;
    private final List<UUID> enrolledStudents;


    public Course(String name) {
        this.name = name;
        this.id = generateId();
        enrolledStudents = new ArrayList<>();
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

    public void enrollStudent(UUID studentId){
        this.enrolledStudents.add(studentId);
    }

    public void removeStudent(Student student){
        this.enrolledStudents.remove(student);
    }

    public List<UUID> getStudents() {
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
