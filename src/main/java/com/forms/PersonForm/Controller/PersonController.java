package com.forms.PersonForm.Controller;

import com.forms.PersonForm.DAO.PersonRepository;
import com.forms.PersonForm.entities.Person;
import com.forms.PersonForm.service.PersonApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonApi personService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page",defaultValue = "0")int page){
        Page<Person> peoplePages = personService.getPeople(page);
        model.addAttribute("listPeople",peoplePages.getContent());
        model.addAttribute("pages",new int[peoplePages.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "people";
    }
}
