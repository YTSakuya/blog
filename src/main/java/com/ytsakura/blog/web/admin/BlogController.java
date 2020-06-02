package com.ytsakura.blog.web.admin;

import com.ytsakura.blog.pojo.Blog;
import com.ytsakura.blog.pojo.User;
import com.ytsakura.blog.service.BlogService;
import com.ytsakura.blog.service.TagService;
import com.ytsakura.blog.service.TypeService;
import com.ytsakura.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/29 17:09
 * @describe
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String ADD="admin/blogs-input";
    private static final String LIST="admin/blogs";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/add")
    public String add(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return ADD;
    }

    @GetMapping("/blogs/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        setTypeAndTag(model);
        Blog blog=blogService.getBlog(id);
        blog.init(); //初始化，将标签数组转换为字符串
        model.addAttribute("blog",blog);
        return ADD;
    }

    @PostMapping("/blogs/addBlog")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if(blog.getId() == null){
            b =blogService.saveBlog(blog);
        }else{
            b =blogService.updateBlog(blog.getId(),blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    private String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType()); //获取所有分类
        model.addAttribute("tags",tagService.listTag());  //获取所有标签
    }
}
