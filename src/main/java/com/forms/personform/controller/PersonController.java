package com.forms.personform.controller;


import com.forms.personform.entities.Person;
import com.forms.personform.service.PersonSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    private final String LIST_PEOPLE_FRAGMENT = "people::peopleTable";

    @Autowired
    private PersonSrv personService;

    @GetMapping("/listPeople")
    public String index(Model model, @RequestParam(name="page",defaultValue = "0")int page){
        List<Person> peoplePages = personService.getPeople();
        model.addAttribute("listPeople",peoplePages);

        return "people";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam(name="page",defaultValue = "0")int page,
                        @RequestParam(name="keyword",defaultValue = "")String kw){
        List<Person> peoplePages = personService.findByKeyword(kw);
        model.addAttribute("listPeople",peoplePages);
        model.addAttribute("keyword",kw);

        return LIST_PEOPLE_FRAGMENT;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(Model model,@PathVariable Long id,@RequestParam(name="page",defaultValue = "0")int page){
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
    public String save(ModelMap model, Person person,@RequestParam(name="page",defaultValue = "0")int page,
                       @RequestParam(name="keyword",defaultValue = "")String kw){
        personService.save(person);
        List<Person> peoplePages = personService.findByKeyword(kw);
        model.addAttribute("listPeople",peoplePages);
        model.addAttribute("keyword",kw);
        return LIST_PEOPLE_FRAGMENT;
    }
}
