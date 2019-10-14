package com.Bondarenko.controller;

import com.Bondarenko.dao.AuthorDao;
import com.Bondarenko.model.Author;
import org.apache.log4j.Logger;
import com.Bondarenko.form.AuthorForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainControllerAuthor {
    private final static Logger logger = Logger.getLogger(MainControllerAuthor.class);
    protected static List<Author> authors = new ArrayList<>();

    private final AuthorDao authorDao;

    public MainControllerAuthor(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/authorList"}, method = RequestMethod.GET)
    public String authorList(Model model) {
        authors = (List<Author>) authorDao.findAll();
        logger.info("Author table output, input: " + authors);
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @RequestMapping(value = {"/addAuthor"}, method = RequestMethod.GET)
    public String showAddAuthorPage(Model model) {
        logger.info("Author adding page, input: " + model);
        AuthorForm authorForm = new AuthorForm();
        model.addAttribute("authorForm", authorForm);
        return "addAuthor";
    }

    @RequestMapping(value = {"/addAuthor"}, method = RequestMethod.POST)
    public String saveAuthor(Model model, @ModelAttribute("authorForm") AuthorForm authorForm) {
        logger.info("Writing a author to the database, input data: " + model + ", " + authorForm);
        String firstName = authorForm.getFirstName();
        String lastName = authorForm.getLastName();
        Integer yearOfBirth = authorForm.getYearOfBirth();
        String biography = authorForm.getBiography();
        if (firstName.length() > 0 && lastName.length() > 0
                && yearOfBirth > 0 && biography.length() > 0) {
            authorDao.save(createAuthor(firstName,lastName,yearOfBirth,biography));
            return "redirect:/authorList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addAuthor";
    }

    @RequestMapping(value = "/authorList/{id}", method = RequestMethod.GET)
    public String ormDeleteAuthorById(@PathVariable Integer id) {
        logger.info("Delete a author by id, input: " + id);
        authorDao.deleteById(id);
        return "redirect:/authorList";
    }

    public Author createAuthor(String firstName, String lastName, Integer yearOfBirth, String biography) {
        Author author = new Author();
        author.setBiography(biography);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setYearOfBirth(yearOfBirth);
        return author;
    }
}
