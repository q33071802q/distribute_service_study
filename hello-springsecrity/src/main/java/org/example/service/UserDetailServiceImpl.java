package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 自定义登录逻辑
 */
@Service
@Lazy
public class UserDetailServiceImpl implements UserDetailsService {
    private static BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserDetailServiceImpl.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行自定义登录逻辑");
        String password = bCryptPasswordEncoder.encode("password123");
//        String password = "123";
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("permission"));
    }
}
