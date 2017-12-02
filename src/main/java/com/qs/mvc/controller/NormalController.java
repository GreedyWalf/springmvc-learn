package com.qs.mvc.controller;

import com.qs.mvc.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class NormalController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/normal")
    public String testPage(Model model){
        model.addAttribute("msg", demoService.saySomething());
        return "page";
    }



}
