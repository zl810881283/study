package edu.cqu.no1.zl.controller;

import edu.cqu.no1.zl.service.ImageService;
import edu.cqu.no1.zl.service.ValidateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by zl on 2016/5/1.
 */
@Controller
@RequestMapping("/captcha")
public class CAPTCHA {
    @Resource
    private ImageService imageService;
    @Resource
    private ValidateService validateService;

    @RequestMapping("/create")
    public Object create() {
        return null;
    }

    @RequestMapping("/validate")
    public Object validate() {
        return null;
    }
}
