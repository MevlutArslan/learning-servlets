package main.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.models.Student;
import main.repositories.StudentRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/api/students/")
public class StudentServlet extends HttpServlet {

    StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Type listType = new TypeToken<List<Student>>(){}.getType()
        Gson gson = new Gson();

        String students = gson.toJson(studentRepository.getAll());

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        printWriter.write(students);

        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
    
}
