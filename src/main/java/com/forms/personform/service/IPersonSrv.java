package com.forms.personform.service;

import com.forms.personform.entities.Person;

import java.util.List;

public interface IPersonSrv {
    List<Person> getPeople();
    List<Person> findByKeyword(String kw);
    int save(Person person);
    int delete(Long id);
}
