// dsaa/SortEngine.java
package dsaa;

import model.Book;
import java.util.List;

public class SortEngine {

    public void quickSort(List<Book> book, int low, int high) {
        if (low < high) {
            int pi = partition(book, low, high);
            quickSort(book, low, pi - 1);
            quickSort(book, pi + 1, high);
        }
    }

    private int partition(List<Book> book, int low, int high) {
        String pivot = book.get(high).getTitle();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (book.get(j).getTitle().compareTo(pivot) < 0) {
                i++;
                Book temp = book.get(i);
                book.set(i, book.get(j));
                book.set(j, temp);
            }
        }

        Book temp = book.get(i + 1);
        book.set(i + 1, book.get(high));
        book.set(high, temp);

        return i + 1;
    }
}
