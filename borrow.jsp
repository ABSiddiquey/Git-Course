<!-- webapp/borrow.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Borrow / Return</title>
</head>

<body>

    <h2>Borrow / Return</h2>

    <form action="borrow" method="post">

        Member ID:
        <input type="number" name="memberId" required />
        <br/><br/>

        Book ID:
        <input type="number" name="bookId" required />
        <br/><br/>

        <button type="submit" name="action" value="borrow">
            Borrow
        </button>

        <button type="submit" name="action" value="return">
            Return
        </button>

        <button type="submit" name="action" value="undo">
            Undo
        </button>

        <button type="submit" name="action" value="redo">
            Redo
        </button>

    </form>

</body>
</html>