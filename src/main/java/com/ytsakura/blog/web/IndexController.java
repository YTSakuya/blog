package com.ytsakura.blog.web;

import com.ytsakura.blog.handler.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/28 2:37
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int i=9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("博客不存在");
//        }
//
        return "index";
    }
}
