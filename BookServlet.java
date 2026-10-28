package servlet;

import dao.BookDAO;
import model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("book", books);
            request.getRequestDispatcher("book.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                String title = request.getParameter("title");
                String isbn = request.getParameter("isbn");
                String author = request.getParameter("author");

                Book book = new Book(0, title, isbn, author, true);
                bookDAO.addBook(book);

            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                bookDAO.removeBook(id);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("book.jsp");
    }
}
