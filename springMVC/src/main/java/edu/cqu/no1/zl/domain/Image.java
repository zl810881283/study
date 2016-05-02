package edu.cqu.no1.zl.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.*;
import java.util.Set;

/**
 * Created by zl on 2016/4/30.
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "fileName")})
public class Image {
    public Image() {
    }

    public Image(String fileName) {
        this.fileName = fileName;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(length = 64)
    private String id;

    @Column(unique = true, nullable = false)
    private String fileName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "image")
    private Set<Subject> subjects;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
