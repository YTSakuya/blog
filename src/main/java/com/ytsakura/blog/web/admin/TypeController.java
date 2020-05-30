package com.ytsakura.blog.web.admin;

import com.ytsakura.blog.pojo.Type;
import com.ytsakura.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/30 3:22
 * @describe
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")   //PageableDefault指定分页的默认参数
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "/admin/types";
    }

    @GetMapping("/types/add")
    public String add(Model model){
        model.addAttribute("type",new Type()); //将一个空Type放入model，方便前端渲染Type
        return "/admin/types-input";
    }

    @PostMapping("/types/addType")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return "/admin/types-input";
        }
        Type t =typeService.saveType(type);
        if(t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/types";
    }


}
