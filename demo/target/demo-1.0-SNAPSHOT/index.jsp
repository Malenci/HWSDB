<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database</title>
</head>
<body>
<h1>Database</h1>
<form action="database" method="post">
   <label>Create database</label>
    <input type="submit" value="Submit">
</form>

<form action="Work2" method="get">
    <label>Look on database</label>
    <input type="submit" value="Submit">
</form>
<h2>Add New Notebook</h2>
<form action="AddEditDelete" method="get">
    Manufacturer Name: <input type="text" name="manufacturer_name"><br>
    Notebook Name: <input type="text" name="notebook_name"><br>
    Page Count: <input type="number" name="page_count"><br>
    Cover Type: <input type="text" name="cover_type"><br>
    Manufacturer Country: <input type="text" name="manufacturer_country"><br>
    Page Layout: <select name="page_layout">
    <option value="grid">Grid</option>
    <option value="lined">Lined</option>
    <option value="blank">Blank</option>
</select><br>
    <input type="hidden" name="action" value="add">
    <input type="submit" value="Add Notebook">
</form>

<h2>Delete Notebook</h2>
<form action="AddEditDelete" method="get">
    Notebook ID: <input type="number" name="id"><br>
    <input type="hidden" name="action" value="delete">
    <input type="submit" value="Delete Notebook">
</form>

<h2>Update Notebook</h2>
<form action="AddEditDelete" method="get">
    Notebook ID: <input type="number" name="id"><br>
    Manufacturer Name: <input type="text" name="manufacturer_name"><br>
    Notebook Name: <input type="text" name="notebook_name"><br>
    Page Count: <input type="number" name="page_count"><br>
    Cover Type: <input type="text" name="cover_type"><br>
    Manufacturer Country: <input type="text" name="manufacturer_country"><br>
    Page Layout: <select name="page_layout">
    <option value="grid">Grid</option>
    <option value="lined">Lined</option>
    <option value="blank">Blank</option>
</select><br>
    <input type="hidden" name="action" value="update">
    <input type="submit" value="Update Notebook">
</form>
</body>
</html>