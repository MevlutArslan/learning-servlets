package main.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.models.Course;
import main.models.Instructor;
import main.models.Student;
import main.repositories.CourseRepository;
import main.repositories.InstructorRepository;
import main.repositories.StudentRepository;
import main.utils.DatabaseHolder;
import main.utils.NotFoundException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "RegisterServlet", value = "/api/courses/register")
public class RegisterServlet extends HttpServlet {

    CourseRepository courseRepository = DatabaseHolder.courseRepository;
    StudentRepository studentRepository = DatabaseHolder.studentRepository;
    InstructorRepository instructorRepository = DatabaseHolder.instructorRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(courseRepository.getAll());

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }

        // Handles the json body sent within the request
        JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
        // Check body for information like :
        // Registering student or instructor
        // Registering to which course
        // Registering person's id

        String registeringType = jsonObject.get("type").getAsString();
        String registeringTo = jsonObject.get("courseName").getAsString();
        String registeringPerson = jsonObject.get("name").getAsString();
        Course course;

        try {
            course = courseRepository.findByName(registeringTo);
            if( registeringType.toLowerCase(Locale.ROOT).equals("student")){
                Student student = studentRepository.findByName(registeringPerson);
                student.addCourse(course.getId());

                course.enrollStudent(student.getStudentId());
            }
            else if ( registeringType.toLowerCase(Locale.ROOT).equals("instructor")){
                Instructor instructor = instructorRepository.findByName(registeringPerson);

                instructor.assignCourse(course.getId());
                course.setInstructor(instructor);
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        response.getWriter().write("registering to course!");

    }
}
