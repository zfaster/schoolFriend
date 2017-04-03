package com.sys.service.school.impl;

import com.sys.bean.school.ClassRoom;
import com.sys.bean.school.Student;
import com.sys.service.base.DaoSupport;
import com.sys.service.school.ClassRoomService;
import com.sys.service.school.StudentService;
import org.springframework.stereotype.Service;

@Service("classRoomService")
public class ClassRoomServiceImpl extends DaoSupport<ClassRoom> implements
        ClassRoomService {
}
