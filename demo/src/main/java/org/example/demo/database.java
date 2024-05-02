package org.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet (name = "database", value = "/database")
public class database extends HttpServlet {
    Connection connection;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3306/bloknot";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Notebooks (manufacturer_name, notebook_name, page_count, cover_type, manufacturer_country, page_layout)\n" +
                    "VALUES \n" +
                    "('ABC Corporation', 'ABC Notebook 1', 100, 'hard', 'USA', 'grid'),\n" +
                    "('XYZ Company', 'XYZ Notebook 1', 150, 'soft', 'China', 'lined'),\n" +
                    "('ABC Corporation', 'ABC Notebook 2', 80, 'hard', 'USA', 'blank'),\n" +
                    "('DEF Ltd.', 'DEF Notebook 1', 120, 'soft', 'Germany', 'lined'),\n" +
                    "('GHI Inc.', 'GHI Notebook 1', 200, 'hard', 'Japan', 'grid')";
            statement.executeUpdate(query);
            out.println("Table Notebook created");
        }
        catch (ClassNotFoundException | SQLException e) {

            out.println("<html><body>");
            out.println("<h2>Помилка: " + e.getMessage() + "</h2>");
            out.println("</body></html>");
            e.printStackTrace();
        }
    }
}
