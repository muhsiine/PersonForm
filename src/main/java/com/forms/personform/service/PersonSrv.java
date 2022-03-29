package com.forms.personform.service;

import com.forms.personform.dao.IPersonDao;

import com.forms.personform.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonSrv implements IPersonSrv {

    @Autowired
    private IPersonDao personDao;

    @Override
    public List<Person> getPeople() {
        List<Person> personList = personDao.getAllPerson();
        if (personList != null && !personList.isEmpty()) {
            return personList;
        } else {
            throw new RuntimeException("No Person was found.");
        }
    }

    @Override
    public List<Person> findByKeyword(String kw) {
        List<Person> personByFistName = personDao.findPersonByFirstName(kw);
        if (personByFistName != null && !personByFistName.isEmpty()) {
            return personByFistName;
        } else {
            throw new RuntimeException("No Person was found.");
        }

    }

    @Override
    public Person save(Person person) {
        if (person != null) {
            return personDao.insertPerson(person.getFirstname(), person.getLastname(),
                    person.getBirthday(), person.getAddress());
        } else {
            throw new RuntimeException("Can't save a null Person.");
        }

    }

    @Override
    public int delete(Long id) {
        if (id != null) {
            return personDao.deletePerson(id);
        } else {
            throw new RuntimeException("The given id is null.");
        }
    }

}
