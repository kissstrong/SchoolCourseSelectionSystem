package com.cyz.SchoolCourseSelectionSystem.controller;
import com.cyz.SchoolCourseSelectionSystem.entity.*;
import com.cyz.SchoolCourseSelectionSystem.entity.Class;
import com.cyz.SchoolCourseSelectionSystem.service.AdminService;
import com.cyz.SchoolCourseSelectionSystem.service.ClassService;
import com.cyz.SchoolCourseSelectionSystem.service.StudentService;
import com.cyz.SchoolCourseSelectionSystem.service.TeacherService;
import com.cyz.SchoolCourseSelectionSystem.vo.Addr;
import com.cyz.SchoolCourseSelectionSystem.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author cyz
 * @date 2020-07-20 18:18
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassService classService;

    @RequestMapping("/queryAllStudent")
    public String queryAllStudent(Model model){
        model.addAttribute("allStudent",studentService.queryAllStudent());
        return "admin/queryAllStudent";
    }
    @RequestMapping("/toaddStudent")
    public String toaddStudent(Model model){
        model.addAttribute("Classes",classService.findAll());
        return "admin/addStudent";
    }
    @RequestMapping("/addStudent")
    public String addStudent(RegisterVo registerVo, Addr addr){
        String address=addr.getSheng()+"_"+addr.getShi()+"_"+addr.getQu();
        studentService.save(new Student().setName(registerVo.getName()).setPassword(registerVo.getPassword())
        .setEmail(registerVo.getEmail()).setGender(registerVo.getGender()).setLocation(address)
        .setAClass(classService.queryById(registerVo.getClassid())));
        return "redirect:/queryAllStudent";
    }
    @RequestMapping("/toupdatestudent")
    public String updatestudent(Integer id,Model model){
        Student student = studentService.queryById(id);
        String location = student.getLocation();
        String[] s = location.split("_");
        Addr addr=new Addr();
        addr.setSheng(s[0]).setShi(s[1]).setQu(s[2]);
        model.addAttribute("student",student);
        model.addAttribute("addr",addr);
        model.addAttribute("Classes",classService.findAll());
        return "admin/updateStudent";
    }
    @RequestMapping("/updateStudent")
    public String updateStudent(RegisterVo registerVo,Addr addr){
        Student student = studentService.queryById(registerVo.getId());
        String location=addr.getSheng()+"_"+addr.getShi()+"_"+addr.getQu();
        student.setName(registerVo.getName()).setAClass(classService.queryById(registerVo.getClassid()))
                .setGender(registerVo.getGender()).setEmail(registerVo.getEmail()).setLocation(location);
        studentService.save(student);
        return "redirect:/queryAllStudent";
    }

    @ResponseBody
    @RequestMapping("/deletestudent")
    public String deletestudent(Integer id){
        boolean b = studentService.deleteById(id);
        if (b){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/resetstudent")
    public String resetstudent(Integer id){
        Student student = studentService.queryById(id);
        Student student1 = student.setPassword("123456");
        studentService.save(student1);
        return "success";
    }


    @RequestMapping("/queryAllTeacher")
    public String queryAllTeacher(Model model){
        model.addAttribute("allTeacher",teacherService.queryAllTeacher());
        return "admin/queryAllTeacher";
    }

    @RequestMapping("/toaddTeacher")
    public String toaddTeacher(Model model){
        model.addAttribute("Classes",classService.findAll());
        return "admin/addTeacher";
    }
    @RequestMapping("/addTeacher")
    public String addTeacher(RegisterVo registerVo, Addr addr){
        String address=addr.getSheng()+"_"+addr.getShi()+"_"+addr.getQu();
        teacherService.save(new Teacher().setName(registerVo.getName()).setPassword(registerVo.getPassword())
                .setEmail(registerVo.getEmail()).setGender(registerVo.getGender()).setLocation(address)
                .setAClass(classService.queryById(registerVo.getClassid())));
        return "redirect:/queryAllTeacher";
    }

    @RequestMapping("/toupdateteacher")
    public String toupdateteacher(Integer id,Model model){
        Teacher teacher = teacherService.queryById(id);
        String location = teacher.getLocation();
        String[] s = location.split("_");
        Addr addr=new Addr();
        addr.setSheng(s[0]).setShi(s[1]).setQu(s[2]);
        model.addAttribute("teacher",teacher);
        model.addAttribute("addr",addr);
        model.addAttribute("Classes",classService.findAll());
        return "admin/updateTeacher";
    }

    @RequestMapping("/updateTeacher")
    public String updateTeacher(RegisterVo registerVo,Addr addr){
        Teacher teacher = teacherService.queryById(registerVo.getId());
        String location=addr.getSheng()+"_"+addr.getShi()+"_"+addr.getQu();
        teacher.setName(registerVo.getName()).setAClass(classService.queryById(registerVo.getClassid()))
                .setGender(registerVo.getGender()).setEmail(registerVo.getEmail()).setLocation(location);
       teacherService.save(teacher);
        return "redirect:/queryAllTeacher";
    }

    @ResponseBody
    @RequestMapping("/deleteteacher")
    public String deleteteacher(Integer id){
        boolean b = teacherService.deleteById(id);
        if (b){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/resetteacher")
    public String resetteacher(Integer id){
        Teacher teacher = teacherService.queryById(id);
        teacher.setPassword("123456");
        teacherService.save(teacher);
        return "success";
    }

    @RequestMapping("/queryAllClass")
    public String queryAllClass(Model model){
        model.addAttribute("classes",classService.findAll());
        return "admin/queryAllClass";
    }

   @ResponseBody
   @RequestMapping("/checkclassrepeat")
   public String checkclassrepeat(String name,String dept){
       Class aClass = classService.queryClassByDeptAndName(dept, name);
       if (aClass==null){
           return "success";
       }
       return "error";
   }

    @RequestMapping("/addclass")
    public String addclass(Class aclass,Model model){
        classService.save(aclass);
        model.addAttribute("classes",classService.findAll());
        return "admin/queryAllClass::tbody";
    }

    @ResponseBody
    @RequestMapping("/deleteclass")
    public String deletecourse(Integer id){
        boolean b = classService.deleteById(id);
        System.out.println(b);
        if (b){
            return "success";
        }else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("/queryclassById")
    public Class queryclassById(Integer id){
        return classService.queryById(id);
    }

    @ResponseBody
    @RequestMapping("/updateclass")
    public String updateclass(Class a){
        classService.save(a);
        return "success";
    }

    @RequestMapping("/queryadminself")
    public String queryadminself(Integer id,Model model){
        model.addAttribute("admin",adminService.queryById(id));
        System.out.println(adminService.queryById(id));
        return "admin/queryself";
    }
    @RequestMapping("/toupdateadminself")
    public String toupdateadminself(Integer id,Model model){
        Admin admin = adminService.queryById(id);
        Addr addr=new Addr();
        String[] s = admin.getLocation().split("_");
        addr.setSheng(s[0]).setShi(s[1]).setQu(s[2]);
        model.addAttribute("admin",admin);
        model.addAttribute("addr",addr);
        return "admin/updateself";
    }

    @RequestMapping("/updateadmin")
    public String updateadmin(RegisterVo registerVo,Addr addr){
        Admin admin=adminService.queryById(registerVo.getId());
        admin.setName(registerVo.getName()).setLocation(addr.getSheng()+"_"+addr.getShi()+"_"+addr.getQu())
                .setGender(registerVo.getGender()).setEmail(registerVo.getEmail());
        adminService.save(admin);
        return "redirect:/queryadminself?id="+admin.getId();
    }

    @RequestMapping("/toupdatepassword")
    public String toupdatepassword(){
        return "admin/updatepassword";
    }
    @ResponseBody
    @RequestMapping("/adminupdatepassword")
    public String adminupdatepassword(String password, Integer id){
        Admin admin1 = adminService.queryById(id);
        admin1.setPassword(password);
        adminService.save(admin1);
        return "success";
    }
}
