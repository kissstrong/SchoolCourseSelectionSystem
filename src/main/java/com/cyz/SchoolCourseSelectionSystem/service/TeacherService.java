package com.cyz.SchoolCourseSelectionSystem.service;

import com.cyz.SchoolCourseSelectionSystem.entity.Course;
import com.cyz.SchoolCourseSelectionSystem.entity.Teacher;

import java.util.List;

/**
 * @author cyz
 * @date 2020-07-21 7:59
 */
public interface TeacherService {
     Teacher checkLogin(String name, String password);
     Teacher save(Teacher teacher);
    Teacher queryByEmail(String email);
    void updatePasswordByEmail(String password,String email);
    List<Teacher> queryAllTeacher();
    boolean deleteById(Integer id);
    Teacher queryById(Integer id);
    List<Course> queryAllByTeacher(Teacher teacher);
    Course queryCourseByName(String name);
    void saveCourse(Course course);
    void deleteCourse(Integer id);
    List<Course> queryCoursesByTeacherAndStudentExistsOrderByName(Teacher teacher);
    Course queryCourseByCid(Integer cid);
    List<Course> queryrebuildStudent(Integer id);
    List<Course> queryCoursesByTeacherAndBonusItemIsNotNullAndBonusGradeIsNull(Teacher teacher);

    void updatecourse(Integer id, String name);
}
