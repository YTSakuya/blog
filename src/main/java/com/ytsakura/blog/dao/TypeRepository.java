package com.ytsakura.blog.dao;

import com.ytsakura.blog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/30 2:46
 * @describe
 */
@Service
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
