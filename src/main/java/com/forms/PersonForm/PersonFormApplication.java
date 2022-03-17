package com.forms.PersonForm;

import com.forms.PersonForm.DAO.PersonRepository;
import com.forms.PersonForm.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PersonFormApplication {
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(PersonFormApplication.class, args);
	}


}
