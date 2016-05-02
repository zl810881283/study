package edu.cqu.no1.zl.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zl on 2016/4/30.
 */
@Entity
public class Subject {
    public Subject() {
    }

    public Subject(String name, Integer centerX, Integer centerY, Integer offX, Integer offY) {
        this.name = name;
        this.centerX = centerX;
        this.centerY = centerY;
        this.offX = offX;
        this.offY = offY;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(length = 64)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column( nullable = false)
    private Integer centerX;

    @Column( nullable = false)
    private Integer centerY;

    @Column( nullable = false)
    private Integer offX;

    @Column( nullable = false)
    private Integer offY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_type_id")
    private SubjectType subjectType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCenterX() {
        return centerX;
    }

    public void setCenterX(Integer centerX) {
        this.centerX = centerX;
    }

    public Integer getCenterY() {
        return centerY;
    }

    public void setCenterY(Integer centerY) {
        this.centerY = centerY;
    }

    public Integer getOffX() {
        return offX;
    }

    public void setOffX(Integer offX) {
        this.offX = offX;
    }

    public Integer getOffY() {
        return offY;
    }

    public void setOffY(Integer offY) {
        this.offY = offY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }
}
