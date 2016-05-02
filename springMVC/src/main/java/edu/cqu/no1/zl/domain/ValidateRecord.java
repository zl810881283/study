package edu.cqu.no1.zl.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DateTimeException;
import java.util.Date;

/**
 * Created by zl on 2016/5/1.
 */
@Entity
public class ValidateRecord {
    public ValidateRecord() {
    }

    public ValidateRecord(String challenge, Date createAt, Date validateAt, Boolean validated) {
        this.challenge = challenge;
        this.createAt = createAt;
        this.validateAt = validateAt;
        this.validated = validated;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(length = 64)
    private String id;

    private String challenge;
    private Date createAt;
    private Date validateAt;
    private Boolean validated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getValidateAt() {
        return validateAt;
    }

    public void setValidateAt(Date validateAt) {
        this.validateAt = validateAt;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}