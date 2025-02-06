package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;


@Service
public class ContactServiceImp implements ContactService {
  @Autowired
    private ContactRepository contactRepository;
  
  @Override
  public void saveContact(ContactForm contactForm) {
    Contact contact = new Contact();
    
    contact.setLastName(contactForm.getLastName());
    contact.setFirstName(contactForm.getFirstName());
    contact.setEmail(contactForm.getEmail());
    contact.setPhone(contactForm.getPhone());
    contact.setZipCode(contactForm.getZipCode());
    contact.setAddress(contactForm.getAddress());
    contact.setBuildingName(contactForm.getBuildingName());
    contact.setContactType(contactForm.getContactType());
    contact.setBody(contactForm.getBody());
    
    contactRepository.save(contact);
  }
  
  @Override
  public Contact findById(Long id) {
    return contactRepository.findById(id).orElse(null);
  }

  public void update(ContactForm contactForm) {
    Contact contact = contactRepository.findById(contactForm.getId()).orElse(null);
        
    contact.setId(contactForm.getId());
    contact.setLastName(contactForm.getLastName());
    contact.setFirstName(contactForm.getFirstName());
    contact.setEmail(contactForm.getEmail());
    contact.setPhone(contactForm.getPhone());
    contact.setZipCode(contactForm.getZipCode());
    contact.setAddress(contactForm.getAddress());
    contact.setBuildingName(contactForm.getBuildingName());
    contact.setContactType(contactForm.getContactType());
    contact.setBody(contactForm.getBody());
    contact.setCreatedAt(contactForm.getCreatedAt());
    contact.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    
    contactRepository.saveAndFlush(contact);
  }

  public void destroy(Long id) {
    contactRepository.deleteById(id);
  }
  
  public ContactForm ContactMapper(Contact contact) {
    ContactForm form = new ContactForm();
    form.setId(contact.getId());
    form.setLastName(contact.getLastName());
    form.setFirstName(contact.getFirstName());
    form.setEmail(contact.getEmail());
    form.setPhone(contact.getPhone());
    form.setZipCode(contact.getZipCode());
    form.setAddress(contact.getAddress());
    form.setBuildingName(contact.getBuildingName());
    form.setContactType(contact.getContactType());
    form.setBody(contact.getBody());
    form.setCreatedAt(contact.getCreatedAt());
    form.setUpdatedAt(contact.getUpdatedAt());
        
    return form;
  }
  
}
