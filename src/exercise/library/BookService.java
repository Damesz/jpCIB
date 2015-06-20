package exercise.library;

import exercise.library.Exception.DoesNotMatchException;

public interface BookService
{
    public Book retrieveBook(String isbn) throws BookNotFoundException, DoesNotMatchException;
    public String getBookSummary(String isbn) throws BookNotFoundException, DoesNotMatchException;
}
