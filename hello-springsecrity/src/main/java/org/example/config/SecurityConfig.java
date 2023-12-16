package org.example.config;

import org.example.handle.MyAccessDeniedHandler;
import org.example.handle.MyAuthenticationFailureHandler;
import org.example.handle.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .usernameParameter("username123")
                .passwordParameter("password123")
                .loginPage("/login.html") //坑 必须带html后缀
                .loginProcessingUrl("/login") //form表单的请求路径
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));

        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/main1.html").hasAnyAuthority("permission")
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**")
                .antMatchers("/**/*.png");
    }
}
