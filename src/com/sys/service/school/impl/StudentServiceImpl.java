package com.sys.service.school.impl;

import com.sys.bean.school.Student;
import com.sys.service.base.DaoSupport;
import com.sys.service.school.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl extends DaoSupport<Student> implements
        StudentService {

    @Override
    public void register(Student student){
        List<Student> list = findScrollDataNoPager(null," o.username = ? ",new Object[]{student.getUsername()});
        if(list.size()>0){
            throw  new RuntimeException("用户名已被使用");
        }
        save(student);
    }

    @Override
    public boolean checkUserName(String name){
        List<Student> list = findScrollDataNoPager(null," o.username = ? ",new Object[]{name});
        return list.size()==0;
    }

    @Override
    public Student login(String username, String password) {
        List<Student> list = findScrollDataNoPager(null," o.username = ? and o.password=? ",new Object[]{username,password});
        if(list.size()==0){
            throw  new RuntimeException("用户名或密码错误");
        }
        return list.get(0);
    }
}
