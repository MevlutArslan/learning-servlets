package main.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.models.Course;
import main.repositories.CourseRepository;
import main.utils.DatabaseHolder;
import main.utils.NotFoundException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CourseServlet", urlPatterns = "/api/courses/*")
public class CourseServlet extends HttpServlet {

    CourseRepository courseRepository = DatabaseHolder.courseRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        Gson gson = new Gson();

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Course course;
        if( pathInfo.length() > 1){
            try {
                course = courseRepository.findByName(pathInfo.substring(1));
                writer.write(gson.toJson(course));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        String courses = gson.toJson(courseRepository.getAll());

        writer.write(courses);

        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }

        // Handles the json body sent within the request
        JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();

        courseRepository.add(new Course(
                jsonObject.get("name").getAsString()
        ));
    }
}
