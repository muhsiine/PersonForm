package com.forms.personform.controller;


import com.forms.personform.entities.Person;
import com.forms.personform.service.PersonApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonApi personService;

    @GetMapping("/listPeople")
    public String index(Model model, @RequestParam(name="page",defaultValue = "0")int page){
        List<Person> peoplePages = personService.getPeople(page);
        model.addAttribute("listPeople",peoplePages);

        return "people";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam(name="page",defaultValue = "0")int page,
                        @RequestParam(name="keyword",defaultValue = "")String kw){
        List<Person> peoplePages = personService.findByKeyword(kw,page);
        model.addAttribute("listPeople",peoplePages);
        model.addAttribute("keyword",kw);

        return "people::peopleTable";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(Model model,@PathVariable Long id,@RequestParam(name="page",defaultValue = "0")int page){
        personService.delete(id);
        List<Person> peoplePages = personService.getPeople(page);
        model.addAttribute("listPeople",peoplePages);
        return "people::peopleTable";
    }

    @GetMapping("/formPerson")
    public String form(){
        return "newperson";
    }

    @PostMapping("/save")
    public String save(ModelMap model, Person person,@RequestParam(name="page",defaultValue = "0")int page,
                       @RequestParam(name="keyword",defaultValue = "")String kw){
        personService.save(person);
        List<Person> peoplePages = personService.findByKeyword(kw,page);
        model.addAttribute("listPeople",peoplePages);
        model.addAttribute("keyword",kw);
        return "people::peopleTable";
    }
}
