package com.forms.personform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.forms.personform.entities.Person;
import com.forms.personform.service.PersonSrv;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    private MockMvc mockMvc;
    private String keyword = "ih";
    private List<Person> peopleList = new ArrayList<>();

    @Mock
    private PersonSrv personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        peopleList.add(new Person(1L,"Janih","Smith", LocalDate.now(),"Avenue XXX"));
        peopleList.add(new Person(2L,"Iharu","Watson", LocalDate.now(),"Avenue YYY"));
        peopleList.add(new Person(74L,"Sih","Williams", LocalDate.now(),"Avenue ZZZ"));


    }

    @Test
    void index() throws Exception {

        when(personService.getPeople()).thenReturn(peopleList);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/listPeople")
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML_VALUE));
                .andExpect(MockMvcResultMatchers.view().name("people"))
                .andExpect(model().attributeExists("listPeople"))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("id"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("firstname"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("lastname"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("birthday"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("address"))));

    }

    @Test
    void search() throws Exception {

        when(personService.findByKeyword(keyword)).thenReturn(peopleList);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/search")
                                .param("keyword", keyword)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("listPeople"))
                .andExpect(model().attributeExists("keyword"))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("firstname",containsStringIgnoringCase(keyword)))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("lastname"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("birthday"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("address"))));
    }



    @Test
    void delete() throws Exception {
        Long id = 5L;

        when(personService.getPeople()).thenReturn(peopleList);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/delete/{id}",id)
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("listPeople"))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("id",not(equalTo(id))))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("firstname"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("lastname"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("birthday"))))
                .andExpect(model().attribute("listPeople",everyItem(hasProperty("address"))));
            }

    @Test
    void save() throws Exception {
        Person p = new Person(1L,"John","Smith", null,"Avenue XXX");

       ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestForm=ow.writeValueAsString(p);
        System.out.println("-----------"+requestForm);

        when(personService.getPeople()).thenReturn(peopleList);
        //Gives stubbing problem
        when(personService.save(Mockito.any())).thenReturn(1);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestForm)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("listPeople"));

    }
}