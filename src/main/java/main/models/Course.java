package main.models;

import java.util.List;
import java.util.UUID;

public class Course {
    private String id;
    private String name;
    private Instructor instructor;
    private List<Student> students;

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
        sb.append(name.charAt(0));

        if(name.contains(" ")){
            for(int i = 0; i < name.length(); i++){
                if(name.charAt(i) == ' '){
                    sb.append(name.charAt(i+1));
                }
            }
        }

        sb.append(UUID.randomUUID());

        return sb.toString();
    }

    public void enrollStudent(Student student){
        this.students.add(student);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getNumberOfStudents(){
        return students.size();
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
}
