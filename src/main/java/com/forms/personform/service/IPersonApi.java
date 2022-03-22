package com.forms.personform.service;

import com.forms.personform.entities.Person;

import java.util.List;

public interface IPersonApi {
    List<Person> getPeople(int page);
    List<Person> findByKeyword(String kw, int page);
    Person save(Person person);
    void delete(Long id);
}
