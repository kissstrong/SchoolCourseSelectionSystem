package com.cyz.SchoolCourseSelectionSystem.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cyz
 * @date 2020-07-20 16:35
 */
@Entity
@Table
@Accessors(chain = true)
@Data
public class Admin {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String gender;
    private String password;
    private String location;
    private String email;
}
