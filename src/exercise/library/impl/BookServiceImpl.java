package exercise.library.impl;

import exercise.library.*;
import exercise.library.Exception.DoesNotMatchException;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class BookServiceImpl implements BookService {


    private BookRepository repository = new BookRepositoryImpl();
    private static final String DOES_NOT_MATCH_EXCEPTION_MESSAGE = "This ISBN text is not Valid. Must be Start with: ";

    @Override
    public Book retrieveBook(String isbn) throws BookNotFoundException, DoesNotMatchException {
        checkIsbn(isbn);
        return getBook(isbn);
    }

    @Override
    public String getBookSummary(String isbn) throws BookNotFoundException, DoesNotMatchException {
        checkIsbn(isbn);
        return getBook(isbn).toString();
    }

    public String getDoesNotMatchExceptionErrorMessage() {
        return DOES_NOT_MATCH_EXCEPTION_MESSAGE + repository.getISBN_PREFIX();
    }

    private void checkIsbn(String isbn) throws DoesNotMatchException {
        if(!isbn.startsWith(repository.getISBN_PREFIX())) {
            throw new DoesNotMatchException(getDoesNotMatchExceptionErrorMessage());
        }
    }

    private Book getBook(String isbn) throws BookNotFoundException {
        Book book = repository.retrieveBook(isbn);
        if(null == book) {
            throw new BookNotFoundException();
        }

        return book;
    }
}
