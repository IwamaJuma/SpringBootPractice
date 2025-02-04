package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller
public class SignupController {
  
  @Autowired
  private AdminService adminService;
  
  @GetMapping("/admin/signup")
  public String signup(Model model) {
    model.addAttribute("adminForm", new AdminForm());
    
    return "signup";
  }
  
  @PostMapping("/admin/signup")
  public String signup(@Validated @ModelAttribute("adminForm") AdminForm adminForm,
        BindingResult errorResult,HttpServletRequest request) {
    if(errorResult.hasErrors()) {
      return "signup";
    }
    
    HttpSession session = request.getSession();
    session.setAttribute("adminForm", adminForm);
    
    adminService.saveAdmin(adminForm);
    return "redirect:/admin/signin";
  }
  

}
