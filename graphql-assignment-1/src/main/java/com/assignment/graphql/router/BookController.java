package com.assignment.graphql.router;
import com.assignment.graphql.model.Book;

import com.assignment.graphql.service.AuthorService;
import com.assignment.graphql.service.BookService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// TODO: Auto-generated Javadoc
/**
 * The Class BookController.
 */
@Controller
@Slf4j
public class BookController {

	@Autowired
    /** The book service. */
    private BookService bookService;
    
    @Autowired 
    /** The author service. */
    private AuthorService authorService;
    
    /**
     * Creates the.
     *
     * @param bookInput the book input
     * @return the mono
     */
    @MutationMapping("createBook")
    public Mono<Object> create(@Argument(name = "book") BookInput bookInput) {   
        Book book = Book.builder()
                .bookTitle(bookInput.getBookTitle())
                .publishingYear(bookInput.getPublishingYear())
                .ratings(bookInput.getRatings())
                .price(bookInput.getPrice())
                .build();
        log.info("[book] saving book details " + book.getBook_id());
        
       return authorService.getAuthorById(bookInput.getAuthorId()).map(ifNotEmpty->
         {
        	 if (ifNotEmpty.getAuthor_id()!=null) {
        	 authorService.getAuthorById(bookInput.getAuthorId()).
             map(response->
             {
                 response.setNumberOfBooksWritten(response.getNumberOfBooksWritten()+1);
                 book.setAuthor(response);
                 return response;
             })	 
              .flatMap(response->{
            	  return authorService.createAuthor(response);
              }).flatMap(author->bookService.publishBook(book));
        	 
        	 return bookService.publishBook(book);
        	 }
        	 else
        		 return Mono.error(new Exception("author does not exit with author please enter a valid book"));
         });
         
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     */
    @QueryMapping("allBooks")
    public Flux<Book> getAll() {
    	  log.info("[book] fething all details ");
        return bookService.getAllBooks();
    }

    /**
     * Gets the.
     *
     * @param bookId the book id
     * @return the mono
     */
    @QueryMapping("getBook")
    public Mono<Book> get(@Argument String bookId) {
    	log.info("[book] fething book details by id.. {$bookId}..."+bookId);
        return bookService.getBookById(bookId)
        		.switchIfEmpty(Mono.error(new Exception(" book details not available...")));
    }

}

class BookInput
{
    @Id
    private String author_id;
    private String bookTitle;
    private int publishingYear;
    private double ratings;
    private double price;
    private String authorId;

    public String getId() {
        return author_id;
    }

    public void setId(String author_id) {
        this.author_id = author_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
