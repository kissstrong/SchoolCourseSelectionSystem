package com.cyz.SchoolCourseSelectionSystem.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cyz
 * @date 2020-07-20 16:38
 */
@Entity
@Accessors(chain = true)
@Data
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String gender;
    private String password;
    private String email;
    private String location;
    @ManyToOne
    private Class aClass;

    @OneToMany(mappedBy = "student")
    private List<Course> courses=new ArrayList<>();
    @ManyToOne
    private Course course;
}
