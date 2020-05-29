package com.ytsakura.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/29 17:09
 * @describe
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("blogs")
    public String blogs(){
        return "admin/blogs";
    }
}
