package edu.cqu.no1.zl.controller;


import edu.cqu.no1.zl.domain.Image;
import edu.cqu.no1.zl.entities.Person;
import edu.cqu.no1.zl.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zl on 2016/4/29.
 */
@Controller
@RequestMapping("/user")
public class User {

    @Resource
    private ImageService imageService;

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public ImageService getImageService() {
        return imageService;
    }

    @RequestMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password,
            Map<String, Object> map) {

        System.out.println(new Person(username, password));
        map.put("person", new Person(username, password));
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/json")
    public Person json(
            Model model) {

        //model.addAttribute("name", person.getUsername());

        return new Person("dsaf", "asdfasd");
    }

    @RequestMapping("/toPerson")
    public String toPerson(
            Person person,
            Map<String, Object> map,HttpServletRequest req,
            Model model) {
        //System.out.println(person);
        System.out.println(req.getRealPath("/"));
        map.put("person", person);
        //System.out.println(getImageService().addImage(new Image("test.jpg")));
        return "hello";
    }

}
