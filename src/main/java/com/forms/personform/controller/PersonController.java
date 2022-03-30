package com.forms.personform.controller;


import com.forms.personform.entities.Person;
import com.forms.personform.service.PersonSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    private final String LIST_PEOPLE_FRAGMENT = "people::peopleTable";

    @Autowired
    private PersonSrv personService;

    @GetMapping(value = "/listPeople")
    public String index(ModelMap model){
        List<Person> peoplePages = personService.getPeople();
        model.addAttribute("listPeople",peoplePages);

        return "people";
    }
    @GetMapping("/search")
    public String search(ModelMap model,
                        @RequestParam(name="keyword",defaultValue = "")String kw){
        List<Person> peoplePages = personService.findByKeyword(kw);
        model.addAttribute("listPeople",peoplePages);
        model.addAttribute("keyword",kw);

        return LIST_PEOPLE_FRAGMENT;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(ModelMap model,@PathVariable Long id){
        personService.delete(id);
        List<Person> peoplePages = personService.getPeople();
        model.addAttribute("listPeople",peoplePages);
        return LIST_PEOPLE_FRAGMENT;
    }

    @GetMapping("/formPerson")
    public String form(){
        return "newperson";
    }

    @PostMapping("/save")
    public String save(ModelMap model, Person person){
        personService.save(person);
        List<Person> peoplePages = personService.getPeople();
        model.addAttribute("listPeople",peoplePages);
        return LIST_PEOPLE_FRAGMENT;
    }
}
