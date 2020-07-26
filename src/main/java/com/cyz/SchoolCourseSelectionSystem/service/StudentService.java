package com.cyz.SchoolCourseSelectionSystem.service;

import com.cyz.SchoolCourseSelectionSystem.entity.Course;
import com.cyz.SchoolCourseSelectionSystem.entity.Student;
import java.util.List;
/**
 * @author cyz
 * @date 2020-07-21 7:59
 */
public interface StudentService {
    public Student checkLogin(String name, String password);
    public Student save(Student student);
    Student queryByEmail(String email);
    void updatePasswordByEmail(String password,String email);
    List<Student> queryAllStudent();
    Student queryById(Integer id);
    boolean deleteById(Integer id);
    List<Course> queryAllCourse();
    List<Course> querySelectedCourse(Integer id);
    Course queryCourseById(Integer id);
    List<Course> queryCourseByStudentId(Integer id);
    void saveCourse(Course course);
    Course queryCourseByStudentAndName(Student student,String name);
    void deleteCourseById(Integer id);
    List<Course> queryRebuildCourses(Integer id);
    List<Course> queryResultVoidBySid(Integer id);
}
