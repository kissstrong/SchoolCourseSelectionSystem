package com.cyz.SchoolCourseSelectionSystem.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cyz
 * @date 2020-07-20 16:41
 */

@Entity
@Accessors(chain = true)
@Data
public class Class {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String dept;
    @OneToMany(mappedBy = "aClass")
    private List<Student> students=new ArrayList<>();
    @OneToMany(mappedBy = "aClass")
    private List<Teacher> teachers=new ArrayList<>();
}
