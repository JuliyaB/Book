package com.Bondarenko.form;

import java.util.List;

public class BookForm {
    private String titleOfTheBook;
    private Integer bookAuthor;
    private Integer yearOfWriting;
    private String genre;
    private String content;
    private Integer cost;

    public String getTitleOfTheBook(){
        return titleOfTheBook;
    }

    public void setTitleOfTheBook(String titleOfTheBook){
        this.titleOfTheBook=titleOfTheBook;
    }

    public Integer getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(Integer bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getYearOfWriting(){
        return yearOfWriting;
    }

    public void setYearOfWriting(Integer yearOfWriting){
        this.yearOfWriting=yearOfWriting;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public Integer getCost(){
        return cost;
    }

    public void setCost(Integer cost){
        this.cost=cost;
    }
}
