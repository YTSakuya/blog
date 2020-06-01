package com.ytsakura.blog.service;

import com.ytsakura.blog.pojo.Blog;
import com.ytsakura.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/31 7:18
 * @describe
 */
public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
