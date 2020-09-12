package guru.springframework.spring5webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5webapp.repositories.BookRepository;

@Controller
public class BookController {
	
	private final BookRepository bookRepository;
	
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// we map this to a specific URL
	@RequestMapping("/books")
	public String getBooks(Model model) {  //  (Spring inserts a model)
		
		model.addAttribute("books", bookRepository.findAll()); // add an attribute to the model
		
		return "books"; // This is the VIEW name. The model is sent to the View
	}

}
