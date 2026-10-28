// dsaa/SearchEngine.java
package dsaa;

import model.Book;
import java.util.List;

public class SearchEngine {

    public Book binarySearch(List<Book> book, String title) {
        int low = 0, high = book.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = book.get(mid).getTitle().compareToIgnoreCase(title);

            if (cmp == 0) return book.get(mid);
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return null;
    }
}
