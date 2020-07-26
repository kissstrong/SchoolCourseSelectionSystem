package com.cyz.SchoolCourseSelectionSystem.service.impl;
import com.cyz.SchoolCourseSelectionSystem.entity.Admin;
import com.cyz.SchoolCourseSelectionSystem.mapper.AdminDao;
import com.cyz.SchoolCourseSelectionSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cyz
 * @date 2020-07-21 8:02
 */
@Service
public class AdminServiceimpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin checkLogin(String name,String password) {
        return adminDao.queryAdminByNameAndPassword(name,password);
    }
    @Override
    public Admin queryByEmail(String email) {
        return adminDao.queryAdminByEmail(email);
    }

    @Override
    public void updatePasswordByEmail(String password, String email) {
        Admin admin = adminDao.queryAdminByEmail(email);
        Admin admin1 = admin.setPassword(password);
        adminDao.save(admin1);
    }

    @Override
    public Admin queryById(Integer id) {
        return adminDao.getOne(id);
    }

    @Override
    public void save(Admin admin) {
        adminDao.save(admin);
    }


}
