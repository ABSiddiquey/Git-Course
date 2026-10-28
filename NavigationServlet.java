package servlet;

import dao.BookDAO;
import dsaa.DoublyLinkedListDemo;
import model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/navigate")
public class NavigationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO = new BookDAO();
    private DoublyLinkedListDemo<Book> dll = new DoublyLinkedListDemo<>();
    private DoublyLinkedListDemo.Node<Book> cursor;

    @Override
    public void init() throws ServletException {
        try {
            for (Book b : bookDAO.getAllBooks()) {
                dll.addLast(b);
            }
            cursor = dll.getHead();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("next".equals(action) && cursor != null && cursor.next != null) {
            cursor = cursor.next;
        } else if ("prev".equals(action) && cursor != null && cursor.prev != null) {
            cursor = cursor.prev;
        }

        request.setAttribute("book", cursor != null ? cursor.data : null);
        request.getRequestDispatcher("navigate.jsp").forward(request, response);
    }
}
