package com.Bondarenko.controller;

import com.Bondarenko.dao.AuthorDao;
import com.Bondarenko.model.Author;
import org.apache.log4j.Logger;
import com.Bondarenko.dao.BookDao;
import com.Bondarenko.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    private final static Logger logger = Logger.getLogger(IndexController.class);

    private BookDao bookDao;

    private final AuthorDao authorDao;

    public IndexController(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("index");
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"{str}"}, method = RequestMethod.GET)
    public String author(Model model, @RequestParam("str") String str) {
        logger.info("Search, input: " + str);
        List<Book> book1 = bookDao.findByGenreOrTitleOfTheBookOrContent(str, str, str);
        List<Author> author1 = authorDao.findByFirstNameOrLastNameOrBiography(str, str, str);
        model.addAttribute("author1", author1);
        model.addAttribute("book1", book1);
        return "search";
    }
}
