package com.ytsakura.blog.web.admin;

import com.ytsakura.blog.pojo.Tag;
import com.ytsakura.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/31 6:26
 * @describe
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                       Pageable pageable,Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/add")
    public String add(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags/addTag")
    public String addTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag t=tagService.getTagByName(tag.getName());
        if(t !=null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t1 = tagService.saveTag(tag);
        if(t1 == null){
            attributes.addFlashAttribute("message","新增标签失败");
        }else{
            attributes.addFlashAttribute("message","新增标签成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/editTag/{id}")
    public String editTag(@Valid Tag tag, BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Tag t=tagService.getTagByName(tag.getName());
        if(t !=null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t1 = tagService.saveTag(tag);
        if(t1 == null){
            attributes.addFlashAttribute("message","修改标签失败");
        }else{
            attributes.addFlashAttribute("message","修改标签成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
