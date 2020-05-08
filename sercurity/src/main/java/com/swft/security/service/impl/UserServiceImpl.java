package com.swft.security.service.impl;

import com.swft.security.pojo.entity.User;
import com.swft.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author JiangTeJie
 * @since 2020/5/7 17:32
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(log.isDebugEnabled()){
            log.debug("用户({})开始认证",s);
        }
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setAuthorities(null);
        return user;
    }
}
