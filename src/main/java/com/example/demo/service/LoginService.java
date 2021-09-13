package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LoginDao;
import com.example.demo.domain.LoginUser;

@Service 
public class LoginService implements UserDetailsService  {
	
	@Autowired
	LoginDao loginDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return loginDao.findById(username).filter(m -> m !=null).map(m -> new LoginUser(m)).get();
	}

}
