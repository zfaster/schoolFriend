package com.sys.service.school.impl;

import com.sys.bean.school.IndexImage;
import com.sys.bean.school.Student;
import com.sys.service.base.DaoSupport;
import com.sys.service.school.IndexImageService;
import com.sys.service.school.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("indexImageService")
public class IndexImageServiceImpl extends DaoSupport<IndexImage> implements
        IndexImageService {
}
