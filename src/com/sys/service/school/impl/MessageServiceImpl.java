package com.sys.service.school.impl;

import com.sys.bean.school.Message;
import com.sys.bean.school.Student;
import com.sys.service.base.DaoSupport;
import com.sys.service.school.MessageService;
import com.sys.service.school.StudentService;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl extends DaoSupport<Message> implements
        MessageService {
}
