package com.ytsakura.blog.dao;

import com.ytsakura.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/29 14:42
 * @describe
 */
public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsernameAndPassword(String username,String password);
}
