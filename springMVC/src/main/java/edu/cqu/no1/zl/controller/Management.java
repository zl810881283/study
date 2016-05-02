package edu.cqu.no1.zl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by zl on 2016/5/2.
 */
@Controller
@RequestMapping("/management")
public class Management {
    @RequestMapping("/image/add")
    public String imageAdd(){

        return "image-add";
    }

    @RequestMapping("/image/{id}")
    public String imageDetail(@PathVariable String id, Map<String,Object> map){
        System.out.println(id);
        map.put("imageId",id);
        return "image-detail";
    }
}
