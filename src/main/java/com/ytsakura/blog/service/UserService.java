package com.ytsakura.blog.service;

import com.ytsakura.blog.pojo.User;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/29 14:38
 * @describe
 */
public interface UserService {

    User checkUser(String username,String password);
}
