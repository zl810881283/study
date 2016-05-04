package edu.cqu.no1.zl.controller;

import edu.cqu.no1.zl.service.ImageService;
import edu.cqu.no1.zl.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zl on 2016/5/2.
 */
@Controller
@RequestMapping("/management")
public class Management {
    @Resource
    private ImageService imageService;
    @Resource
    private SubjectService subjectService;

    public ImageService getImageService() {
        return imageService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/image/add", method = RequestMethod.GET)
    public String imageAdd() {

        return "image-add";
    }

    @RequestMapping("/image/{id}")
    public String imageDetail(@PathVariable String id, Map<String, Object> map) {

        map.put("imageId", id);
        map.put("subjects", subjectService.getSubjectsByImageId(id));
        return "image-detail";
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String imageList(Map<String, Object> map) {
        map.put("images", imageService.getAllImage());
        return "image-list";
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Object imagePost(HttpServletRequest req) throws IOException {
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) req;
        MultipartFile file = mreq.getFile("image");
        String uploadPath = req.getRealPath("/") + "images/";
        String id = imageService.addImage(file, uploadPath).getId();


        return "redirect:/management/image/" + id;

    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String subjectList(Map<String, Object> map) {
        map.put("subjects", subjectService.getAllSubject());
        return "subject-list";

    }
}
