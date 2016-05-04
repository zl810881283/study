package edu.cqu.no1.zl.service;

import edu.cqu.no1.zl.dao.ImageDao;
import edu.cqu.no1.zl.dao.SubjectDao;
import edu.cqu.no1.zl.domain.Image;
import edu.cqu.no1.zl.domain.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by zl on 2016/5/3.
 */
@Service
public class SubjectService {
    @Resource
    private ImageDao imageDao;
    @Resource
    private SubjectDao subjectDao;


    public Serializable addSubject(String imageId, String name, Integer centerX, Integer centerY,
                                   Integer offX, Integer offY) {


        Image image = imageDao.get(Image.class, imageId);
        if (image == null) {
            return null;
        }
        Subject subject = new Subject(name, centerX, centerY, offX, offY);
        subject.setImage(image);
        return subjectDao.save(subject);
    }

    public Set<Subject> getSubjectsByImageId(String imageId) {
        return imageDao.get(Image.class, imageId).getSubjects();
    }

    public void removeSubject(String id) {
        subjectDao.delete(Subject.class, id);
    }

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public SubjectDao getSubjectDao() {
        return subjectDao;
    }

    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public List<Subject> getAllSubject() {
        return subjectDao.findAll(Subject.class);
    }
}
