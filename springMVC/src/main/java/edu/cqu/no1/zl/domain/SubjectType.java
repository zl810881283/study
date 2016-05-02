package edu.cqu.no1.zl.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.*;
import java.util.Set;

/**
 * Created by zl on 2016/4/30.
 */

@Entity
public class SubjectType {
    public SubjectType() {
    }

    public SubjectType(String name) {
        this.name = name;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(length = 64)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjectType")
    private Set<Subject> subjects;

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
