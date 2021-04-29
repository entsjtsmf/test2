package team.ljm.secw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ljm.secw.entity.Student;
import team.ljm.secw.entity.Teacher;
import team.ljm.secw.mapper.LoginMapper;
import team.ljm.secw.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService{

    @Autowired
    LoginMapper loginMapper;

    public Student findStudentByAccount(String account) {
        return loginMapper.selectStudentByAccount(account);
    }

    public Teacher findTeacherByAccount(String account) {
        return loginMapper.selectTeacherByAccount(account);
    }

}
