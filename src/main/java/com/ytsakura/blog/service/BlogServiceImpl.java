package com.ytsakura.blog.service;

import com.ytsakura.blog.dao.BlogRepository;
import com.ytsakura.blog.handler.NotFoundException;
import com.ytsakura.blog.pojo.Blog;
import com.ytsakura.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/31 7:20
 * @describe
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates =new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle() != null){//判断查询条件之一:根据博客标题查询
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId()!= null){//判断查询条件之二:根据博客分类查询
                    predicates.add(cb.equal(root.<Integer>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){//判断查询条件之三:根据博客是否被推荐查询
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                if(blog.isPublished()){   //判断查询条件之四:根据博客是否已发布查询，过滤掉状态为草稿的博客
                    predicates.add(cb.equal(root.<Boolean>get("published"),blog.isPublished()));
                }
                if(blog.isDraft()){   //判断查询条件之五:根据博客是否是草稿，实现后台查询草稿博客功能
                    predicates.add(cb.equal(root.<Boolean>get("published"),!blog.isDraft()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id,Blog blog) {
        Blog b =blogRepository.findById(id).orElse(null);
        if(b == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,b);
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }


}
