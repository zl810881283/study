package edu.cqu.no1.zl.domain;

import edu.cqu.no1.zl.utils.Serial;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2016/5/1.
 */
@Entity
public class ValidateRecord {
    public ValidateRecord() {
    }

    public ValidateRecord(byte[] challenge, Date createAt, Date validateAt, Boolean validated) {
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

    @Column(length = 65535)
    private byte[] challenge;
    private Date createAt;
    private Date validateAt;
    private Boolean validated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, List<String>> getChallenge() throws IOException, ClassNotFoundException {
        return new Serial<Map<String, List<String>>>().ByteToObject(this.challenge);
    }

    public void setChallenge(byte[] challenge) {
        this.challenge = challenge;
    }

    public void setChallenge(Map<String, List<String>> map) throws IOException {
        this.challenge = new Serial<Map<String, List<String>>>().objectToByte(map);
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