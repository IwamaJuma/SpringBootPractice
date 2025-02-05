package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.SigninRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  private SigninRepository signin;
  //private BCryptPasswordEncoder encoder;
  
  @Autowired
  public UserDetailsServiceImpl(@Lazy SigninRepository signin) {
    this.signin = signin;
    //this.encoder = encoder;
  }
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
    Admin admin = signin.findAdmin(email);
    
    if (admin == null) {
      throw new UsernameNotFoundException("Email:" + email + "was not found in the database");
    }
    
    List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
    GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
    grantList.add(authority);
    
    //encoder = new BCryptPasswordEncoder();
    
    UserDetails userDetails = (UserDetails)new User(admin.getEmail(),admin.getPassword(),grantList);
    
    return userDetails;
  }


}
