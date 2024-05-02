package org.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet (name = "AddEditDelete", value="/AddEditDelete")
public class Work5Servlet extends HttpServlet {
    Connection connection;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
            String url = "jdbc:mysql://localhost:3306/bloknot";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "add":
                        addNotebook(request, response, out);
                        break;
                    case "delete":
                        deleteNotebook(request, response, out);
                        break;
                    case "update":
                        updateNotebook(request, response, out);
                        break;
                    default:
                        out.println("Invalid action");
                        break;
                }
            }
            displayNotebooks(out);
        }
        catch (SQLException | ClassNotFoundException e){

        }
    }
    private void addNotebook(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws SQLException {
        String manufacturerName = request.getParameter("manufacturer_name");
        String notebookName = request.getParameter("notebook_name");
        int pageCount = Integer.parseInt(request.getParameter("page_count"));
        String coverType = request.getParameter("cover_type");
        String manufacturerCountry = request.getParameter("manufacturer_country");
        String pageLayout = request.getParameter("page_layout");

        String sql = "INSERT INTO Notebooks (manufacturer_name, notebook_name, page_count, cover_type, manufacturer_country, page_layout) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, manufacturerName);
        statement.setString(2, notebookName);
        statement.setInt(3, pageCount);
        statement.setString(4, coverType);
        statement.setString(5, manufacturerCountry);
        statement.setString(6, pageLayout);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            out.println("<h3>New notebook added successfully</h3>");
        } else {
            out.println("<h3>Failed to add new notebook</h3>");
        }
    }

    private void deleteNotebook(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM Notebooks WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            out.println("<h3>Notebook deleted</h3>");
        } else {
            out.println("<h3>Notebook found with id: " + id + "</h3>");
        }
    }

    private void updateNotebook(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String manufacturerName = request.getParameter("manufacturer_name");
        String notebookName = request.getParameter("notebook_name");
        int pageCount = Integer.parseInt(request.getParameter("page_count"));
        String coverType = request.getParameter("cover_type");
        String manufacturerCountry = request.getParameter("manufacturer_country");
        String pageLayout = request.getParameter("page_layout");

        String sql = "UPDATE Notebooks SET manufacturer_name=?, notebook_name=?, page_count=?, cover_type=?, manufacturer_country=?, page_layout=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, manufacturerName);
        statement.setString(2, notebookName);
        statement.setInt(3, pageCount);
        statement.setString(4, coverType);
        statement.setString(5, manufacturerCountry);
        statement.setString(6, pageLayout);
        statement.setInt(7, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            out.println("<h3>Notebook updated successfully</h3>");
        } else {
            out.println("<h3>No notebook found with id: " + id + "</h3>");
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
}
