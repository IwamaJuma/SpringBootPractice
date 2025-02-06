package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactServiceImp;

@Controller
public class ContactForAdminController {
  @Autowired
  private ContactServiceImp contactService;
  @Autowired
  private ContactRepository contactRepository;
  
  @GetMapping("/admin/contacts")
  public String index(Model model) {
    List<Contact> contacts = contactRepository.findAll();
    model.addAttribute("contacts", contacts);
    return "contacts";
  }
  
  @GetMapping("/admin/contacts/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {
    Contact contact = contactService.findById(id);
    model.addAttribute("contact", contact);
    return "detail";
  }
  
  @GetMapping("/admin/contacts/{id}/edit")
  public String edit(@PathVariable("id") Long id, Model model) {
    Contact contact = contactService.findById(id);
    ContactForm contactForm = contactService.ContactMapper(contact);
    model.addAttribute("contactForm", contactForm);
    
    return "edit";
  }
  
  @PostMapping("/admin/contacts/confirm")
  public String confirm(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult, HttpServletRequest request) {
    
    if(errorResult.hasErrors()) {
      return "edit";
    }
    
    HttpSession session = request.getSession();
    session.setAttribute("contactForm", contactForm);
    
    return "redirect:/contacts/confirm";
  }
  
  @GetMapping("/contacts/confirm")
  public String confirm(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession();
    
    ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
    model.addAttribute("contactForm",contactForm);
    return "editConfirmation";
  }
  
  @PostMapping("/admin/contacts/update")
  public String update(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession();
    ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
    contactService.update(contactForm);
    return "redirect:/admin/contacts/" + contactForm.getId();
  }
  
  @PostMapping("/admin/contacts/destroy")
  public String destroy(@ModelAttribute ContactForm contactForm) {
    contactService.destroy(contactForm.getId());
    return "redirect:/admin/contacts";
  }
}
