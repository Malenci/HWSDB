package org.example.demo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet (name = "Work2", value = "/Work2")
public class Work2Servlet extends HttpServlet {

    Connection connection;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String url = "jdbc:mysql://localhost:3306/bloknot";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            displayNotebooks(out); //

            displayManufacturerCountries(out);

            displayNotebooksByCountry(out);

            displayNotebooksByManufacturer(out);

        } catch (SQLException e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    out.println("<h3>Connection close</h3>");
                    connection.close();
                } catch (SQLException e) {
                    out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    private void displayNotebooks(PrintWriter out) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Notebooks");
        out.println("<h2>Notebooks:</h2>");
        while (rs.next()) {
            out.println("ID: " + rs.getInt("id") +
                    ", Manufacturer: " + rs.getString("manufacturer_name") +
                    ", Notebook: " + rs.getString("notebook_name") +
                    ", Page count: " + rs.getInt("page_count") +
                    ", Cover type: " + rs.getString("cover_type") +
                    ", Manufacturer country: " + rs.getString("manufacturer_country") +
                    ", Page layout: " + rs.getString("page_layout") + "<br>");
        }
        rs.close();
        stmt.close();
    }

    private void displayManufacturerCountries(PrintWriter out) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT manufacturer_country FROM Notebooks");
        out.println("<h2>Manufacturer countries:</h2>");
        while (rs.next()) {
            out.println(rs.getString("manufacturer_country") + "<br>");
        }
        rs.close();
        stmt.close();
    }

    private void displayNotebooksByCountry(PrintWriter out) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT manufacturer_country, COUNT(*) AS notebook_count FROM Notebooks GROUP BY manufacturer_country");
        out.println("<h2>Notebooks by country:</h2>");
        while (rs.next()) {
            out.println("Country: " + rs.getString("manufacturer_country") +
                    ", Notebook count: " + rs.getInt("notebook_count") + "<br>");
        }
        rs.close();
        stmt.close();
    }

    private void displayNotebooksByManufacturer(PrintWriter out) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT manufacturer_name, COUNT(*) AS notebook_count FROM Notebooks GROUP BY manufacturer_name");
        out.println("<h2>Notebooks by manufacturer:</h2>");
        while (rs.next()) {
            out.println("Manufacturer: " + rs.getString("manufacturer_name") +
                    ", Notebook count: " + rs.getInt("notebook_count") + "<br>");
        }
        rs.close();
        stmt.close();
    }
}
