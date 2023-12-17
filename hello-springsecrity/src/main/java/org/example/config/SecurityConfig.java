package org.example.config;

import org.example.handle.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private MyPermissionEvaluator myPermissionEvaluator;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单提交
        http.formLogin()
                //自定义用户名和密码参数
                .usernameParameter("username123")
                .passwordParameter("password123")
                //自定义登录页面
                .loginPage("/login.html")
                //必须和表单提交的接口一样，执行自定义登录逻辑
                .loginProcessingUrl("/login")
                //登录成功跳转的页面，post请求
                .successForwardUrl("/toMain")
                //自定义登录失败处理器
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
        //授权
        http.authorizeRequests()
                //放行/login.html,不需要认证
                .antMatchers("/login.html").permitAll()
                //放行/error.html，不需要认证
                .antMatchers("/error.html").permitAll()
                .expressionHandler(defaultWebSecurityExpressionHandler())
                //所有请求必须认证
                .anyRequest().authenticated();

        //异常处理器
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        //登出
        http.logout()
                //登出接口,与表单访问接口一致
                .logoutUrl("/signLogout")
                //登出处理器
//                .addLogoutHandler(new MyLogoutHandler())
                //登出成功后跳转的页面
//                .logoutSuccessHandler(new MyLogoutSuccessHandler("/login.html"));
        ;

        //关闭csrf防护
        http.csrf().disable();
    }

    @Bean
    public SecurityExpressionHandler<FilterInvocation> defaultWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setPermissionEvaluator(myPermissionEvaluator);
        return defaultWebSecurityExpressionHandler;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**")
                .antMatchers("/**/*.png");
    }
}
