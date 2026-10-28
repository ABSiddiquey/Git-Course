<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>

<html>
<head>
    <title>Library Inventory</title>
</head>
<body>

<h2>Library Inventory</h2>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>ISBN</th>
        <th>Author</th>
        <th>Available</th>
        <th>Action</th>
    </tr>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null) {
            for (Book b : books) {
    %>

    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getTitle() %></td>
        <td><%= b.getIsbn() %></td>
        <td><%= b.getAuthor() %></td>
        <td><%= b.isAvailable() ? "Yes" : "No" %></td>

        <td>
            <form action="book" method="post" style="display:inline;">
                <input type="hidden" name="action" value="remove"/>
                <input type="hidden" name="id" value="<%= b.getId() %>"/>
                <button type="submit">Remove</button>
            </form>
        </td>
    </tr>

    <%
            }
        }
    %>

</table>

<br><br>

<h3>Add New Book</h3>

<form action="book" method="post">
    <input type="hidden" name="action" value="add"/>

    <label>Title:</label>
    <input type="text" name="title" required/><br/><br/>

    <label>ISBN:</label>
    <input type="text" name="isbn" required/><br/><br/>

    <label>Author:</label>
    <input type="text" name="author" required/><br/><br/>

    <button type="submit">Add Book</button>
</form>

</body>
</html>
