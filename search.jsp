<!-- webapp/search.jsp -->
<%@ page import="model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Books</title>
</head>

<body>

    <h2>Search Books</h2>

    <form action="search" method="get">
        Title:
        <input type="text" name="title" required />

        <button type="submit">Search</button>
    </form>

    <%
        Book result = (Book) request.getAttribute("result");

        if (result != null) {
    %>

        <h3>Result:</h3>

        ID: <%= result.getId() %><br/>
        Title: <%= result.getTitle() %><br/>
        ISBN: <%= result.getIsbn() %><br/>
        Author: <%= result.getAuthor() %><br/>
        Available: <%= result.isAvailable() ? "Yes" : "No" %><br/>

    <%
        } else if (request.getParameter("title") != null) {
    %>

        <p>No book found with that title.</p>

    <%
        }
    %>

</body>
</html>