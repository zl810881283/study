package edu.cqu.no1.zl.domain;

import edu.cqu.no1.zl.utils.Serial;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;

/**
 * Created by zl on 2016/5/1.
 */
@Entity
public class SiteSetting {
    public SiteSetting() {
    }

    public SiteSetting(String key, byte[] value) {
        this.key = key;
        this.value = value;
    }

    public SiteSetting(String key, String value) {
        this.key = key;
        try {
            this.value = new Serial<String>().objectToByte(value);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(length = 64)
    private String id;

    @Column(name="`key`",nullable = false, unique = true)
    private String key;

    @Column(name="`value`",length = 65535)
    private byte[] value;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}
