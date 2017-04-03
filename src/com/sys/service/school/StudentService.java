package com.sys.service.school;

import com.sys.bean.school.Student;
import com.sys.service.base.DAO;

public interface StudentService extends DAO<Student> {
    void register(Student student);

    boolean checkUserName(String name);

    Student login(String username, String password);
}
