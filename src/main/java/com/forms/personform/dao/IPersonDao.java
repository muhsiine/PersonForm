package com.forms.personform.dao;

import com.forms.personform.entities.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface IPersonDao {

    List<Person> getAllPerson();
    List<Person> findPersonByFirstName(@Param("keyword") String kw);
    int insertPerson(@Param("firstname")String firstname, @Param("lastname")String lastname,
                      @Param("birthday") LocalDate birthday, @Param("address")String address);
    int deletePerson(@Param("id") Long id);

}
