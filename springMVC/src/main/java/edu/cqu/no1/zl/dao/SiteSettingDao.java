package edu.cqu.no1.zl.dao;

import edu.cqu.no1.zl.domain.SiteSetting;
import edu.cqu.no1.zl.utils.Serial;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zl on 2016/5/1.
 */
@Repository
@Transactional
public class SiteSettingDao extends BaseDao<SiteSetting> {

    public static final String VALIDATED_IMAGE_COUNT = "ValidatedImageCount";
    public static final String UNCERTAINTY_IMAGE_COUNT = "UncertaintyImageCount";


    public static final Integer VALIDATED_IMAGE_COUNT_DEFAULT_VALUE = 5;
    public static final Integer UNCERTAINTY_IMAGE_COUNT_DEFAULT_VALUE = 5;

    @Resource
    private Serial<Object> serial;

    public Serial<Object> getSerial() {
        return serial;
    }

    public void setSerial(Serial<Object> serial) {
        this.serial = serial;
    }


    public void setProperty(String key, Object value) {
        try {
            byte[] bytes = serial.objectToByte(value);
            SiteSetting siteSetting = new SiteSetting(key, bytes);
            save(siteSetting);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    public Object getProperty(String key) {
        SiteSetting siteSetting = get(SiteSetting.class, key);

        if (siteSetting == null) {
            switch (key) {
                case VALIDATED_IMAGE_COUNT:
                    return VALIDATED_IMAGE_COUNT_DEFAULT_VALUE;
                case UNCERTAINTY_IMAGE_COUNT:
                    return UNCERTAINTY_IMAGE_COUNT_DEFAULT_VALUE;
                default:
                    return null;
            }
        }
        Object obj = null;
        try {
            obj = serial.ByteToObject(siteSetting.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
