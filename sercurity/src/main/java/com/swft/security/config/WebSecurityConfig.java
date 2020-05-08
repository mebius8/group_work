package com.swft.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swft.security.pojo.vo.ResultVO;
import com.swft.security.service.UserService;
import com.swft.security.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;

/**
 * @author JiangTeJie
 * @since 2020/5/7 17:12
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(new MyDefaultPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/auth/list").hasAuthority("USER:LIST")
                .antMatchers("/user/auth/add").hasAuthority("USER:ADD")
                .antMatchers("/user/auth/delete").hasAuthority("USER:DELETE")
                .antMatchers("/user/auth/update").hasAuthority("USER:UPDATE")
                .antMatchers("/layui/**","/image/**","/login","/logout")
                .permitAll()
                .antMatchers("/**")
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/form")
                .successHandler((request, response, ex)->{
                    if(log.isDebugEnabled()){
                        log.debug("认证成功({})", UserUtil.getCurrentUser());
                    }
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/index");
                })
                .failureHandler((request, response, ex)->{
                    if(log.isDebugEnabled()){
                        log.debug("认证失败({})", ex.getMessage());
                    }
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/login");
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .csrf()
                .disable(); // 关闭跨站请求伪造功能
    }

    //这里采用内部类的方式
    class MyDefaultPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return s.equals(charSequence.toString());
        }
    }
}
