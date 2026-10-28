package servlet;

import dao.BookDAO;
import dsaa.SortEngine;
import dsaa.SearchEngine;
import model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO = new BookDAO();
    private SortEngine sortEngine = new SortEngine();
    private SearchEngine searchEngine = new SearchEngine();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");

        try {
            List<Book> books = bookDAO.getAllBooks();

            if (!books.isEmpty()) {
                sortEngine.quickSort(books, 0, books.size() - 1);
            }

            Book result = (title != null && !title.isEmpty())
                    ? searchEngine.binarySearch(books, title)
                    : null;

            request.setAttribute("result", result);
            request.getRequestDispatcher("search.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
