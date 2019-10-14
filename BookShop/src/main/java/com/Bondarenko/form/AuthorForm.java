package com.Bondarenko.form;

public class AuthorForm {
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
    private String biography;

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public Integer getYearOfBirth(){
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth){
        this.yearOfBirth=yearOfBirth;
    }

    public String getBiography(){
        return biography;
    }

    public void setBiography(String biography){
        this.biography=biography;
    }
}
