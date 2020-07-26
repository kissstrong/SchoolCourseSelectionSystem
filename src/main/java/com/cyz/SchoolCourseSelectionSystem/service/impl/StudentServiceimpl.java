package com.cyz.SchoolCourseSelectionSystem.service.impl;

import com.cyz.SchoolCourseSelectionSystem.entity.Course;
import com.cyz.SchoolCourseSelectionSystem.entity.Student;
import com.cyz.SchoolCourseSelectionSystem.mapper.CourseDao;
import com.cyz.SchoolCourseSelectionSystem.mapper.StudentDao;
import com.cyz.SchoolCourseSelectionSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cyz
 * @date 2020-07-21 8:02
 */
@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CourseDao courseDao;
    @Override
    public Student checkLogin(String name, String password) {
       return studentDao.queryStudentByNameAndPassword(name, password);
    }

    @Override
    public Student save(Student student) {
       return studentDao.save(student);
    }

    @Override
    public Student queryByEmail(String email) {
        return studentDao.queryStudentByEmail(email);
    }

    @Override
    public void updatePasswordByEmail(String password, String email) {
        Student student = studentDao.queryStudentByEmail(email);
        Student student1 = student.setPassword(password);
        studentDao.save(student1);
    }

    @Override
    public List<Student> queryAllStudent() {
        return studentDao.findAll();
    }

    @Override
    public Student queryById(Integer id) {
        return studentDao.getOne(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        studentDao.deleteById(id);
        boolean b = studentDao.existsById(id);
        return !b;
    }

    @Override
    public List<Course> queryAllCourse() {
        return courseDao.queryAllByStudentIsNull();
    }
    @Override
    public List<Course> querySelectedCourse(Integer id) {
        return courseDao.queryCoursesByStudent(studentDao.getOne(id));
    }

    @Override
    public Course queryCourseById(Integer id) {
        return courseDao.getOne(id);
    }

    @Override
    public List<Course> queryCourseByStudentId(Integer id) {
        List<Course> courses = courseDao.queryCoursesByStudent(studentDao.getOne(id));
        return courses;
    }

    @Override
    public void saveCourse(Course course) {
        courseDao.save(course);
    }

    @Override
    public Course queryCourseByStudentAndName(Student student, String name) {
        return courseDao.queryCourseByStudentAndName(student,name);
    }

    @Override
    public void deleteCourseById(Integer id) {
        Course one = courseDao.getOne(id);
        courseDao.deleteById(id);
        Course course = courseDao.queryCourseByNameAndStudentIsNull(one.getName());
        course.setUsednum(course.getUsednum()-1);
        courseDao.save(course);
    }

    @Override
    public List<Course> queryRebuildCourses(Integer id) {
        return courseDao.queryCoursesByStudent(studentDao.getOne(id));
    }

    @Override
    public List<Course> queryResultVoidBySid(Integer id) {
        return courseDao.queryCoursesByStudent(studentDao.getOne(id));
    }
}
