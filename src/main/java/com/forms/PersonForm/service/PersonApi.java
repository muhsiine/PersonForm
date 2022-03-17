package com.forms.PersonForm.service;

import com.forms.PersonForm.DAO.PersonRepository;
import com.forms.PersonForm.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class PersonApi implements IPersonApi{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Person> getPeople(int page) {
        Page<Person> peoplePage = personRepository.findAll(PageRequest.of(page,5));
        return peoplePage;
    }

}
