package org.example.config;

import org.example.service.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .usernameParameter("username123")
                .passwordParameter("password123")
                .loginPage("/login.html") //坑 必须带html后缀
                .loginProcessingUrl("/login")
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"));

        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/main.html").permitAll()
                .anyRequest().authenticated();

//        http.exceptionHandling().accessDeniedHandler()

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/js/**")
                .antMatchers("/**/*.png");
    }
}
