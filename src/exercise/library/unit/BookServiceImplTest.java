package exercise.library.unit;

import exercise.library.Book;
import exercise.library.BookNotFoundException;
import exercise.library.Exception.DoesNotMatchException;
import exercise.library.impl.BookServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class BookServiceImplTest {

    private BookServiceImpl bookService = new BookServiceImpl();

    private static final String INVALID = "INVALID-TEXT";
    private static final String NOT_FOUND = "ISBN-777";
    private static final String HARRY_POTTER = "ISBN-001";
    private Map<String, String> BOOKS_TEST = new HashMap<String, String>();

    @Before
    public void setUp() throws Exception {
        BOOKS_TEST.put("ISBN-001", "[ISBN-001] Harry Potter and the Deathly Hallows - Sorcery and Magic.");
        BOOKS_TEST.put("ISBN-002", "[ISBN-002] The Player of Games - Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy.");
        BOOKS_TEST.put("ISBN-003", "[ISBN-003] Genius: Richard Feynman and Modern Physics - A brilliant interweaving of Richard Feynman's colourful " +
                "life and a detailed and accessible account of his theories and experiments.");
    }

    @Test
    public void testRetrieveBook() throws BookNotFoundException, DoesNotMatchException {
        Throwable exception = null;
        try {
            bookService.retrieveBook(INVALID);
        } catch (Throwable ex) {
            exception = ex;
        }

        assertTrue(exception instanceof DoesNotMatchException);
        assertEquals(exception.getMessage(), bookService.getDoesNotMatchExceptionErrorMessage());

        try {
            bookService.retrieveBook(NOT_FOUND);
        } catch (Throwable ex) {
            exception = ex;
        }

        assertTrue(exception instanceof BookNotFoundException);

        Book book = null;
        exception = null;
        try {
            book = bookService.retrieveBook(HARRY_POTTER);
        } catch (Throwable ex) {
            exception = ex;
        }

        assertNull(exception);
        assertTrue(book instanceof Book);
        assertEquals("Harry Potter and the Deathly Hallows", (book.getTitle()));
    }

    @Test
    public void testgetBookSummary() {
        Throwable exception = null;
        try {
            bookService.getBookSummary(INVALID);
        } catch (Throwable ex) {
            exception = ex;
        }

        assertTrue(exception instanceof DoesNotMatchException);
        assertEquals(exception.getMessage(), bookService.getDoesNotMatchExceptionErrorMessage());

        try {
            bookService.getBookSummary(NOT_FOUND);
        } catch (Throwable ex) {
            exception = ex;
        }

        assertTrue(exception instanceof BookNotFoundException);

        List<String> bookSummaries = new ArrayList<String>();
        exception = null;
        try {
            for (Map.Entry<String,String> entry : BOOKS_TEST.entrySet()) {
                bookSummaries.add(bookService.getBookSummary(entry.getKey()));
            }
        } catch (Throwable ex) {
            exception = ex;
        }

        assertNull(exception);

        int i = 0;
        for (Map.Entry<String,String> entry : BOOKS_TEST.entrySet()) {
            assertEquals(entry.getValue(), bookSummaries.get(i));
            i++;
        }
    }
}
