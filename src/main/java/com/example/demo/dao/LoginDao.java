package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.TmUser;

public interface LoginDao extends JpaRepository<TmUser, String>{
}
