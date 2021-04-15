package main.repositories;

import main.models.Student;
import main.utils.NotFoundException;

import java.util.List;

public class StudentRepository implements BaseRepository<Student> {

    List<Student> studentList;

    @Override
    public Student findByName(String name) throws NotFoundException {
        for(Student student : studentList){
            if(student.getName().equals(name))
                return student;
        }

        throw new NotFoundException();
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    @Override
    public void remove(Student student) {
        studentList.remove(student);
    }

    @Override
    public void update(Student updatedInstance) {
        for(Student student : studentList){
            if(student.equals(updatedInstance)){
                student = updatedInstance;
            }
        }
    }

    @Override
    public List<Student> getAll() {
        return studentList;
    }
}
