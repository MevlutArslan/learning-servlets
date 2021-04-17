package main.repositories;

import main.models.Instructor;
import main.models.Student;
import main.utils.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class InstructorRepository implements BaseRepository<Instructor> {

    private final List<Instructor> instructorList = new ArrayList<>();

    @Override
    public Instructor findByName(String name) throws NotFoundException {
        for(Instructor instructor : instructorList){
            if(instructor.getName().equals(name))
                return instructor;
        }

        throw new NotFoundException();
    }

    @Override
    public void add(Instructor instructor) {
        instructorList.add(instructor);
    }

    @Override
    public void remove(Instructor instructor) {
        instructorList.remove(instructor);
    }

    @Override
    public void update(Instructor updatedInstance) {
        for(Instructor instructor : instructorList){
            if(instructor.equals(updatedInstance)){
                instructor = updatedInstance;
            }
        }
    }

    @Override
    public List<Instructor> getAll() {
        return instructorList;
    }
}
