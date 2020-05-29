package com.ytsakura.blog.handler;

import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/28 2:43
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final org.slf4j.Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null){
            throw e;
        }

        ModelAndView mv =new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
