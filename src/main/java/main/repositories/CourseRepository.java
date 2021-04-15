package main.repositories;

import main.models.Course;
import main.utils.NotFoundException;

import java.util.List;

public class CourseRepository implements BaseRepository<Course> {

    private List<Course> courseList;

    @Override
    public Course findByName(String name) throws NotFoundException {
            for(Course course : courseList){
                if(course.getName().equals(name))
                    return course;
            }

            throw new NotFoundException();
        }

    @Override
    public void add(Course course) {
        courseList.add(course);
    }

    @Override
    public void remove(Course course) {
        courseList.remove(course);
    }

    @Override
    public void update(Course updatedCourse) {
        for(Course course : courseList){
            if(course.equals(updatedCourse)){
                course = updatedCourse;
            }
        }
    }

    @Override
    public List<Course> getAll() {
        return courseList;
    }

}
