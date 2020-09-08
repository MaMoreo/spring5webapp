package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;

/**
 * Spring looks for instances of CommanLineRunner and runs them at booting time
 * 
 *
 */
@Component // This tells Spring, this is a Spring managed Component
public class BootStrapData implements CommandLineRunner {

	// Autowiring
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Author eric = new Author("Eric", "Evans");
		Book book = new Book("Domain Driven Desing", "123456");
		eric.getBooks().add(book);
		book.getAuthors().add(eric);

		authorRepository.save(eric);
		bookRepository.save(book);

		Author rod = new Author("Rod", "Johnson");
		Book book2 = new Book("J2EE development without EJB", "123999");

		rod.getBooks().add(book2);
		book2.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(book2);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: " + bookRepository.count());
	}
}
