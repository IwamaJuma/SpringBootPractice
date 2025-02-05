package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactServiceImp;

@Controller
public class EditController {
  @Autowired
  private ContactServiceImp contactService;
  
  @GetMapping("/admin/contacts/{id}/edit")
  public String edit(@PathVariable("id") Long id, Model model) {
    Contact contact = contactService.findById(id);
    model.addAttribute("contact", contact);
    return "edit";
  }
  
  @PostMapping("/admin/contacts/update")
  public String update(@ModelAttribute Contact contact) {
    contactService.updateContact(contact);
    return "redirect:/admin/contacts/" + contact.getId();
  }

}
