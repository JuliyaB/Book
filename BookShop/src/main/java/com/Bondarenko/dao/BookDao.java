package com.Bondarenko.dao;

import com.Bondarenko.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

    void deleteById(Integer id);

    List<Book> findByGenreOrTitleOfTheBookOrContent(String s, String a, String b);
}
