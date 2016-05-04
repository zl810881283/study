package edu.cqu.no1.zl.dao;

import edu.cqu.no1.zl.domain.Subject;
import edu.cqu.no1.zl.domain.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by zl on 2016/5/3.
 */
@Repository
@Transactional
public class SubjectDao extends BaseDao<Subject> {

}
