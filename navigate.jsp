<!-- webapp/navigation.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Book" %>
<html>
<head>
    <title>Navigation</title>
</head>
<body>
<h2>Navigate Catalog</h2>

<%
    Book book = (Book) request.getAttribute("book");
    if (book != null) {
%>
    <p>
        ID: <%= book.getId() %><br/>
        Title: <%= book.getTitle() %><br/>
        ISBN: <%= book.getIsbn() %><br/>
        Author: <%= book.getAuthor() %><br/>
        Available: <%= book.isAvailable() ? "Yes" : "No" %><br/>
    </p>
<%
    } else {
%>
    <p>No books available.</p>
<%
    }
%>

<form action="navigate" method="get">
    <button type="submit" name="action" value="prev">Previous</button>
    <button type="submit" name="action" value="next">Next</button>
</form>

</body>
</html>
