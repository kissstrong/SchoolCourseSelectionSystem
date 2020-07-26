package com.cyz.SchoolCourseSelectionSystem.entity;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author cyz
 * @date 2020-07-20 16:42
 */
@Entity
@Accessors(chain = true)
@Data
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String bonusItem;
    private Integer bonusGrade;
    private boolean isRebuild;
    private Integer grade;
    private Integer totalnum;
    private Integer usednum;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    private List<Student> students=new ArrayList<>();
    @ManyToOne
    private Student student;

}
