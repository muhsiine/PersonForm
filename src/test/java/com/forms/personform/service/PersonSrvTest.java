package com.forms.personform.service;

import com.forms.personform.dao.IPersonDao;
import com.forms.personform.entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonSrvTest {
    @Mock
    private IPersonDao personDao;

    @InjectMocks
    private PersonSrv personService;

    @Test
    @DisplayName("List of all people should not be null or empty")
    void returnedFullListIsNotNullAndNotEmpty() {
        List<Person> expectedList = new ArrayList<Person>();
        expectedList.add(new Person(null,"John","Watson",null,"Avenue XXX"));

        when(personDao.getAllPerson()).thenReturn(expectedList);

        List<Person> personList= personService.getPeople();
        assertNotNull(personList);
        assertFalse(personList.isEmpty());
    }

    @Test
    @DisplayName("Null or empty list of all people should throw Exception")
    void returnedFullListIsNull() {
        List<Person> expectedList = new ArrayList<Person>();
        when(personDao.getAllPerson()).thenReturn(expectedList);

        assertThrows(RuntimeException.class,
                ()->  personService.getPeople());
    }

    @Test
    @DisplayName("List of people with keyword 'oh' is not null or empty")
    void findByKeywordOh() {

        List<Person> expectedList = new ArrayList<Person>();
        expectedList.add(new Person(null,"John","Watson",null,"Avenue XXX"));

        when(personDao.findPersonByFirstName("oh")).thenReturn(expectedList);

        List<Person> personList= personService.findByKeyword("oh");
        assertNotNull(personList);
        assertFalse(personList.isEmpty());
    }

    @Test
    @DisplayName("List of people with keyword 'an' throws Exception")
    void findByKeyword() {

        List<Person> expectedList = new ArrayList<Person>();
        expectedList.add(new Person(null,"John","Watson",null,"Avenue XXX"));

        assertThrows(RuntimeException.class,
                ()->  personService.findByKeyword("an"));
    }

    @Test
    @DisplayName("The Person saved is not null returns Person")
    void saveNotNull() {
        Person person = new Person(null,"John","Williams",null,"Avenue XXX");
       when(personDao.insertPerson(person.getFirstname(), person.getLastname(),
                                    person.getBirthday(),person.getAddress())).thenReturn(person);

        Person personTest =new Person(null,"John","Williams",null,"Avenue XXX");
        assertNotNull( personService.save(personTest));
    }

    @Test
    @DisplayName("The Person saved is null throws Exception")
    void saveNull() {

        Person personTest = null;
        assertThrows(RuntimeException.class,
                ()->  personService.save(personTest));
    }

    @Test
    @DisplayName("The id of person to delete is null")
    void deleteNotNull() {

        assertThrows(RuntimeException.class,
                ()->  personService.delete(null));
    }

}