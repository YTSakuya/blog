package com.ytsakura.blog.dao;

import com.ytsakura.blog.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/31 7:21
 * @describe
 */
public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

}
