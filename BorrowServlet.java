package servlet;

import dao.BookDAO;
import dao.MemberDAO;
import dao.ActionsDAO;

import dsaa.StackDemo;
import dsaa.QueueDemo;

import model.Book;
import model.Member;
import model.Actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO = new BookDAO();
    private MemberDAO memberDAO = new MemberDAO();
    private ActionsDAO actionsDAO = new ActionsDAO();

    private StackDemo<Actions> undoStack = new StackDemo<>();
    private StackDemo<Actions> redoStack = new StackDemo<>();
    private QueueDemo<Member> waitlistQueue = new QueueDemo<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("borrow".equals(action)) {
                borrowBook(request);
            } else if ("return".equals(action)) {
                returnBook(request);
            } else if ("undo".equals(action)) {
                undoAction();
            } else if ("redo".equals(action)) {
                redoAction();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("borrow.jsp");
    }

    private void borrowBook(HttpServletRequest request) throws SQLException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        Book book = bookDAO.getBookById(bookId);

        if (!book.isAvailable()) {
            Member m = memberDAO.getMemberById(memberId);
            waitlistQueue.enqueue(m);
            return;
        }

        bookDAO.updateAvailability(bookId, false);

        Actions t = new Actions(memberId, bookId, "BORROW", LocalDateTime.now());
        actionsDAO.addAction(t);

        undoStack.push(t);
        redoStack = new StackDemo<>();
    }

    private void returnBook(HttpServletRequest request) throws SQLException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        bookDAO.updateAvailability(bookId, true);

        if (!waitlistQueue.isEmpty()) {
            Member next = waitlistQueue.dequeue();
            bookDAO.updateAvailability(bookId, false);

            Actions autoBorrow = new Actions(next.getId(), bookId, "BORROW", LocalDateTime.now());
            actionsDAO.addAction(autoBorrow);

            undoStack.push(autoBorrow);
        }

        Actions t = new Actions(memberId, bookId, "RETURN", LocalDateTime.now());
        actionsDAO.addAction(t);

        undoStack.push(t);
        redoStack = new StackDemo<>();
    }

    private void undoAction() throws SQLException {
        if (undoStack.isEmpty()) return;

        Actions last = undoStack.pop();

        if ("BORROW".equals(last.getActionType())) {
            bookDAO.updateAvailability(last.getBookId(), true);
        } else if ("RETURN".equals(last.getActionType())) {
            bookDAO.updateAvailability(last.getBookId(), false);
        }

        redoStack.push(last);
    }

    private void redoAction() throws SQLException {
        if (redoStack.isEmpty()) return;

        Actions last = redoStack.pop();

        if ("BORROW".equals(last.getActionType())) {
            bookDAO.updateAvailability(last.getBookId(), false);
        } else if ("RETURN".equals(last.getActionType())) {
            bookDAO.updateAvailability(last.getBookId(), true);
        }

        undoStack.push(last);
    }
}
