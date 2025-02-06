package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
  
  void saveContact(ContactForm contactForm);
  
  Contact findById(Long Id);
}
