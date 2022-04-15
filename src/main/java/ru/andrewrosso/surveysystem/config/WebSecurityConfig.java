package ru.andrewrosso.surveysystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/surveys/**").permitAll()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(myPasswordEncoder())
//                .withUser("user")
//                .password(myPasswordEncoder().encode("1234"))
//                .roles("USER");
        auth.inMemoryAuthentication()
                .passwordEncoder(myPasswordEncoder())
                .withUser("admin")
                .password(myPasswordEncoder().encode("admin1234"))
                .roles("ADMIN");
    }

    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
