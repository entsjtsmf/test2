package team.ljm.secw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ljm.secw.dto.LoginDTO;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;
import team.ljm.secw.service.ILoginService;
import team.ljm.secw.vo.ResponseVO;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    ILoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        System.out.println(loginDTO.toString());
        try {
            if (loginDTO.getType().equals("0")) {
                Student student = loginService.findStudentByAccount(loginDTO.getAccount());
                if (student != null) {
                    if (loginDTO.getPassword().equals(student.getPwd())) {
                        session.setAttribute("account", student.getAccount());
                        session.setAttribute("name", student.getStudentName());
                        session.setAttribute("clazzId", student.getClazzId());
                        return new ResponseVO("200", "登录成功");
                    } else {
                        return new ResponseVO("401", "密码错误，请重新输入！");
                    }
                } else {
                    return new ResponseVO("401", "不存在该账号！");
                }
            } else {
                Teacher teacher = loginService.findTeacherByAccount(loginDTO.getAccount());
                if (teacher != null) {
                    if (loginDTO.getPassword().equals(teacher.getPwd())) {
                        session.setAttribute("id", teacher.getId());
                        session.setAttribute("account", teacher.getAccount());
                        session.setAttribute("name", teacher.getTeacherName());
                        return new ResponseVO("200", "登录成功！");
                    } else {
                        return new ResponseVO("401", "密码错误，请重新输入！");
                    }
                } else {
                    return new ResponseVO("401", "不存在该账号！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVO("500", "Error");
        }
    }

}
