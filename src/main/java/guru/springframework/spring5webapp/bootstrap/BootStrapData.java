package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

/**
 * Spring looks for instances of CommanLineRunner and runs them at booting time
 * 
 *
 */
@Component // This tells Spring, this is a Spring managed Component
public class BootStrapData implements CommandLineRunner {

	// Autowiring in the Constructor
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
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

		Publisher publisher = new Publisher("Anagrama", "Una calle", "Madrid", "MD", "A1234");
		//publisherRepository.save(publisher);
		
		book.setPublisher(publisher);
		publisher.getBooks().add(book);
		book2.setPublisher(publisher);
		publisher.getBooks().add(book2);
		
		publisherRepository.save(publisher);
		
		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Number of Publishers: " + publisherRepository.count());
		// This throws an error due to lazy loading failing. It can be fixed adding (fetching = EAGER) to the OneToMany Relationship
		System.out.println("Number of Books published: " + publisherRepository.findById(publisher.getId()).get().getBooks().size() );
		System.out.println("Number of Books published: " + publisher.getBooks().size());
	}
}
