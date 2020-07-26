package com.cyz.SchoolCourseSelectionSystem;
import com.cyz.SchoolCourseSelectionSystem.entity.Course;
import com.cyz.SchoolCourseSelectionSystem.entity.Student;
import com.cyz.SchoolCourseSelectionSystem.service.AdminService;
import com.cyz.SchoolCourseSelectionSystem.service.StudentService;
import com.cyz.SchoolCourseSelectionSystem.service.impl.AdminServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@SpringBootTest
@Slf4j
class SchoolCourseSelectionSystemApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void getString() {
        List<Course> courses = studentService.queryRebuildCourses(5);
        System.out.println(courses.size());
    }
}
