package com.example.demo.repository;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
public class SigninRepository {
  @Autowired
  EntityManager em;
  
  public Admin findAdmin(String email) {
    String query = "";
    query += "SELECT * ";
    query += "FROM  admins ";
    query += "WHERE email = :email ";
    return (Admin)em.createNativeQuery(query, Admin.class).setParameter("email", email)
                .getSingleResult();
    
  }

}
