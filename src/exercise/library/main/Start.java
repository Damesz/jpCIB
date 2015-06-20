package exercise.library.main;

import exercise.library.BookNotFoundException;
import exercise.library.Exception.DoesNotMatchException;
import exercise.library.impl.BookServiceImpl;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class Start {

    private static BookServiceImpl bookService = new BookServiceImpl();

    public static void main(String[] args) throws BookNotFoundException, DoesNotMatchException {
        if(args.length > 0) {
            String isbn = args[0];
            try {
                System.out.println("Book title for this ISBN (" + isbn +") number is: " + bookService.retrieveBook(isbn).getTitle());
                System.out.println("Book Summary for this ISBN (" + isbn + ") number is: " + bookService.getBookSummary(isbn));
            } catch (DoesNotMatchException e) {
                System.out.println(e.getMessage());
            } catch (BookNotFoundException e) {
                System.out.println("In our database has no book with this ISBN number: " + isbn + ". Please check the ISBN number");
            }


        } else {
            System.out.println("Please add argument");
        }
    }
}
