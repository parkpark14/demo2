package com.example.demo.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginService loginService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("security config");

        http.authorizeRequests()
                .antMatchers("/main/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        http.formLogin().loginPage("/login");

        http.exceptionHandling().accessDeniedPage("/accessDenied");

        http.logout().logoutUrl("/logout").invalidateHttpSession(true);

        http.userDetailsService(loginService);
	}
	
	// SHA512로 비밀번호 암호화
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(loginService).passwordEncoder(new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {

//                String rawPasswordStr = (String) rawPassword;
//                String encodePassword = "";
//
//                try {
//                    encodePassword = EncBySha512(rawPasswordStr);
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
//
//                return encodePassword;

                return "";

            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {

                String rawPasswordStr = (String) rawPassword;
                String encodePassword = "";

                try {
                    encodePassword = EncBySha512(rawPasswordStr);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                return encodedPassword.equals(encodePassword);
            }

        });

    }

    @Bean
    public BCryptPasswordEncoder encoderPw() {
        return new BCryptPasswordEncoder();
    }

    public String EncBySha512(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());

        StringBuilder builder = new StringBuilder();

        for (byte b : md.digest()) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }
}
