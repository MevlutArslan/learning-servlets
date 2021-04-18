package main.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.models.Course;
import main.models.Instructor;
import main.repositories.InstructorRepository;
import main.utils.DatabaseHolder;
import main.utils.NotFoundException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InstructorServlet", value = "/api/instructors/")
public class InstructorServlet extends HttpServlet {

    InstructorRepository instructorRepository = DatabaseHolder.instructorRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        Gson gson = new Gson();

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Instructor instructor;
        if( pathInfo.length() > 1){
            try {
                instructor = instructorRepository.findByName(pathInfo.substring(1));
                writer.write(gson.toJson(instructor));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        String instructors = gson.toJson(instructorRepository.getAll());

        writer.write(instructors);

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

        instructorRepository.add(
                new Instructor(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("surname").getAsString(),
                        jsonObject.get("title").getAsString()
                )
        );

    }
}
