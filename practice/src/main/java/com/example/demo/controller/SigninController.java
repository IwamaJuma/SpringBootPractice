package com.example.demo.controller;

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

@Controller
public class SigninController {
  
  private AuthenticationManager authenticationManager;
  
  
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
  public String contacts() {
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
