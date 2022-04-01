package com.forms.personform;


import com.forms.personform.entities.Person;
import com.forms.personform.service.PersonSrv;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(scripts = "classpath:table.sql")
@SpringBootTest
public class PersonIT {

    @Autowired
    PersonSrv personSrv;

    @Test
    public void getPeople() {
        List<Person> personList = personSrv.getPeople();
        assertEquals(3, personList.size());
    }


}
