package com.forms.PersonForm.DAO;

import com.forms.PersonForm.entities.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface IPersonDao {

    List<Person> getAllPerson();
    List<Person> findPersonByFirstName(@Param("keyword") String kw);
    Person insertPerson(@Param("firstname")String firstname, @Param("lastname")String lastname,
                      @Param("birthday") LocalDate birthday, @Param("address")String address);
    int deletePerson(@Param("id") Long id);

}
