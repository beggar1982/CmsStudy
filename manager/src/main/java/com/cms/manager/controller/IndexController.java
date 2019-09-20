package com.cms.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xianfu.xia
 * @since 2018/12/10
 */
@Controller
public class IndexController {

    @RequestMapping("/manager")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

}
