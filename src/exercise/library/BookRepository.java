package exercise.library;

public interface BookRepository {
	Book retrieveBook(String isbn);
	String getISBN_PREFIX();
}
