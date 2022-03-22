package com.forms.PersonForm.service;

import com.forms.PersonForm.DAO.IPersonDao;

import com.forms.PersonForm.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonApi implements IPersonApi{

    @Autowired
    private IPersonDao personDao;

    private List<Person> peoplePage;

    @Override
    public List<Person> getPeople(int page) {
        return personDao.getAllPerson();
    }

    @Override
    public List<Person> findByKeyword(String kw) {
        peoplePage = personDao.findPersonByFirstName(kw);
        //peoplePage = personDao.findPersonByFirstName(kw,PageRequest.of(page,5));
        return peoplePage;
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
