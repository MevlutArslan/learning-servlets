package main.utils;

import main.repositories.CourseRepository;
import main.repositories.InstructorRepository;
import main.repositories.StudentRepository;

public  class DatabaseHolder {

    public static StudentRepository studentRepository = new StudentRepository();
    public static CourseRepository courseRepository = new CourseRepository();
    public static InstructorRepository instructorRepository = new InstructorRepository();
}
