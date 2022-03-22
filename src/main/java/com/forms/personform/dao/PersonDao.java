package com.forms.personform.dao;

import com.forms.personform.entities.Person;
import com.forms.personform.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PersonDao implements IPersonDao {
    public void saveEmployee(Person employee) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("insertEmployee", employee);
        session.commit();
        session.close();
    }


    public Person findById(int employeeId) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Person employee = (Person) session.selectOne("findById", employeeId);
        session.commit();
        session.close();
        return employee;
    }

    @Override
    public List<Person> getAllPerson() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Person> personList = session.selectList("getAllPerson");
        session.commit();
        session.close();
        return personList;
    }

    @Override
    public List<Person> findPersonByFirstName(String kw) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Person> personList = session.selectList("findPersonByFirstName", kw);
        session.commit();
        session.close();
        return personList;
    }

    @Override
    public Person insertPerson(String firstname, String lastname,
                               LocalDate birthday, String address) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Person person = Person.builder()
                .firstname(firstname)
                .lastname(lastname)
                .birthday(birthday)
                .address(address)
                .build();
        session.insert("insertPerson", person);
        session.commit();
        session.close();
        return person;
    }

    @Override
    public int deletePerson(Long id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int deleteRows = session.delete("deletePerson", id);
        session.commit();
        session.close();
        return deleteRows;
    }
}
