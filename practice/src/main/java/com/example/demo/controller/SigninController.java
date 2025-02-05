package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Controller
public class SigninController {
  
  private AuthenticationManager authenticationManager;
  @Autowired
  private ContactRepository contactRepository;
  
  
  @GetMapping("/admin/signin")
  public String signin() {
    return "signin";
  }
  
  @PostMapping("/admin/signin")
  public String signinSuccess(@RequestParam String email, @RequestParam String password, Model model) {
    try {
      UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email,password);
      Authentication auth = authenticationManager.authenticate(authReq);
      SecurityContextHolder.getContext().setAuthentication(auth);
      return "redirect:/admin/contacts";
    } catch (AuthenticationException e) {
      model.addAttribute("error","Invalid email and password!");
      return "signin";
    } 
  }
  
  @GetMapping("/admin/contacts")
  public String contacts(Model model) {
    List<Contact> contacts = contactRepository.findAll();
    model.addAttribute("contacts", contacts);
    return "contacts";
  }
  
  @GetMapping("/")
  public String home() {
    return "contacts";
  }
  
  @GetMapping("/admin/signout")
  public String signout() {
    return "signout";
  }
  
  

}
