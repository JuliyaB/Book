package com.Bondarenko.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "titleOfTheBook", nullable = false)
    private String titleOfTheBook;

    @Column(name = "yearOfWriting", nullable = false)
    private Integer yearOfWriting;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @ManyToOne
    @JoinColumn(name = "bookAuthor", referencedColumnName = "id", nullable = false)
    private Author author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleOfTheBook() {
        return titleOfTheBook;
    }

    public void setTitleOfTheBook(String titleOfTheBook) {
        this.titleOfTheBook = titleOfTheBook;
    }

    public Integer getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(Integer yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
