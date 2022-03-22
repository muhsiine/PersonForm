package com.forms.PersonForm.service;

import com.forms.PersonForm.entities.Person;

import java.util.List;

public interface IPersonApi {
    List<Person> getPeople(int page);
    List<Person> findByKeyword(String kw);
    Person save(Person person);
    void delete(Long id);
}
