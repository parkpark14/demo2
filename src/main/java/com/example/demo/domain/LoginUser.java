package com.example.demo.domain;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser extends User {

	private static final long serialVersionUID = 1L;

	private static final String ROLE_PREFIX = "ROLE_";

    private TmUser tmUser;

    public LoginUser(TmUser tmUser) {

        super(tmUser.getUid(), tmUser.getPwd(), Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + tmUser.getRoles())));

        this.tmUser = tmUser;

    }
}
