package sg.edu.nus.iss.lecture15day.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.lecture15day.services.PersonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.lecture15day.Util.Util;
import sg.edu.nus.iss.lecture15day.model.Person;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import sg.edu.nus.iss.lecture15day.repo.ListRepo;



@Controller
@RequestMapping("/person")
public class PersonController {
  
  @Autowired
  PersonService personservice;

  // @GetMapping("/by")
  // public String getPersonList(Model model) {
  //   model.addAttribute("persons", new Person());
  //     return "tablelist";
  // }

  @GetMapping(" ")
  public String personListPage(Model model) {
    List<Person> persons = personservice.findAll(Util.keyPerson);
    model.addAttribute("persons", persons);
      return "tablelist";
  }
  
  @GetMapping("/create")
  public String createPerson(Model model) {
    Person p = new Person();
    model.addAttribute("per", p);
      return "Createform";
  }
  
  @PostMapping("/create")
  public String postCreatePerson(@Valid @ModelAttribute("per") Person person,BindingResult result,Model model) {
      if (result.hasErrors()) {
        return "Createform";
      } else{
        personservice.addPerson(Util.keyPerson, person);
        return "redirect:/person";
      }
      
      
  }
  @GetMapping("/delete/{id}")
  public String getMethodName(@PathVariable("id") String Id) {
    List<Person> persons = personservice.findAll(Util.keyPerson);
    Person foundPerson = persons.stream()
    .filter(p -> p.getId().equals(Id))
    .findFirst()
    .orElse(null);
    
    if (foundPerson != null) {
      personservice.removebyvalue(Util.keyPerson, 0, foundPerson);
  }
      return "redirect:/person";
  }
  
  




  


  

}
