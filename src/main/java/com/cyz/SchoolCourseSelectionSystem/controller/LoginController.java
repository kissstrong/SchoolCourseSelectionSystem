package com.cyz.SchoolCourseSelectionSystem.controller;
import com.cyz.SchoolCourseSelectionSystem.entity.Admin;
import com.cyz.SchoolCourseSelectionSystem.entity.Student;
import com.cyz.SchoolCourseSelectionSystem.entity.Teacher;
import com.cyz.SchoolCourseSelectionSystem.service.AdminService;
import com.cyz.SchoolCourseSelectionSystem.service.ClassService;
import com.cyz.SchoolCourseSelectionSystem.service.StudentService;
import com.cyz.SchoolCourseSelectionSystem.service.TeacherService;
import com.cyz.SchoolCourseSelectionSystem.service.impl.QQCheckCodeImpl;
import com.cyz.SchoolCourseSelectionSystem.vo.Addr;
import com.cyz.SchoolCourseSelectionSystem.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
/**
 * @author cyz
 * @date 2020-07-21 9:43
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private QQCheckCodeImpl qqCheckCode;
    @Autowired
    private ClassService classService;

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name, String password, String role, Model model, HttpSession session){
        if (role.equals("admin")){
            Admin admin = adminService.checkLogin(name, password);
            if (admin!=null){
                session.setAttribute("user",admin);
                return "admin/index";
            }else {
                model.addAttribute("loginmsg","用户名或密码错误");
                return "login";
            }
        }else if (role.equals("teacher")){
            Teacher teacher = teacherService.checkLogin(name, password);
            if (teacher!=null){
                session.setAttribute("user",teacher);
                return "teacher/index";
            }else {
                model.addAttribute("loginmsg","用户名或密码错误");
                return "login";
            }
        }else{
            Student student = studentService.checkLogin(name, password);
            if (student!=null){
                session.setAttribute("user",student);
                return "student/index";
            }else{
                model.addAttribute("loginmsg","用户名或密码错误");
                return "login";
            }
        }
    }

    @RequestMapping("/toregister")
    public String toregister(Model model){
        model.addAttribute("Classes",classService.findAll());
        return "register";
    }

    @ResponseBody
    @RequestMapping("/checkemail")
    public String checkemail(String email){
        Teacher teacher = teacherService.queryByEmail(email);
        Student student = studentService.queryByEmail(email);
        Admin admin = adminService.queryByEmail(email);
        if (teacher==null&&student==null&&admin==null){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/sendcheckCode")
    public String sendcheckCode(String qq){
        String msg = qqCheckCode.createNum(qq);
        if (msg==null){
            return "success";
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("/checkcode")
    public String checkcode(String qq,String code){
        String msg = qqCheckCode.checkNum(qq, code);
        return msg;
    }

    @RequestMapping("/register")
    public String register(RegisterVo registerVo,Addr addr){
        String role = registerVo.getRole();
        String address =addr.getSheng()+"_"+addr.getShi()+"_"+addr.getQu();
        if (role.equals("teacher")){
            teacherService.save(new Teacher().setName(registerVo.getName()).setGender(registerVo.getGender())
                    .setEmail(registerVo.getEmail()).setLocation(address).setPassword(registerVo.getPassword())
                    .setAClass(classService.queryById(registerVo.getClassid())));
        }else {
            studentService.save(new Student().setName(registerVo.getName()).setGender(registerVo.getGender())
                    .setEmail(registerVo.getEmail()).setLocation(address).setPassword(registerVo.getPassword())
                    .setAClass(classService.queryById(registerVo.getClassid())));
        }
        return "login";
    }
    @RequestMapping("/toreset")
    public String toreset(){
        return "reset";
    }

    @ResponseBody
    @RequestMapping("/existemail")
    public String existemail(String email){
        Teacher teacher = teacherService.queryByEmail(email);
        Student student = studentService.queryByEmail(email);
        Admin admin = adminService.queryByEmail(email);
        if (teacher!=null||student!=null||admin!=null){
            return "success";
        }
        return "error";
    }
    @RequestMapping("/reset")
    public String reset(){
        return "reset";
    }
    @RequestMapping("/resetpassword")
    public String resetpassword(String email,String password){
        Teacher teacher = teacherService.queryByEmail(email);
        Student student = studentService.queryByEmail(email);
        if (teacher!=null){
            teacherService.updatePasswordByEmail(password,email);
        }else if (student!=null){
            studentService.updatePasswordByEmail(password,email);
        }else {
            adminService.updatePasswordByEmail(password,email);
        }
        return "redirect:/logout";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/tologin";
    }
}
