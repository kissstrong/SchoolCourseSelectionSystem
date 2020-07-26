package com.cyz.SchoolCourseSelectionSystem.mapper;

import com.cyz.SchoolCourseSelectionSystem.entity.Class;
import com.cyz.SchoolCourseSelectionSystem.entity.Course;
import com.cyz.SchoolCourseSelectionSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author cyz
 * @date 2020-07-20 18:17
 */
public interface StudentDao extends JpaRepository<Student,Integer> {
    Student queryStudentByNameAndPassword(String name,String password);
    Student queryStudentByEmail(String email);
    List<Student> queryStudentsByCourse(Course course);
}
