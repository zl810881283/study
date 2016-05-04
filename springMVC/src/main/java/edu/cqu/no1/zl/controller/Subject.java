package edu.cqu.no1.zl.controller;

import edu.cqu.no1.zl.dao.SubjectDao;
import edu.cqu.no1.zl.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zl on 2016/5/3.
 */
@Controller
@RequestMapping("/rest")
public class Subject {
    @Resource
    private SubjectService subjectService;

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }




    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    @ResponseBody
    public Object post(String imageId, String name, Integer centerX, Integer centerY,
                       Integer offX, Integer offY) {
        System.out.println(imageId);
        return subjectService.addSubject(imageId,name,centerX,centerY,offX,offY);

    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable String id) {
        System.out.println(id);
        subjectService.removeSubject(id);
    }


}
