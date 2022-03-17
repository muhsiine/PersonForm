package com.forms.PersonForm.service;

import com.forms.PersonForm.entities.Person;
import org.springframework.data.domain.Page;

public interface IPersonApi {
    Page<Person> getPeople(int page);
}
