package com.ytsakura.blog.service;

import com.ytsakura.blog.dao.UserRepository;
import com.ytsakura.blog.pojo.User;
import com.ytsakura.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/29 14:40
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
