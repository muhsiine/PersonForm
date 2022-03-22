package com.forms.personform.controller;


import com.forms.personform.entities.Person;
import com.forms.personform.service.PersonApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonApi personService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page",defaultValue = "0")int page,
                        @RequestParam(name="keyword",defaultValue = "")String kw){
        List<Person> peoplePages = personService.findByKeyword(kw,page);
        model.addAttribute("listPeople",peoplePages);

        return "people";
    }

    @GetMapping("/delete")
    public String delete(Model model,Long id){
        personService.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/formPerson")
    public String form(){
        return "newperson";
    }

    @PostMapping("/save")
    public String save(Person person){
        personService.save(person);
        return "redirect:/index";
    }
}
