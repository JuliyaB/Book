package com.Bondarenko.controller;

import com.Bondarenko.dao.AuthorDao;
import com.Bondarenko.model.Author;
import org.apache.log4j.Logger;
import com.Bondarenko.dao.BookDao;
import com.Bondarenko.form.BookForm;
import com.Bondarenko.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainControllerBook {
    private final static Logger logger = Logger.getLogger(MainControllerBook.class);
    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public MainControllerBook(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/bookList"}, method = RequestMethod.GET)
    public String bookList(Model model) {
        books = (List<Book>) bookDao.findAll();
        logger.info("Book table output, input: " + books);
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        return "bookList";
    }

    @RequestMapping(value = {"/addBook"}, method = RequestMethod.GET)
    public String showAddBookPage(Model model) {
        logger.info("Book adding page, input: " + model);
        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);
        authors = (List<Author>) authorDao.findAll();
        model.addAttribute("authors", authors);
        return "addBook";
    }

    @RequestMapping(value = {"/addBook"}, method = RequestMethod.POST)
    public String saveBook(Model model, @ModelAttribute("bookForm") BookForm bookForm) {
        logger.info("Writing a book to the database, input data: " + model + ", " + bookForm);
        String titleOfTheBook = bookForm.getTitleOfTheBook();
        Integer bookAuthor = bookForm.getBookAuthor();
        Integer yearOfWriting = bookForm.getYearOfWriting();
        String genre = bookForm.getGenre();
        String content = bookForm.getContent();
        Integer cost = bookForm.getCost();
        if (titleOfTheBook.length() > 0 && yearOfWriting > 0 && genre.length() > 0
                && content.length() > 0 && cost > 0) {
            bookDao.save(createBook(titleOfTheBook, bookAuthor, yearOfWriting, genre, content, cost));
            return "redirect:/bookList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addBook";
    }

    @RequestMapping(value = "/bookList/{id}", method = RequestMethod.GET)
    public String ormDeleteBookById(@PathVariable Integer id) {
        logger.info("Delete a book by id, input: " + id);
        bookDao.deleteById(id);
        return "redirect:/bookList";
    }

    public Book createBook(String titleOfTheBook, Integer bookAuthor,
                           Integer yearOfWriting, String genre, String content, Integer cost) {
        Book book = new Book();
        book.setCost(cost);
        book.setContent(content);
        book.setGenre(genre);
        book.setYearOfWriting(yearOfWriting);
        book.setTitleOfTheBook(titleOfTheBook);
        Optional<Author> author = authorDao.findById(bookAuthor);
        book.setAuthor(author.get());
        return book;
    }
}
