package main.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.models.Student;
import main.repositories.StudentRepository;
import main.utils.NotFoundException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentServlet", urlPatterns = {"/api/students/*"})
public class StudentServlet extends HttpServlet {

    StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();

        Student student;

        if (pathInfo.length() > 1){
            try {
                student = studentRepository.findByName(pathInfo.substring(1));
                printWriter.write(gson.toJson(student));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        String students = gson.toJson(studentRepository.getAll());

        printWriter.write(students);

        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // A post request to this endpoint should add a new student to the database.
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }

        // Handles the json body sent within the request
        JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();

        studentRepository.add(new Student(
                jsonObject.get("name").getAsString(),
                jsonObject.get("surname").getAsString()
            )
        );

    }

}
