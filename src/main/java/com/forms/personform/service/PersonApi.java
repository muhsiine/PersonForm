package com.forms.personform.service;

import com.forms.personform.dao.IPersonDao;

import com.forms.personform.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonApi implements IPersonApi{

    @Autowired
    private IPersonDao personDao;

    @Override
    public List<Person> getPeople(int page) {
        return personDao.getAllPerson();
    }

    @Override
    public List<Person> findByKeyword(String kw,int page) {
        return  personDao.findPersonByFirstName(kw);
    }

    @Override
    public Person save(Person person) {
        return personDao.insertPerson(person.getFirstname(),person.getLastname(),
                                            person.getBirthday(),person.getAddress());
    }

    @Override
    public void delete(Long id) {
        personDao.deletePerson(id);
    }

}
