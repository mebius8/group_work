package com.swft.security.util;

import com.swft.security.pojo.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author JiangTeJie
 * @since 2020/5/7 17:37
 */
public class UserUtil {

    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
