package com.Bondarenko.dao;

import com.Bondarenko.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends CrudRepository<Author, Integer> {

    void deleteById(Integer id);

    List<Author> findByFirstNameOrLastNameOrBiography(String s, String a, String b);
}
