package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {
    @RequestMapping("/indexPageShow")
    public ModelAndView indexPageShow(ModelAndView mvo, HttpSession httpSession,String productname){
        System.out.println("-----test:");
        httpSession.setAttribute("indexPageShow","indexPageShow124");
        //mvo.addObject("indexPageShow","indexPageShow123");
        mvo.setViewName("index");
        return mvo;
    }
}
