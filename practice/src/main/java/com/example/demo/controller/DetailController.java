package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactServiceImp;

@Controller
public class DetailController {
  @Autowired
  private ContactServiceImp contactService;
  
  @GetMapping("/admin/contacts/{id}")
  public String show(@PathVariable("id") Long id, Model model) {
    Contact contact = contactService.findById(id);
    model.addAttribute("contact", contact);
    return "detail";
  }

}
