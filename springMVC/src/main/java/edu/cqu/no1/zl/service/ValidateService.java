package edu.cqu.no1.zl.service;

import edu.cqu.no1.zl.dao.ValidateDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zl on 2016/5/1.
 */
@Service
public class ValidateService {
    @Resource
    private ValidateDao validateDao;

    public ValidateDao getValidateDao() {
        return validateDao;
    }

    public void setValidateDao(ValidateDao validateDao) {
        this.validateDao = validateDao;
    }
}
