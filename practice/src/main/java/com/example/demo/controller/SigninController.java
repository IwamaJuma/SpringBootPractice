package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SigninController {
  
  @GetMapping("/admin/signin")
  public String signin() {
    return "signin";
  }
  
  @PostMapping("/admin/signin")
  public String signinSuccess() {
    return "redirect:/admin/contacts";
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
